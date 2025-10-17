package com.efootball.tournament.backend.model;

import java.util.Date;

/**
 * Data Transfer Object for Player information.
 */
public class PlayerDto {

    private long id;
    private String telegramUserId;
    private String username;
    private String displayName;
    private String psnId;
    private int skillRating;

    public PlayerDto() {
    }

    public PlayerDto(long id, String telegramUserId, String username, String displayName, String psnId, int skillRating) {
        this.id = id;
        this.telegramUserId = telegramUserId;
        this.username = username;
        this.displayName = displayName;
        this.psnId = psnId;
        this.skillRating = skillRating;
    }

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
}
