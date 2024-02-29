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
    
    @Inject
    @Channel("validierung-result")
    Emitter<String> validierungsAnfrageEmitter;

    @Inject
    ItemRepo invItemRepo;

    private static final Logger LOG = Logger.getLogger(ItemService.class);

    @Transactional
    public void sendItemForValidation(ObjectId itemid) {
        LOG.info("Sending item for validation: " + itemid);
        InventoryItem item = invItemRepo.findById(itemid);
        LOG.info("Payload Item: " + item.id);
        if (item != null) {
            validierungsAnfrageEmitter.send(item.getName());
            LOG.info("Sended item for validation: " + item.id);
        }
    }

    @Transactional
    @Incoming("validierung-request")
    public void updatedItemValidationStatus(String message) {
        LOG.info("Received validation result: " + message);
        String[] parts = message.split(",");
        ObjectId objectId = new ObjectId(parts[0]);
        InventoryItem item = invItemRepo.findById(objectId);
        boolean isValidated = Boolean.parseBoolean(parts[1]);
        if (item != null) {
            item.setValidated(isValidated);
            invItemRepo.persist(item);
        }
    }
}
