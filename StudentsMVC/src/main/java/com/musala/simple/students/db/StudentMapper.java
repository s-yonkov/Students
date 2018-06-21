package com.musala.simple.students.db;

import com.musala.simple.students.db.mongo.MongoStudent;
import com.musala.simple.students.db.mysql.MySQLStudent;

public class StudentMapper {

     public static StudentDTO mapToStudentDTO(MongoStudent mongoStudent) {
    
     return new StudentDTO(mongoStudent.getId(), mongoStudent.getName(), mongoStudent.getAge(),
     mongoStudent.getGrade());
     }

    public static StudentDTO mapToStudentDTO(MySQLStudent mySQLStudent) {

        return new StudentDTO(mySQLStudent.getId(), mySQLStudent.getName(), mySQLStudent.getAge(),
                mySQLStudent.getGrade());
    }

    public static MySQLStudent mapToMySQLStudent(StudentDTO student) {

        return new MySQLStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
    }

     public static MongoStudent mapToMongoStudent(StudentDTO student) {
    
     return new MongoStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
     }

}
