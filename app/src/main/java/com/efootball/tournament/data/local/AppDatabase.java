package com.efootball.tournament.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.efootball.tournament.data.local.dao.TournamentDao;
import com.efootball.tournament.data.local.dao.PlayerDao;
import com.efootball.tournament.data.local.dao.MatchDao;
import com.efootball.tournament.data.local.entity.TournamentEntity;
import com.efootball.tournament.data.local.entity.PlayerEntity;
import com.efootball.tournament.data.local.entity.MatchEntity;
import com.efootball.tournament.data.local.converter.DateConverter;

/**
 * Room Database for the eFootball Tournament Platform.
 * 
 * This database serves as the local persistence layer for the application,
 * providing offline-first capabilities and caching of server data.
 * 
 * Entities:
 * - TournamentEntity: Stores tournament metadata and configuration
 * - PlayerEntity: Stores player registration and profile information
 * - MatchEntity: Stores match schedules, results, and statistics
 * 
 * Version: 1 (initial schema)
 */
@Database(
    entities = {
        TournamentEntity.class,
        PlayerEntity.class,
        MatchEntity.class
    },
    version = 1,
    exportSchema = true
)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    
    public abstract TournamentDao tournamentDao();
    
    public abstract PlayerDao playerDao();
    
    public abstract MatchDao matchDao();
}
