package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;
import jakarta.persistence.*;

import java.time.LocalDate;

public class LoanDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    private ClientDto client;
    private GameDto game;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return startDate
     */
    public LocalDate getStartDate() {

        return this.startDate;
    }

    /**
     * @param startDate value of {@link #getStartDate()}.
     */
    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;
    }

    /**
     * @return endDate
     */
    public LocalDate getEndDate() {

        return this.endDate;
    }

    /**
     * @param endDate value of {@link #getEndDate()}.
     */
    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;
    }

    /**
     * @return client
     */
    public ClientDto getClient() {

        return this.client;
    }

    /**
     * @param client new value of {@link #getClient()}.
     */
    public void setClient(ClientDto client) {

        this.client = client;
    }

    public GameDto getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame()}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }
}