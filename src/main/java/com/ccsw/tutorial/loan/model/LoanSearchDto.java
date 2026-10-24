package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

import java.time.LocalDate;

public class LoanSearchDto {

    private Long gameId;
    private Long customerId;
    LocalDate date;

    private PageableRequest pageable;

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) { this.gameId = gameId; }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long clientId) { this.customerId = clientId; }

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}