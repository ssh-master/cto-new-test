package com.efootball.tournament.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import java.util.Date;

/**
 * Entity representing a Tournament in the local database.
 * 
 * Stores tournament metadata, configuration, and state information
 * for offline access and synchronization with the backend service.
 */
@Entity(tableName = "tournaments")
public class TournamentEntity {
    
    @PrimaryKey(autoGenerate = true)
    private long id;
    
    @ColumnInfo(name = "name")
    private String name;
    
    @ColumnInfo(name = "description")
    private String description;
    
    @ColumnInfo(name = "start_date")
    private Date startDate;
    
    @ColumnInfo(name = "end_date")
    private Date endDate;
    
    @ColumnInfo(name = "status")
    private String status;
    
    @ColumnInfo(name = "current_phase")
    private int currentPhase;
    
    @ColumnInfo(name = "max_participants")
    private int maxParticipants;
    
    @ColumnInfo(name = "created_at")
    private Date createdAt;
    
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
