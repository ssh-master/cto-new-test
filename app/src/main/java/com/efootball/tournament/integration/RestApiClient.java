package com.efootball.tournament.integration;

import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Retrofit;

/**
 * REST API client for communicating with the backend service.
 * 
 * This class serves as a facade for all REST API interactions, providing
 * a centralized point for backend communication once REST endpoints are available.
 * 
 * Future API service interfaces will be injected here when the backend module
 * is migrated to provide REST endpoints.
 */
@Singleton
public class RestApiClient {
    
    private final Retrofit retrofit;
    
    @Inject
    public RestApiClient(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
