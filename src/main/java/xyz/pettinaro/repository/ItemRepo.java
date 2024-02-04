package xyz.pettinaro.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import xyz.pettinaro.model.InventoryItem;

@ApplicationScoped
public class ItemRepo implements PanacheMongoRepository<InventoryItem> {

    public boolean isPersistend(ObjectId id) {
        return findById(id) != null;
    }
}
