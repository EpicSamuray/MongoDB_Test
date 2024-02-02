package xyz.pettinaro.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import xyz.pettinaro.model.Fruit;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FruitService {

    @Inject MongoClient mongoClient;

    public List<Fruit> list() {
        List<Fruit> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Fruit fruit = new Fruit();
                fruit.setName(document.getString("name"));
                fruit.setDescription(document.getString("description"));
                list.add(fruit);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(Fruit fruit) {
        Document document = new Document()
                .append("name", fruit.getName())
                .append("description", fruit.getDescription());
        getCollection().insertOne(document);
    }


    private MongoCollection getCollection() {
        return mongoClient.getDatabase("fruit").getCollection("fruit");
    }
}
