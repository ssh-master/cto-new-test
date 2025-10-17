package com.efootball.tournament.backend.model;

import java.util.Date;

/**
 * Data Transfer Object for Match information.
 */
public class MatchDto {

    private long id;
    private long tournamentId;
    private PlayerDto player1;
    private PlayerDto player2;
    private Date scheduledTime;
    private Integer player1Score;
    private Integer player2Score;
    private String status;

    public MatchDto() {
    }

    public MatchDto(long id, long tournamentId, PlayerDto player1, PlayerDto player2, Date scheduledTime, Integer player1Score, Integer player2Score, String status) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.player1 = player1;
        this.player2 = player2;
        this.scheduledTime = scheduledTime;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.status = status;
    }

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

    public PlayerDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDto player1) {
        this.player1 = player1;
    }

    public PlayerDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDto player2) {
        this.player2 = player2;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
