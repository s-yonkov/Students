package com.musala.simple.students;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class StudentAdaptor {

	public static final DBObject toDBObject(Student student) {
		return new BasicDBObject("_id", student.getId()).append("name", student.getName())
				.append("age", student.getAge()).append("grade", student.getGrade());
	}

	public static final Student toStudentObject(DBObject dbObject) {

		//convert from DB obj to Student object

		return null;
	}

}
