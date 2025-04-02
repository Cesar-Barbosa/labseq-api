package com.labseq;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.math.BigInteger;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
public class LabseqResource {

    @Inject
    LabseqService service;

	@GET
	@Path("/{n}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLabseqAsString(@PathParam("n") int n) {
		try {
			return Response.ok(service.calculate(n).toString()).build();
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.BAD_REQUEST)
						   .entity(e.getMessage())
						   .build();
		}
	}
}
