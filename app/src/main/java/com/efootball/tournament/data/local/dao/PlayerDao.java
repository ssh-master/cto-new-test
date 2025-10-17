package com.efootball.tournament.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.efootball.tournament.data.local.entity.PlayerEntity;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * Data Access Object for Player entities.
 * 
 * Provides CRUD operations and queries for players using RxJava reactive streams.
 */
@Dao
public interface PlayerDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(PlayerEntity player);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<PlayerEntity> players);
    
    @Update
    Completable update(PlayerEntity player);
    
    @Delete
    Completable delete(PlayerEntity player);
    
    @Query("SELECT * FROM players WHERE id = :playerId")
    Single<PlayerEntity> getPlayerById(long playerId);
    
    @Query("SELECT * FROM players WHERE telegram_user_id = :telegramUserId")
    Single<PlayerEntity> getPlayerByTelegramUserId(String telegramUserId);
    
    @Query("SELECT * FROM players ORDER BY skill_rating DESC")
    Flowable<List<PlayerEntity>> getAllPlayersByRating();
    
    @Query("SELECT * FROM players ORDER BY registered_at DESC")
    Flowable<List<PlayerEntity>> getAllPlayers();
    
    @Query("DELETE FROM players")
    Completable deleteAll();
}
