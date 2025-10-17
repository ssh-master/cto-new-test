package com.efootball.tournament.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import java.util.Date;

/**
 * Entity representing a Player in the local database.
 * 
 * Stores player registration information, profiles, and statistics
 * for tournament participation tracking.
 */
@Entity(tableName = "players")
public class PlayerEntity {
    
    @PrimaryKey(autoGenerate = true)
    private long id;
    
    @ColumnInfo(name = "telegram_user_id")
    private String telegramUserId;
    
    @ColumnInfo(name = "username")
    private String username;
    
    @ColumnInfo(name = "display_name")
    private String displayName;
    
    @ColumnInfo(name = "email")
    private String email;
    
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    
    @ColumnInfo(name = "psn_id")
    private String psnId;
    
    @ColumnInfo(name = "skill_rating")
    private int skillRating;
    
    @ColumnInfo(name = "matches_played")
    private int matchesPlayed;
    
    @ColumnInfo(name = "matches_won")
    private int matchesWon;
    
    @ColumnInfo(name = "registered_at")
    private Date registeredAt;
    
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(String telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPsnId() {
        return psnId;
    }

    public void setPsnId(String psnId) {
        this.psnId = psnId;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        this.skillRating = skillRating;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
