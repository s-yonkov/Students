package com.musala.simple.students.db;

public interface DataBase {
	
	public void insert(Student student);
	
	public Student getStudentById(int id);
	
	public void deleteDb();
	
	public void updateDb(StudentGroup students);
	
	public void createDb(StudentGroup students);
	
	public StudentGroup getDb();
	
	public boolean isEmpty();

}
