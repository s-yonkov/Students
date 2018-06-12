package com.musala.simple.students.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.musala.simple.students.db.exceptions.SQLConnectionException;

public class MySqlDBTest {

	@Test
	public void testInsert() throws SQLConnectionException {
		MySqlDBCopy db = new MySqlDBCopy();
		Student student = new Student(1, "Angel", 22, 10);

		db.insert(student);
		assertTrue(!db.isEmpty());
	}

	@Test
	public void testGetStudentById() throws SQLConnectionException {
		MySqlDBCopy db = new MySqlDBCopy();
		db.insert(new Student(1, "Angel", 22, 10));
		db.insert(new Student(2, "Ivan", 33, 7));

		assertTrue(db.getStudentById(2).getId() == 2 && db.getStudentById(2).getName().equals("Ivan"));
	}

	@Test
	public void testInsertStudents() throws SQLConnectionException {
		MySqlDBCopy db = new MySqlDBCopy();
		db = initDB(db);

		assertTrue((db.getStudents().getStudents().size() == 3) && db.getStudentById(3).getName().equals("Ivan"));
	}

	@Test
	public void testGetStudents() throws SQLConnectionException {
		MySqlDBCopy db = new MySqlDBCopy();
		db = initDB(db);

		assertTrue(
				db.getStudents().getClass().equals(StudentGroup.class) && (db.getStudents().getStudents().size() == 3));

	}

	@Test
	public void testIsEmpty() throws SQLConnectionException {
		MySqlDBCopy db = new MySqlDBCopy();
		assertTrue(db.isEmpty());
		db = initDB(db);
		assertFalse(db.isEmpty());
	}

	private MySqlDBCopy initDB(MySqlDBCopy db) throws SQLConnectionException {
		StudentGroup studentGroup = new StudentGroup();
		ArrayList<Student> students = new ArrayList<>();
		students.add(new Student(1, "Angel", 22, 10));
		students.add(new Student(2, "Petar", 17, 9));
		students.add(new Student(3, "Ivan", 33, 7));
		studentGroup.setStudents(students);
		db.insertStudents(studentGroup);
		return db;
	}

}
