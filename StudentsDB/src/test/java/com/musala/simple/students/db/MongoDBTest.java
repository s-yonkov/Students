package com.musala.simple.students.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.musala.simple.students.db.impl.MongoDB;

public class MongoDBTest {

	@Test
	public void testInsert() {

		MongoDB db = new MongoDBCopy();
		Student student = new Student(1, "Angel", 22, 10);

		db.insert(student);
		assertFalse(db.isEmpty());
	}

	@Test
	public void testGetStudentById() {
		MongoDB db = new MongoDBCopy();
		db.insert(new Student(1, "Pesho", 33, 11));
		db.insert(new Student(2, "Andrei", 19, 9));

		assertTrue(db.getStudentById(1).getId() == 1);

	}

	@Test
	public void testInsertStudents() {
		MongoDB db = new MongoDBCopy();
		db.insert(new Student(1, "Andrei", 19, 3));
		db.insert(new Student(2, "Georgi", 29, 4));
		db.insert(new Student(3, "Peter", 39, 5));

		assertTrue(db.getStudents().getStudents().size() == 3);

	}

	@Test
	public void testGetStudents() {
		MongoDB db = new MongoDBCopy();
		db.insert(new Student(1, "Andrei", 19, 3));
		db.insert(new Student(2, "Georgi", 29, 4));
		db.insert(new Student(3, "Peter", 39, 5));

		assertTrue((db.getStudents().getClass().equals(StudentGroup.class))
				&& !(db.getStudents().getStudents().isEmpty()));
	}

	@Test
	public void testIsEmpty() {
		MongoDB db = new MongoDBCopy();

		assertTrue(db.isEmpty() && db.getStudents().getStudents().size() == 0);

	}
	
	
	

}
