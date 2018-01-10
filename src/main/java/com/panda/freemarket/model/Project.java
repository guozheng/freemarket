package com.panda.freemarket.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by gzge. All Rights Reserved
 */
public class Project {
    private int id;
    private Timestamp created_time;
    private String name;
    private String description;
    private Timestamp bid_deadline;
    private int buyer_id;

    @JsonCreator
    public Project(@JsonProperty("id") int id,
                   @JsonProperty("created_time") Timestamp created_time,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("bid_deadline") Timestamp bid_deadline,
                   @JsonProperty("buyer_id") int buyer_id) {
        this.id = id;
        this.created_time = created_time;
        this.name = name;
        this.description = description;
        this.bid_deadline = bid_deadline;
        this.buyer_id = buyer_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                buyer_id == project.buyer_id &&
                Objects.equals(created_time, project.created_time) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(bid_deadline, project.bid_deadline);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created_time, name, description, bid_deadline, buyer_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", created_time=").append(created_time);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", bid_deadline=").append(bid_deadline);
        sb.append(", buyer_id=").append(buyer_id);
        sb.append('}');
        return sb.toString();
    }
}
