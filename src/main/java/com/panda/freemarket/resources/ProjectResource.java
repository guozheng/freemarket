package com.panda.freemarket.resources;

import com.codahale.metrics.annotation.Timed;
import com.panda.freemarket.model.Project;
import com.panda.freemarket.services.StorageService;
import io.dropwizard.jersey.PATCH;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 */
@Path("/api/v1/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
    private final StorageService storageService;

    public ProjectResource(StorageService storageService) {
        this.storageService = storageService;
    }

    @Path("{id}")
    @GET
    @Timed
    public Project getById(@PathParam("id") int id) {
        final Project project = storageService.getProjectById(id);
        if (project == null) {
            throw new WebApplicationException("Cannot find project with id: " + id,
                    Response.Status.NOT_FOUND);
        }
        return project;
    }

    @GET
    @Timed
    public List<Project> getByRange(@QueryParam("status") @DefaultValue("open") String status,
                                    @QueryParam("from") @DefaultValue("1") int from,
                                    @QueryParam("count") @DefaultValue("10") int count) {
        List<Project> projects = null;

        // validate status
        if (status.equalsIgnoreCase("open")) {
            projects = storageService.getOpenProjectsByRange(from, count);
        } else if (status.equalsIgnoreCase("closed")) {
            projects = storageService.getClosedProjectsByRange(from, count);
        } else {
            throw new WebApplicationException("Invalid project status, should be 'open' or 'closed'",
                    Response.Status.BAD_REQUEST);
        }

        if (projects == null || projects.size() == 0) {
            throw new WebApplicationException("No projects found for the given range: from=" +
                    from + ", count=" + count, Response.Status.NOT_FOUND);
        }

        return projects;
    }

    @POST
    @Timed
    public Response add(@NotNull @Valid final Project project) {
        project.setCreated_time(new Timestamp(System.currentTimeMillis()));
        storageService.addProject(project);
        return Response.created(UriBuilder.fromResource(ProjectResource.class).build(project))
                .build();
    }

    @Path("{id}")
    @PATCH
    @Timed
    public Response update(@NotNull @Valid final Project project) {
        storageService.updateProject(project);
        return Response.created(UriBuilder.fromResource(ProjectResource.class).build(project))
                .build();
    }
}
