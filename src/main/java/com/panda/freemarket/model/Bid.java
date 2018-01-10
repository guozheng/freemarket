package com.panda.freemarket.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by gzge. All Rights Reserved
 */
public class Bid {
    private int id;
    private Timestamp created_time;
    private double price;
    private String status;
    private int seller_id;
    private int project_id;

    @JsonCreator
    public Bid(@JsonProperty("id") int id,
               @JsonProperty("created_time") Timestamp created_time,
               @JsonProperty("price") double price,
               @JsonProperty("status") String status,
               @JsonProperty("seller_id") int seller_id,
               @JsonProperty("project_id") int project_id) {
        this.id = id;
        this.created_time = created_time;
        this.price = price;
        this.status = status;
        this.seller_id = seller_id;
        this.project_id = project_id;
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
    public double getPrice() {
        return price;
    }

    @JsonProperty
    public void setPrice(double price) {
        this.price = price;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }

    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty
    public int getSeller_id() {
        return seller_id;
    }

    @JsonProperty
    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    @JsonProperty
    public int getProject_id() {
        return project_id;
    }

    @JsonProperty
    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id &&
                Double.compare(bid.price, price) == 0 &&
                seller_id == bid.seller_id &&
                project_id == bid.project_id &&
                Objects.equals(created_time, bid.created_time) &&
                Objects.equals(status, bid.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created_time, price, status, seller_id, project_id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bid{");
        sb.append("id=").append(id);
        sb.append(", created_time=").append(created_time);
        sb.append(", price=").append(price);
        sb.append(", status='").append(status).append('\'');
        sb.append(", seller_id=").append(seller_id);
        sb.append(", project_id=").append(project_id);
        sb.append('}');
        return sb.toString();
    }
}
