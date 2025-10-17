package com.efootball.tournament.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.Index;
import java.util.Date;

/**
 * Entity representing a Match in the local database.
 * 
 * Stores match scheduling, results, and related metadata for tournament progression.
 */
@Entity(
    tableName = "matches",
    foreignKeys = {
        @ForeignKey(
            entity = TournamentEntity.class,
            parentColumns = "id",
            childColumns = "tournament_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = PlayerEntity.class,
            parentColumns = "id",
            childColumns = "player1_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = PlayerEntity.class,
            parentColumns = "id",
            childColumns = "player2_id",
            onDelete = ForeignKey.CASCADE
        )
    },
    indices = {
        @Index("tournament_id"),
        @Index("player1_id"),
        @Index("player2_id")
    }
)
public class MatchEntity {
    
    @PrimaryKey(autoGenerate = true)
    private long id;
    
    @ColumnInfo(name = "tournament_id")
    private long tournamentId;
    
    @ColumnInfo(name = "player1_id")
    private long player1Id;
    
    @ColumnInfo(name = "player2_id")
    private long player2Id;
    
    @ColumnInfo(name = "scheduled_time")
    private Date scheduledTime;
    
    @ColumnInfo(name = "actual_start_time")
    private Date actualStartTime;
    
    @ColumnInfo(name = "actual_end_time")
    private Date actualEndTime;
    
    @ColumnInfo(name = "player1_score")
    private Integer player1Score;
    
    @ColumnInfo(name = "player2_score")
    private Integer player2Score;
    
    @ColumnInfo(name = "winner_id")
    private Long winnerId;
    
    @ColumnInfo(name = "status")
    private String status;
    
    @ColumnInfo(name = "phase")
    private int phase;
    
    @ColumnInfo(name = "round")
    private int round;
    
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

    public long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public long getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(long player1Id) {
        this.player1Id = player1Id;
    }

    public long getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(long player2Id) {
        this.player2Id = player2Id;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Integer getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Integer player1Score) {
        this.player1Score = player1Score;
    }

    public Integer getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Integer player2Score) {
        this.player2Score = player2Score;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
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
