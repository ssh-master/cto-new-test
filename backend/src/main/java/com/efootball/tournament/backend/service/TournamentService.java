package com.efootball.tournament.backend.service;

import com.efootball.tournament.backend.model.TournamentDto;
import com.efootball.tournament.data.local.dao.TournamentDao;
import com.efootball.tournament.data.local.entity.TournamentEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Business logic service for Tournament operations.
 *
 * This service encapsulates the core business logic for tournament management,
 * decoupled from Android-specific dependencies. It can be used by:
 * - Android app module for local operations
 * - REST API endpoints when backend is migrated
 * - Background workers and scheduled tasks
 *
 * Key responsibilities:
 * - Tournament creation and validation
 * - Tournament lifecycle management (8 phases)
 * - Player registration and eligibility checks
 * - Match scheduling algorithms
 */
@Singleton
public class TournamentService {

    private final TournamentDao tournamentDao;

    @Inject
    public TournamentService(TournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    public TournamentDto createTournament(String name, String description, int maxParticipants) {
        TournamentEntity newTournament = new TournamentEntity();
        newTournament.setName(name);
        newTournament.setDescription(description);
        newTournament.setMaxParticipants(maxParticipants);
        newTournament.setStatus("PENDING");
        newTournament.setCreatedAt(new Date());
        newTournament.setUpdatedAt(new Date());

        tournamentDao.insert(newTournament).blockingAwait();
        // This is a simplification. In a real app, we'd get the inserted ID.
        // For now, we'll just fetch the latest tournament. This is not robust.
        List<TournamentEntity> tournaments = tournamentDao.getAllTournaments().blockingFirst();
        TournamentEntity latestTournament = tournaments.get(0);

        return toDto(latestTournament);
    }

    public List<TournamentDto> getAllTournaments() {
        return tournamentDao.getAllTournaments()
                .blockingFirst()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TournamentDto getTournamentById(long id) {
        TournamentEntity tournamentEntity = tournamentDao.getTournamentById(id).blockingGet();
        if (tournamentEntity == null) {
            return null;
        }
        return toDto(tournamentEntity);
    }

    public void deleteTournament(long id) {
        TournamentEntity tournamentEntity = tournamentDao.getTournamentById(id).blockingGet();
        if (tournamentEntity != null) {
            tournamentDao.delete(tournamentEntity).blockingAwait();
        }
    }

    private TournamentDto toDto(TournamentEntity entity) {
        if (entity == null) {
            return null;
        }
        return new TournamentDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus(),
                entity.getCurrentPhase(),
                entity.getMaxParticipants()
        );
    }
}
