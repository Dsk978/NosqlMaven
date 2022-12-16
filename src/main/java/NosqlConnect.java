import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

import java.net.UnknownHostException;


public class NosqlConnect {
    public static void main(String[] args) {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            var database = mongoClient.getDatabase("student");
            MongoCollection <Document> collection = database.getCollection("student_details");
            System.out.println("database name -> " + database.getName());

            /*var d1 = new Document("roll_no", 6);
            d1.append("name", "Inaya");
            d1.append("age", 20);

            collection.insertOne(d1);
            collection.deleteOne(Filters.eq("roll_no", 1));
            System.out.println("Document deleted successfully...");*/

            /*collection.updateOne(new Document("name", "Rohit"),
                    new Document("$set", new Document("age", 30)));*/

            try (MongoCursor< Document > cur = collection.find().iterator()) {

                while (cur.hasNext()) {

                    var doc = cur.next();
                    var student_details = new ArrayList < > (doc.values());

                    System.out.println("Roll_No: "+ student_details.get(1));
                    System.out.println("Name:" + student_details.get(2));
                    System.out.println("Age:" + student_details.get(3));
                    /*return new Document("id:", new ObjectId()).append("roll_no", roll_no)
                            .append("class_id", classId)
                            .append("scores", scores);*/
                }
            }
            for (String name : database.listCollectionNames()) {

                System.out.println(name);
            }
        }
    }
}
