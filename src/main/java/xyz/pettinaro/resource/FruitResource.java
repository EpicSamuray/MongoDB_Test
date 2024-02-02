package xyz.pettinaro.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import xyz.pettinaro.model.Fruit;
import xyz.pettinaro.service.FruitService;

import java.util.List;

@Path("/fruits")
public class FruitResource {

    @Inject FruitService fruitService;

    @GET
    public List<Fruit> list() {
        return fruitService.list();
    }

    @POST
    public List<Fruit> add(Fruit fruit) {
        fruitService.add(fruit);
        return list();
    }

}
