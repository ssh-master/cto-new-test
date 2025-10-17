package com.efootball.tournament.domain.model;

import java.util.Date;

/**
 * Domain model representing a Tournament.
 * 
 * This is a pure business logic representation, independent of database or network layers.
 * Used in the domain and presentation layers to maintain clean architecture.
 */
public class Tournament {
    
    private final long id;
    private final String name;
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final String status;
    private final int currentPhase;
    private final int maxParticipants;

    public Tournament(long id, String name, String description, Date startDate, 
                     Date endDate, String status, int currentPhase, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.currentPhase = currentPhase;
        this.maxParticipants = maxParticipants;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }
}
