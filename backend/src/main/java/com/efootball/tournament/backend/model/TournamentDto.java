package com.efootball.tournament.backend.model;

import java.util.Date;

/**
 * Data Transfer Object for Tournament information.
 * 
 * Used for communication between backend services and API consumers.
 * This pure Java class is independent of Android or database frameworks.
 */
public class TournamentDto {
    
    private long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    private int currentPhase;
    private int maxParticipants;

    public TournamentDto() {
    }

    public TournamentDto(long id, String name, String description, Date startDate, 
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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
