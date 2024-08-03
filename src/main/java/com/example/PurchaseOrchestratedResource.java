package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("purchase-orchestrated")
public class PurchaseOrchestratedResource {
    @Inject
    private CreditService creditService;
    @Inject
    private OrderService orderService;

    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga() {
        long id = 0L;
        purchase(++id, 20);
        purchase(++id, 30);
        purchase(++id, 30);
        purchase(++id, 25);
        return Response.ok().build();
    }

    private void purchase(Long id, int value) {
        orderService.doOrder(id);
        try {
            creditService.newOrderValue(id, value);
            System.out.println("Order " + id + ", Registered. Value = " + value + ". Balance available = " + creditService.getCreditTotal());
        } catch (IllegalStateException e) {
            orderService.undoOrder(id);
            System.err.println("Order " + id + ", reversed. Value = " + value);
        }
    }
}
