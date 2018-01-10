package com.panda.freemarket.resources;

import com.codahale.metrics.annotation.Timed;
import com.panda.freemarket.model.Buyer;
import com.panda.freemarket.model.Seller;
import com.panda.freemarket.representations.BuyerRepresentation;
import com.panda.freemarket.representations.SellerRepresentation;
import com.panda.freemarket.services.StorageService;
import io.dropwizard.jersey.PATCH;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */

@Path("/api/v1/buyers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuyerResource {
    private final StorageService storageService;

    public BuyerResource(StorageService storageService) {
        this.storageService = storageService;
    }

    @Path("{id}")
    @GET
    @Timed
    public Buyer getById(@PathParam("id") int id) {
        final Buyer buyer = storageService.getBuyerById(id);
        if (buyer == null) {
            throw new WebApplicationException("Cannot find buyer with id: " + id,
                    Response.Status.NOT_FOUND);
        }
        return buyer;
    }

    @GET
    @Timed
    public List<Buyer> getByRange(@QueryParam("from") @DefaultValue("1") int from,
                                               @QueryParam("count") @DefaultValue("10") int count) {
        List<Buyer> buyers = storageService.getBuyersByRange(from, count);
        if (buyers == null || buyers.size() == 0) {
            throw new WebApplicationException("No sellers exist in DB for the given range: from=" +
                    from + ", count=" + count);
        }

        return buyers;
    }

    @POST
    @Timed
    public Response add(@QueryParam("name") String name) {
        storageService.addBuyer(name);
        return Response.created(UriBuilder.fromResource(BuyerResource.class).build(name))
                .build();
    }

    @Path("{id}")
    @PATCH
    @Timed
    public Response update(@NotNull @Valid final Buyer buyer) {
        storageService.updateBuyer(buyer);
        return Response.created(UriBuilder.fromResource(BuyerResource.class).build(buyer))
                .build();
    }
}
