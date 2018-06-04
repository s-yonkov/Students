package com.musala.simple.students.db;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public abstract class MongoObjectMapper {

	public static final DBObject toDBObject(Student student) {
		return new BasicDBObject("_id", student.getId()).append("name", student.getName())
				.append("age", student.getAge()).append("grade", student.getGrade());
	}

	public static final StudentGroup toStudentGroup(DBCollection collection) {
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
