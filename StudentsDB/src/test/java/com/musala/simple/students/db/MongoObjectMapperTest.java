package com.musala.simple.students.db;

import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.musala.simple.students.db.impl.MongoDB;
import com.musala.simple.students.db.mappers.MongoObjectMapper;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class MongoObjectMapperTest {

    class MongoDBCopy extends MongoDB {

        private MongoServer server;

        public void setMongoClient(MongoClient mongoClient) {
            this.mongoClient = mongoClient;
        }

        public void setDatabase(DB database) {
            this.database = database;
        }

        public void setCollection(DBCollection collection) {
            this.collection = collection;
        }

        public DBCollection getCollection() {
            return this.collection;
        }

        @Override
        protected void createDB() {
            this.server = new MongoServer(new MemoryBackend());
            InetSocketAddress serverAddress = server.bind();
            this.setMongoClient(new MongoClient(new ServerAddress(serverAddress)));
            this.setDatabase(mongoClient.getDB("testdb"));
            this.setCollection(database.getCollection("testcollection"));
            mapper = new MongoObjectMapper();
        }

    }

    @Test
    public void testToDBObject() {
        MongoObjectMapper mapper = new MongoObjectMapper();
        Student student = new Student(1, "Petko", 23, 11);

        assertTrue(mapper.toDBObject(student).getClass().equals(BasicDBObject.class));
    }

    @Test
    public void testToStudentGroup() {
        MongoObjectMapper mapper = new MongoObjectMapper();
        BasicDBObject dbObj = new BasicDBObject().append("id", 1).append("name", "Ivan").append("age", 33)
                .append("grade", 8);

        MongoDBCopy mongo = new MongoDBCopy();
        DBCollection collection = mongo.getCollection();
        collection.insert(dbObj);

        assertTrue(mapper.toStudentGroup(collection).getClass().equals(StudentGroup.class));
    }

}
