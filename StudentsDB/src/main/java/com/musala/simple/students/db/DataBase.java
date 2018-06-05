package com.musala.simple.students.db;

public interface DataBase {

	public void insert(Student student);

	public Student getStudentById(int id);

	public void insertStudents(StudentGroup students);

	public StudentGroup getStudents();

	public boolean isEmpty();

}
