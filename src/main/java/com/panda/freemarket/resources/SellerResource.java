package com.panda.freemarket.resources;

import com.codahale.metrics.annotation.Timed;
import com.panda.freemarket.model.Seller;
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
 *
 *
 */

@Path("/api/v1/sellers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SellerResource {
    private final StorageService storageService;

    public SellerResource(StorageService storageService) {
        this.storageService = storageService;
    }

    @Path("{id}")
    @GET
    @Timed
    public Seller getById(@PathParam("id") int id) {
        final Seller seller = storageService.getSellerById(id);
        if (seller == null) {
            throw new WebApplicationException("Cannot find seller with id: " + id,
                    Response.Status.NOT_FOUND);
        }
        return seller;
    }

    @GET
    @Timed
    public List<Seller> getByRange(@QueryParam("from") @DefaultValue("1") int from,
                                               @QueryParam("count") @DefaultValue("10") int count) {
        List<Seller> sellers = storageService.getSellersByRange(from, count);
        if (sellers == null || sellers.size() == 0) {
            throw new WebApplicationException("No sellers exist in DB for the given range: from="
                + from + ", count=" + count);
        }

        return sellers;
    }

    @POST
    @Timed
    public Response add(@QueryParam("name") String name) {
        storageService.addSeller(name);
        return Response.created(UriBuilder.fromResource(SellerResource.class).build(name))
                .build();
    }

    @Path("{id}")
    @PATCH
    @Timed
    public Response update(@NotNull @Valid final Seller seller) {
        storageService.updateSeller(seller);
        return Response.created(UriBuilder.fromResource(SellerResource.class).build(seller))
                .build();
    }
}
