package com.musala.simple.students.db.mapper;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.mongo.MongoStudent;
import com.musala.simple.students.db.mysql.MySQLStudent;

/*
 * A class responsible for mapping of entity data onto DTO object
 */
public class StudentMapper {

    /**
     * Maps a {@link MongoStudent} to a {@link StudentDTO}
     * 
     * @param mongoStudent of type {@link MongoStudent}
     * @return the resulting {@link StudentDTO}
     */
    public static StudentDTO mapToStudentDTO(MongoStudent mongoStudent) {
        return new StudentDTO.StudentDTOBuilder(mongoStudent.getId(), mongoStudent.getName()).age(mongoStudent.getAge())
                .grade(mongoStudent.getGrade()).build();
    }

    /**
     * Maps a {@link MySQLStudent} to a {@link StudentDTO}
     * 
     * @param mySQLStudent of type {@link MySQLStudent}
     * @return the resulting {@link StudentDTO}
     */
    public static StudentDTO mapToStudentDTO(MySQLStudent mySQLStudent) {
        return new StudentDTO.StudentDTOBuilder(mySQLStudent.getId(), mySQLStudent.getName()).age(mySQLStudent.getAge())
                .grade(mySQLStudent.getGrade()).build();
    }

    /**
     * Maps a {@link StudentDTO} to a {@link MySQLStudent}
     * 
     * @param student of type {@link StudentDTO}
     * @return the resulting {@link MySQLStudent}
     */
    public static MySQLStudent mapToMySQLStudent(StudentDTO student) {
        return new MySQLStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
    }

    /**
     * Maps a {@link StudentDTO} to a {@link MongoStudent}
     * 
     * @param student of type {@link StudentDTO}
     * @return the resulting {@link MongoStudent}
     */
    public static MongoStudent mapToMongoStudent(StudentDTO student) {
        return new MongoStudent(student.getId(), student.getName(), student.getAge(), student.getGrade());
    }

}
