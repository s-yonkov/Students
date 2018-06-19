package com.musala.simple.students.db.mappers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.musala.simple.students.db.Student;
import com.musala.simple.students.db.StudentGroup;

public class MongoObjectMapper {

    public DBObject toDBObject(Student student) {

        return new BasicDBObject().append("id", student.getId()).append("name", student.getName())
                .append("age", student.getAge()).append("grade", student.getGrade());
    }

    public StudentGroup toStudentGroup(DBCollection collection) {
        StudentGroup students = new StudentGroup();
        Gson gson = new Gson();
        ArrayList<Student> tempStudents = new ArrayList<>();

        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            Student student = gson.fromJson(obj.toString(), Student.class);
            tempStudents.add(student);
        }
        students.setStudents(tempStudents);

        return students;
    }

}
