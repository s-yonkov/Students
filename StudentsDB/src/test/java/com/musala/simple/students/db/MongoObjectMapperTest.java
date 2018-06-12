package com.musala.simple.students.db;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.musala.simple.students.db.mappers.MongoObjectMapper;

public class MongoObjectMapperTest {

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
