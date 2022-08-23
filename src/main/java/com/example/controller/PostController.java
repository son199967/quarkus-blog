package com.example.controller;


import com.example.model.Post;
import com.example.model.request.PostRequest;
import com.example.service.PostService;
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

@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostController {
    @Inject
    private PostService postService;

    @POST
    @APIResponse(
            responseCode = "201",
            description = "Post Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Post.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Post",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Post already exists",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response createPost(@NotNull @Valid PostRequest post) {
        return Response.status(Response.Status.CREATED).entity(postService.createNewPost(post)).build();
    }

    @PUT
    @Path("/{id}")
    @APIResponse(
            responseCode = "204",
            description = "Post updated",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = SchemaType.OBJECT, implementation = Post.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Post",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "400",
            description = "Post object does not have id",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponse(
            responseCode = "404",
            description = "No Customer found for customerId provided",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response updatePost(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id,
            @NotNull @Valid PostRequest postRequest) {

        if (!Objects.equals(id, postRequest.getId())) {
            throw new ServiceException("Path variable id does not match post.id");
        }
        Post postUpdate = postService.updatePost(postRequest);

        return Response.ok(postUpdate).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id) {

        postService.deletePost(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(
            @Parameter(name = "id", required = true)
            @PathParam("id") Long id) {

        Post post = postService.getPostById(id);

        return Response.ok(post).build();
    }


    @GET
    @Path("/findAll/{page}/{size}")
    public Response findByALL(
            @PathParam("page")   int page,
            @PathParam("size") int size
    ) {

        Page<Post> postPage = postService.getAllPost(page,size);

        return Response.ok(postPage).build();
    }


}
