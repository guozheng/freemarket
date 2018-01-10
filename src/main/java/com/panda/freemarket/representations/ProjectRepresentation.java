package com.panda.freemarket.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.panda.freemarket.model.Project;

import java.sql.Timestamp;

/**
 * Created by gzge. All Rights Reserved
 */
public class ProjectRepresentation {
    private int id;
    private Timestamp created_time;
    private String name;
    private String description;
    private Timestamp bid_deadline;
    private int buyer_id;

    public ProjectRepresentation(int id, Timestamp created_time, String name, String description, Timestamp bid_deadline, int buyer_id) {
        this.id = id;
        this.created_time = created_time;
        this.name = name;
        this.description = description;
        this.bid_deadline = bid_deadline;
        this.buyer_id = buyer_id;
    }

    public ProjectRepresentation(Project project) {
        this.id = project.getId();
        this.created_time = project.getCreated_time();
        this.name = project.getName();
        this.description = project.getDescription();
        this.bid_deadline = project.getBid_deadline();
        this.buyer_id = project.getBuyer_id();
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public Timestamp getCreated_time() {
        return created_time;
    }

    @JsonProperty
    public void setCreated_time(Timestamp created_time) {
        this.created_time = created_time;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public Timestamp getBid_deadline() {
        return bid_deadline;
    }

    @JsonProperty
    public void setBid_deadline(Timestamp bid_deadline) {
        this.bid_deadline = bid_deadline;
    }

    @JsonProperty
    public int getBuyer_id() {
        return buyer_id;
    }

    @JsonProperty
    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }
}
