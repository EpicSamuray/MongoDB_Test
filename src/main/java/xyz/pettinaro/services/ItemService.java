package xyz.pettinaro.services;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import xyz.pettinaro.model.InventoryItem;
import xyz.pettinaro.repository.ItemRepo;

@ApplicationScoped
public class ItemService {

    public record ItemReq(String id, String name) {}
    public record ItemRes(String id, boolean isValid) {}
    
    @Inject
    @Channel("validierung-result")
    Emitter<ItemReq> validierungsAnfrageEmitter;

    @Inject
    ItemRepo invItemRepo;

    private static final Logger LOG = Logger.getLogger(ItemService.class);

    public void sendItemForValidation(ObjectId itemid) {
        LOG.info("Sending item for validation: " + itemid);
        InventoryItem item = invItemRepo.findById(itemid);
        LOG.info("Payload Item: " + item.id);
        if (item != null) {
            validierungsAnfrageEmitter.send(new ItemReq(item.id.toString(), item.getName()));
            LOG.info("Sended item for validation: " + item.id);
        }
    }

    @Transactional
    @Incoming("validierung-request")
    public void updatedItemValidationStatus(ItemRes message) {
        LOG.info("Received validation result: " + message);
        ObjectId objectId = new ObjectId(message.id());
        InventoryItem item = invItemRepo.findById(objectId);
        LOG.info("Payload Item: " + objectId);
        LOG.info("Payload Item: " + message.isValid());
        if (item != null) {
            item.setValidation(message.isValid());
            LOG.info("Persisting item: " + item.id);
            invItemRepo.update(item);
            LOG.info("Persisted item: " + item.id);
        }
        LOG.info("Payload Item: " + item.isValidation());
    }

    
}
