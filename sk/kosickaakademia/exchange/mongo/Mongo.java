package sk.kosickaakademia.exchange.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Mongo {
   private static MongoClient mongoClient = MongoClients.create();

   public void create (){
       MongoDatabase database = mongoClient.getDatabase("shop");
       MongoCollection<Document> shoes = database.getCollection("shoes");
       Document shoes1 = new Document("brand", "nike").append("ages", new Document());
       shoes.insertOne(shoes1);
   }

   public void writeData(double eur, String[] ratesGui){
       MongoDatabase database = mongoClient.getDatabase("exchange");
       MongoCollection<Document> collection = database.getCollection("history");
       Date currentDate = new Date();
       List<String> list = Arrays.asList(ratesGui.clone());
       Document document = new Document("date",currentDate.toString()).append("value",eur).append("rates", list);
       System.out.println(document.toString());
       collection.insertOne(document);
   }

}
