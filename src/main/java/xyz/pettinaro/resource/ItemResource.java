package xyz.pettinaro.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import xyz.pettinaro.model.InventoryItem;
import xyz.pettinaro.repository.ItemRepo;
import xyz.pettinaro.services.ItemService;

import java.util.List;

@Path("/inv-item")
public class ItemResource {

    @Inject
    ItemRepo invItemRepo;

    @Inject
    ItemService itemService;

    private static final Logger LOG = Logger.getLogger(ItemResource.class);

    @POST
    public Response createItem(InventoryItem item) {
        item.id = new ObjectId();
        LOG.info("Creating new item: " + item.id);
        invItemRepo.persist(item);
        if (invItemRepo.isPersistend(item.id)){
            itemService.sendItemForValidation(item.id);
            return Response.ok(item).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    public List<InventoryItem> getAll() {
        return invItemRepo.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getItemById(@PathParam("id") String id) {
        InventoryItem item = invItemRepo.findById(new ObjectId(id));
        if (item != null) {
            return Response.ok(item).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response getItemById(@PathParam("id") String id, InventoryItem item) {
        InventoryItem entity = invItemRepo.findById(new ObjectId(id));
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        entity.setName(item.getName());
        entity.setPrice(item.getPrice());
        entity.setQuantity(item.getQuantity());
        entity.setLocation(item.getLocation());

        return Response.ok(entity).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        boolean deleted = invItemRepo.deleteById(new ObjectId(id));
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/clear")
    public Response deleteAll() {
        invItemRepo.deleteAll();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
