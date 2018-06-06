package com.musala.simple.students.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDB implements DataBase {

	private final MongoClient mongoClient;
	private DB database;
	private DBCollection collection;
	private MongoObjectMapper mapper;

	public MongoDB() {

		mongoClient = new MongoClient();
		database = mongoClient.getDB("studentsDB");
		collection = database.getCollection("studentsCollection");
		mapper = new MongoObjectMapper();
	}

	public DBCollection getCollection() {
		return collection;
	}

	@Override
	public void insert(Student student) {
		this.collection.insert(mapper.toDBObject(student));
	}

	@Override
	public Student getStudentById(int id) {
		return this.getStudents().getStudentById(id);
	}

	@Override
	public void insertStudents(StudentGroup students) {
		students.getStudents().forEach(student -> this.collection.insert(mapper.toDBObject(student)));
	}

	@Override
	public StudentGroup getStudents() {
		return mapper.toStudentGroup(this.collection);
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
