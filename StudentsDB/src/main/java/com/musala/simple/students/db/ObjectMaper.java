package com.musala.simple.students.db;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public interface ObjectMaper {

	public StudentGroup toStudentGroup(DBCollection collection);

	public DBObject toDBObject(Student student);

}
