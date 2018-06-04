package com.musala.simple.students.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDB implements DataBase {

	private final MongoClient mongoClient;
	private DB database;
	private DBCollection collection;

	public MongoDB() {

		mongoClient = new MongoClient();
		database = mongoClient.getDB("studentsDB");
		collection = database.getCollection("studentsCollection");
	}

	public DBCollection getCollection() {
		return collection;
	}

	@Override
	public void insert(Student student) {

	}

	@Override
	public Student getStudentById(int id) {
		return null;
	}

	@Override
	public void deleteDb() {
		this.collection.drop();
	}

	@Override
	public void updateDb(StudentGroup students) {

	}

	@Override
	public void createDb(StudentGroup students) {
		students.getStudents().forEach(student -> this.collection.insert(MongoObjectMapper.toDBObject(student)));
	}

	@Override
	public StudentGroup getDb() {

		return MongoObjectMapper.toStudentGroup(this.collection);
	}

	@Override
	public boolean isEmpty() {

		if (this.collection.count() > 0) {
			return false;
		} else {
			return true;
		}

	}

}
