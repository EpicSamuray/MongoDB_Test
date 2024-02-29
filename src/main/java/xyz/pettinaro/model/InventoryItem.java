package xyz.pettinaro.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Objects;

@MongoEntity(collection = "InvItem")
public class InventoryItem extends PanacheMongoEntity {

    @BsonProperty("name")
    private String name;
    
    @BsonProperty("price")
    private double price;
    
    @BsonProperty("quantity")
    private int quantity;
    
    @BsonProperty("location")
    private String location;

    @BsonProperty("validated")
    private String validation;

    public InventoryItem() {

    }

    public InventoryItem(ObjectId id, String name, double price, int quantity, String location) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setValidation(String validated) {
        this.validation = validated;
    }

    public String isValidation() {
        return this.validation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InventoryItem other)) {
            return false;
        }

        return Objects.equals(other.id, this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
