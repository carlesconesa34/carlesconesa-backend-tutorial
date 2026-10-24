package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.customer.model.CustomerDto;
import com.ccsw.tutorial.game.model.GameDto;

import java.time.LocalDate;

public class LoanDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    private CustomerDto customer;
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
     * @return customer
     */
    public CustomerDto getCustomer() {

        return this.customer;
    }

    /**
     * @param client new value of {@link #getCustomer()}.
     */
    public void setCustomer(CustomerDto client) {

        this.customer = client;
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