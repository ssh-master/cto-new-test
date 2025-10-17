package com.efootball.tournament.backend.service;

import com.efootball.tournament.backend.model.PlayerDto;
import com.efootball.tournament.data.local.dao.PlayerDao;
import com.efootball.tournament.data.local.entity.PlayerEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PlayerService {

    private final PlayerDao playerDao;

    @Inject
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public PlayerDto registerPlayer(String telegramUserId, String username, String displayName, String psnId) {
        PlayerEntity newPlayer = new PlayerEntity();
        newPlayer.setTelegramUserId(telegramUserId);
        newPlayer.setUsername(username);
        newPlayer.setDisplayName(displayName);
        newPlayer.setPsnId(psnId);
        newPlayer.setRegisteredAt(new Date());
        newPlayer.setUpdatedAt(new Date());

        playerDao.insert(newPlayer).blockingAwait();
        
        // This is not a robust way to get the last inserted user.
        // It's a simplification for this stage of development.
        PlayerEntity registeredPlayer = playerDao.getPlayerByTelegramUserId(telegramUserId).blockingGet();

        return toDto(registeredPlayer);
    }

    public PlayerDto getPlayerByTelegramUserId(String telegramUserId) {
        PlayerEntity playerEntity = playerDao.getPlayerByTelegramUserId(telegramUserId).blockingGet();
        return toDto(playerEntity);
    }

    public List<PlayerDto> getAllPlayers() {
        return playerDao.getAllPlayers()
                .blockingFirst()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PlayerDto toDto(PlayerEntity entity) {
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
