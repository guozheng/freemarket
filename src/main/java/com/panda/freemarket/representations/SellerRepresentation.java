package com.panda.freemarket.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.panda.freemarket.model.Seller;

/**
 * Created by gzge. All Rights Reserved
 *
 * Seller representation that converts back and forth between POJO and JSON.
 */
public class SellerRepresentation {
    private int id;
    private String name;

    public SellerRepresentation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SellerRepresentation(Seller seller) {
        this.id = seller.getId();
        this.name = seller.getName();
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
