package com.musala.simple.students.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.musala.simple.students.db.impl.MongoDB;
import com.musala.simple.students.db.mappers.MongoObjectMapper;

import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;

public class MongoDBTest {

    class MongoDBForTest extends MongoDB {

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
    public void testInsert() {

        MongoDB db = new MongoDBForTest();
        Student student = new Student(1, "Angel", 22, 10);

        db.insert(student);
        assertFalse(db.isEmpty());
    }

    @Test
    public void testGetStudentById() {
        MongoDB db = new MongoDBForTest();
        db.insert(new Student(1, "Pesho", 33, 11));
        db.insert(new Student(2, "Andrei", 19, 9));

        assertTrue(db.getStudentById(1).getId() == 1);

    }

    @Test
    public void testInsertStudents() {
        MongoDB db = new MongoDBForTest();
        db.insert(new Student(1, "Andrei", 19, 3));
        db.insert(new Student(2, "Georgi", 29, 4));
        db.insert(new Student(3, "Peter", 39, 5));

        assertTrue(db.getStudents().getStudents().size() == 3);

    }

    @Test
    public void testGetStudents() {
        MongoDB db = new MongoDBForTest();
        db.insert(new Student(1, "Andrei", 19, 3));
        db.insert(new Student(2, "Georgi", 29, 4));
        db.insert(new Student(3, "Peter", 39, 5));

        assertTrue((db.getStudents().getClass().equals(StudentGroup.class))
                && !(db.getStudents().getStudents().isEmpty()));
    }

    @Test
    public void testIsEmpty() {
        MongoDB db = new MongoDBForTest();

        assertTrue(db.isEmpty() && db.getStudents().getStudents().size() == 0);

    }

}
