package com.efootball.tournament.config;

import android.content.Context;
import androidx.room.Room;
import com.efootball.tournament.data.local.AppDatabase;
import com.efootball.tournament.data.local.dao.TournamentDao;
import com.efootball.tournament.data.local.dao.PlayerDao;
import com.efootball.tournament.data.local.dao.MatchDao;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

/**
 * Hilt module that provides application-wide dependencies.
 * 
 * This module is installed in the SingletonComponent, meaning all provided
 * dependencies will live as long as the application.
 * 
 * Responsibilities:
 * - Provide Room database instance
 * - Provide DAO instances for data access
 * - Provide core application services
 */
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "tournament_database"
        )
        .fallbackToDestructiveMigration()
        .build();
    }

    @Provides
    @Singleton
    public TournamentDao provideTournamentDao(AppDatabase database) {
        return database.tournamentDao();
    }

    @Provides
    @Singleton
    public PlayerDao providePlayerDao(AppDatabase database) {
        return database.playerDao();
    }

    @Provides
    @Singleton
    public MatchDao provideMatchDao(AppDatabase database) {
        return database.matchDao();
    }
}
