package com.efootball.tournament.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.efootball.tournament.data.local.entity.TournamentEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * Data Access Object for Tournament entities.
 * 
 * Provides CRUD operations and queries for tournaments using RxJava reactive streams.
 */
@Dao
public interface TournamentDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(TournamentEntity tournament);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<TournamentEntity> tournaments);
    
    @Update
    Completable update(TournamentEntity tournament);
    
    @Delete
    Completable delete(TournamentEntity tournament);
    
    @Query("SELECT * FROM tournaments WHERE id = :tournamentId")
    Single<TournamentEntity> getTournamentById(long tournamentId);
    
    @Query("SELECT * FROM tournaments ORDER BY created_at DESC")
    Flowable<List<TournamentEntity>> getAllTournaments();
    
    @Query("SELECT * FROM tournaments WHERE status = :status ORDER BY created_at DESC")
    Flowable<List<TournamentEntity>> getTournamentsByStatus(String status);
    
    @Query("DELETE FROM tournaments")
    Completable deleteAll();
}
