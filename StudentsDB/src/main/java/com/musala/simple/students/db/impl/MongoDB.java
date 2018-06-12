package com.musala.simple.students.db.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.musala.simple.students.db.DataBase;
import com.musala.simple.students.db.Student;
import com.musala.simple.students.db.StudentGroup;
import com.musala.simple.students.db.mappers.MongoObjectMapper;

public class MongoDB implements DataBase {

	protected MongoClient mongoClient;
	protected DB database;
	protected DBCollection collection;
	protected MongoObjectMapper mapper;

	public MongoDB() {
		CreateDB();
	}

	protected void CreateDB() {
		mongoClient = new MongoClient();
		database = mongoClient.getDB("studentsDB");
		collection = database.getCollection("studentsCollection");
		mapper = new MongoObjectMapper();
	}

	private DBCollection getCollection() {
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
