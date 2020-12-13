package ru.geekbrains.rest;


import ru.geekbrains.service.ProductRepresentative;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Local
@Path("v1/product")
public interface ProductServiceRs {

@POST
@Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductRepresentative productRepresentative);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductRepresentative productRepresentative);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") long id);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductRepresentative findById(@PathParam("id") long id);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductRepresentative> findAll();




}
