package com.musala.simple.students.db.dao;

import org.springframework.stereotype.Component;

import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MongoConnectionException;
import com.musala.simple.students.db.exceptions.MySQLConnectionException;

/**
 * 
 * Interface that provides access to
 *
 */
@Component
public interface StudentDAO {

    /**
     * Insert a {@link StudentDTO} in the Database
     * 
     * @param StudentDTO
     */
    public void insertStudent(StudentDTO student) throws MySQLConnectionException, MongoConnectionException;

    /**
     * Get a {@link StudentDTO} from the Database
     * 
     * @return {@link StudentDTO}
     * @param id - the id of the StudentDTO;
     *            Should be of type long.
     */
    public StudentDTO getStudentById(long id) throws MySQLConnectionException, MongoConnectionException;

    /**
     * Insert collection of students in the Database
     * 
     * @param students of type {@link StudentGroup}
     *
     */
    public void insertStudents(StudentGroup students) throws MongoConnectionException;

    /**
     * Returns collection of students from the Database
     * 
     * @return {@link StudentGroup}
     *
     */
    public StudentGroup getStudents() throws MySQLConnectionException, MongoConnectionException;

    /**
     * Check if the Database is empty
     * 
     * @return boolean
     *
     */
    public boolean isEmpty();

}
