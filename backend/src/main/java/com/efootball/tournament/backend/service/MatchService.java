package com.efootball.tournament.backend.service;

import com.efootball.tournament.backend.model.MatchDto;
import com.efootball.tournament.backend.model.PlayerDto;
import com.efootball.tournament.data.local.dao.MatchDao;
import com.efootball.tournament.data.local.dao.PlayerDao;
import com.efootball.tournament.data.local.entity.MatchEntity;
import com.efootball.tournament.data.local.entity.PlayerEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MatchService {

    private final MatchDao matchDao;
    private final PlayerDao playerDao;

    @Inject
    public MatchService(MatchDao matchDao, PlayerDao playerDao) {
        this.matchDao = matchDao;
        this.playerDao = playerDao;
    }

    public MatchDto createMatch(long tournamentId, long player1Id, long player2Id, Date scheduledTime) {
        MatchEntity newMatch = new MatchEntity();
        newMatch.setTournamentId(tournamentId);
        newMatch.setPlayer1Id(player1Id);
        newMatch.setPlayer2Id(player2Id);
        newMatch.setScheduledTime(scheduledTime);
        newMatch.setStatus("SCHEDULED");
        newMatch.setCreatedAt(new Date());
        newMatch.setUpdatedAt(new Date());

        matchDao.insert(newMatch).blockingAwait();
        
        // This is a simplification. A better approach would be to get the inserted ID.
        List<MatchEntity> matches = matchDao.getMatchesByTournamentId(tournamentId).blockingFirst();
        MatchEntity createdMatch = matches.get(matches.size() - 1);
        
        return toDto(createdMatch);
    }

    public MatchDto getMatchById(long matchId) {
        MatchEntity matchEntity = matchDao.getMatchById(matchId).blockingGet();
        return toDto(matchEntity);
    }

    public List<MatchDto> getMatchesByTournamentId(long tournamentId) {
        return matchDao.getMatchesByTournamentId(tournamentId)
                .blockingFirst()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MatchDto updateMatchScore(long matchId, int player1Score, int player2Score) {
        MatchEntity matchEntity = matchDao.getMatchById(matchId).blockingGet();
        if (matchEntity != null) {
            matchEntity.setPlayer1Score(player1Score);
            matchEntity.setPlayer2Score(player2Score);
            if (player1Score > player2Score) {
                matchEntity.setWinnerId(matchEntity.getPlayer1Id());
            } else if (player2Score > player1Score) {
                matchEntity.setWinnerId(matchEntity.getPlayer2Id());
            }
            matchEntity.setStatus("COMPLETED");
            matchEntity.setUpdatedAt(new Date());
            matchDao.update(matchEntity).blockingAwait();
        }
        return toDto(matchEntity);
    }
    
    private MatchDto toDto(MatchEntity entity) {
        if (entity == null) {
            return null;
        }

        PlayerEntity player1Entity = playerDao.getPlayerById(entity.getPlayer1Id()).blockingGet();
        PlayerEntity player2Entity = playerDao.getPlayerById(entity.getPlayer2Id()).blockingGet();

        PlayerDto player1Dto = toPlayerDto(player1Entity);
        PlayerDto player2Dto = toPlayerDto(player2Entity);

        return new MatchDto(
                entity.getId(),
                entity.getTournamentId(),
                player1Dto,
                player2Dto,
                entity.getScheduledTime(),
                entity.getPlayer1Score(),
                entity.getPlayer2Score(),
                entity.getStatus()
        );
    }

    private PlayerDto toPlayerDto(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }
        return new PlayerDto(
                entity.getId(),
                entity.getTelegramUserId(),
                entity.getUsername(),
                entity.getDisplayName(),
                entity.getPsnId(),
                entity.getSkillRating()
        );
    }
}
