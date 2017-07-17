package com.github.raulra08.frontend;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicLong;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class BalanceResource {

    private final AtomicLong counter;

    public BalanceResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Path("/balance")
    public Response getBalance() {
        return Response.ok().entity(new Balance(counter.incrementAndGet())).build();
    }

}
