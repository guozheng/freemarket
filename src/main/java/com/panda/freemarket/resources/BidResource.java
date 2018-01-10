package com.panda.freemarket.resources;

import com.codahale.metrics.annotation.Timed;
import com.panda.freemarket.model.BidStatus;
import com.panda.freemarket.representations.BidRepresentation;
import com.panda.freemarket.services.StorageService;
import com.panda.freemarket.model.Bid;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.IntParam;
import org.jvnet.hk2.annotations.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */
@Path("/api/v1/bids")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BidResource {
    private final StorageService storageService;

    public BidResource(StorageService storageService) {
        this.storageService = storageService;
    }

    @Path("{id}")
    @GET
    @Timed
    public Bid getById(@PathParam("id") int id) {
        final Bid bid = storageService.getBidById(id);

        if (bid == null) {
            throw new WebApplicationException("Cannot find bid with id: " + id,
                    Response.Status.NOT_FOUND);
        }
        return bid;
    }

    @GET
    @Timed
    public List<Bid> get(@QueryParam("seller_id") @Optional IntParam sellerIdParam,
                                       @QueryParam("project_id") @Optional IntParam projectIdParam) {
        if (sellerIdParam == null && projectIdParam == null) {
            throw new WebApplicationException("Need one of seller_id or project_id to query bids");
        }

        List<Bid> bids = null;

        if (sellerIdParam != null) {
            bids = storageService.getBidsBySellerId(sellerIdParam.get());
        } else {
            bids = storageService.getBidsByProjectId(projectIdParam.get());
        }

        return bids;
    }

    @POST
    @Timed
    public Response add(@NotNull @Valid final Bid bid) {

        // override created_time and status for a new bid
        bid.setCreated_time(new Timestamp(System.currentTimeMillis()));
        bid.setStatus(BidStatus.OPEN.getType());//open status for new bid

        storageService.addBid(bid);
        return Response.created(UriBuilder.fromResource(BidResource.class).build(bid))
                .build();
    }

    @Path("{id}")
    @PATCH
    @Timed
    public Response update(@NotNull @Valid final Bid bid) {
        storageService.updateBid(bid);
        return Response.created(UriBuilder.fromResource(BidResource.class).build(bid))
                .build();
    }
}
