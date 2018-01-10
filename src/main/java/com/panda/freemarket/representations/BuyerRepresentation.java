package com.panda.freemarket.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.panda.freemarket.model.Buyer;

/**
 * Created by gzge. All Rights Reserved
 */
public class BuyerRepresentation {
    private int id;
    private String name;

    public BuyerRepresentation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BuyerRepresentation(Buyer buyer) {
        this.id = buyer.getId();
        this.name = buyer.getName();
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
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
}
