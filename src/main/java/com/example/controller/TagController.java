package com.example.controller;

import com.example.model.Post;
import com.example.model.Tag;
import com.example.model.request.PostRequest;
import com.example.model.request.TagRequest;
import com.example.service.PostService;
import com.example.service.TagService;
import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;

@Path("/tag")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    @Inject
    private TagService tagService;

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Tag Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Tag.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Tag",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Tag already exists",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response createTag(@NotNull @Valid TagRequest tagRequest) {
        return Response.status(Response.Status.CREATED).entity(tagService.createNewTag(tagRequest)).build();
    }

    @PUT
    @Path("/{id}")
    @APIResponse(
            responseCode = "204",
            description = "Tag updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Tag.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Tag",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Tag object does not have id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Tag found for id provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response updateTag(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id,
            @NotNull @Valid TagRequest tagRequest) {

        if (!Objects.equals(id, tagRequest.getId())) {
            throw new ServiceException("Path variable id does not match tag.id");
        }
        Tag tagUpdate = tagService.updateTag(tagRequest);

        return Response.ok(tagUpdate).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTag(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id) {

        tagService.deleteTag(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id) {

        Tag tag = tagService.getTagById(id);

        return Response.ok(tag).build();
    }


    @GET
    @Path("/findAll/{page}/{size}")
    public Response findByAll(
            @PathParam("page")   int page,
            @PathParam("size") int size
    ) {

        Page<Tag> tagPage = tagService.getAllTag(page,size);

        return Response.ok(tagPage).build();
    }
}
