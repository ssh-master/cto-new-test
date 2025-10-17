package com.efootball.tournament.presentation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.efootball.tournament.R;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * Main Activity - Entry point for the Tournament Platform UI.
 * 
 * This activity serves as the primary navigation host and container for the application.
 * It is annotated with @AndroidEntryPoint to enable Hilt dependency injection.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
