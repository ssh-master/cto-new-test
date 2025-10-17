package com.efootball.tournament;

import android.app.Application;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import dagger.hilt.android.HiltAndroidApp;
import javax.inject.Inject;
import androidx.hilt.work.HiltWorkerFactory;

/**
 * Custom Application class for the eFootball Tournament Platform.
 * 
 * This class serves as the entry point for the application and is responsible for:
 * - Initializing Hilt dependency injection framework
 * - Setting up WorkManager with custom configuration
 * - Initializing application-wide components and services
 * - Managing application lifecycle events
 * 
 * The @HiltAndroidApp annotation triggers Hilt's code generation including a base class
 * for the application that serves as the application-level dependency container.
 */
@HiltAndroidApp
public class TournamentApplication extends Application implements Configuration.Provider {

    @Inject
    HiltWorkerFactory workerFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        
        // Initialize WorkManager with Hilt support
        WorkManager.initialize(
            this,
            getWorkManagerConfiguration()
        );
    }

    @Override
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .setMinimumLoggingLevel(android.util.Log.INFO)
                .build();
    }
}
