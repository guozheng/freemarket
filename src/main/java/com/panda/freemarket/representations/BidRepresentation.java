package com.panda.freemarket.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.panda.freemarket.model.Bid;

import java.sql.Timestamp;

/**
 * Created by gzge. All Rights Reserved
 */
public class BidRepresentation {
    private int id;
    private Timestamp created_time;
    private double price;
    private String status;
    private int seller_id;
    private int project_id;

    public BidRepresentation(int id,
                             Timestamp created_time,
                             double price,
                             String status,
                             int seller_id,
                             int project_id) {
        this.id = id;
        this.created_time = created_time;
        this.price = price;
        this.status = status;
        this.seller_id = seller_id;
        this.project_id = project_id;
    }

    public BidRepresentation(Bid bid) {
        this.id = bid.getId();
        this.created_time = bid.getCreated_time();
        this.price = bid.getPrice();
        this.status = bid.getStatus();
        this.seller_id = bid.getSeller_id();
        this.project_id = bid.getProject_id();
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
}
