package com.musala.simple.students.db;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoObjectMapper implements ObjectMaper {

	public DBObject toDBObject(Student student) {
		return new BasicDBObject("_id", student.getId()).append("name", student.getName())
				.append("age", student.getAge()).append("grade", student.getGrade());
	}

	public StudentGroup toStudentGroup(DBCollection collection) {
		StudentGroup students = new StudentGroup();
		Gson gson = new Gson();

		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Student student = gson.fromJson(obj.toString(), Student.class);
			students.getStudents().add(student);
		}

		return students;
	}

}
