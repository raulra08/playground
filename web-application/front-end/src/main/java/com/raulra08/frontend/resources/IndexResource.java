package com.raulra08.frontend.resources;

import com.raulra08.frontend.views.index.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces({MediaType.TEXT_HTML})
public class IndexResource {

    @GET
    public Response getIndex(){
        return Response.ok().entity(new IndexView("index.ftl")).build();
    }
}
