package sk.kosickaakademia.exchange.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Mongo {
   private static MongoClient mongoClient = MongoClients.create();

   public void create (){
       MongoDatabase database = mongoClient.getDatabase("shop");
       MongoCollection<Document> shoes = database.getCollection("shoes");
       Document shoes1 = new Document("brand", "nike").append("ages", new Document());
       shoes.insertOne(shoes1);
   }

}
