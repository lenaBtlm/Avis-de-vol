package com.airfrance.avisdevol.model;

enum Status {
    TREATY,
    PUBLISHED,
    REJECTED
}

public class StatusDTO {
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}