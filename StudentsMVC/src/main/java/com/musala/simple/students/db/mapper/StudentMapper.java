package com.musala.simple.students.db.mapper;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.mongo.MongoStudent;
import com.musala.simple.students.db.mysql.MySQLStudent;

public class StudentMapper {

    public static StudentDTO mapToStudentDTO(MongoStudent mongoStudent) {

        return new StudentDTO.StudentDTOBuilder(mongoStudent.getId(), mongoStudent.getName()).age(mongoStudent.getAge())
                .grade(mongoStudent.getGrade()).build();
    }

    public static StudentDTO mapToStudentDTO(MySQLStudent mySQLStudent) {

        return new StudentDTO.StudentDTOBuilder(mySQLStudent.getId(), mySQLStudent.getName()).age(mySQLStudent.getAge())
                .grade(mySQLStudent.getGrade()).build();
    }

    public static MySQLStudent mapToMySQLStudent(StudentDTO student) {

        return new MySQLStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
    }

    public static MongoStudent mapToMongoStudent(StudentDTO student) {

        return new MongoStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
    }

}
