package com.efootball.tournament.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.efootball.tournament.data.local.entity.MatchEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * Data Access Object for Match entities.
 * 
 * Provides CRUD operations and queries for matches using RxJava reactive streams.
 */
@Dao
public interface MatchDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(MatchEntity match);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<MatchEntity> matches);
    
    @Update
    Completable update(MatchEntity match);
    
    @Delete
    Completable delete(MatchEntity match);
    
    @Query("SELECT * FROM matches WHERE id = :matchId")
    Single<MatchEntity> getMatchById(long matchId);
    
    @Query("SELECT * FROM matches WHERE tournament_id = :tournamentId ORDER BY scheduled_time ASC")
    Flowable<List<MatchEntity>> getMatchesByTournament(long tournamentId);
    
    @Query("SELECT * FROM matches WHERE tournament_id = :tournamentId AND phase = :phase ORDER BY round, scheduled_time")
    Flowable<List<MatchEntity>> getMatchesByTournamentAndPhase(long tournamentId, int phase);
    
    @Query("SELECT * FROM matches WHERE player1_id = :playerId OR player2_id = :playerId ORDER BY scheduled_time DESC")
    Flowable<List<MatchEntity>> getMatchesByPlayer(long playerId);
    
    @Query("SELECT * FROM matches WHERE status = :status ORDER BY scheduled_time ASC")
    Flowable<List<MatchEntity>> getMatchesByStatus(String status);
    
    @Query("DELETE FROM matches WHERE tournament_id = :tournamentId")
    Completable deleteMatchesForTournament(long tournamentId);
    
    @Query("DELETE FROM matches")
    Completable deleteAll();
}
