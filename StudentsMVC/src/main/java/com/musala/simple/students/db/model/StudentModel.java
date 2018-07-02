package com.musala.simple.students.db.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.musala.simple.students.db.controller.StudentController;
import com.musala.simple.students.db.dao.StudentDAO;
import com.musala.simple.students.db.dbtypes.DataBaseType;
import com.musala.simple.students.db.dto.StudentDTO;
import com.musala.simple.students.db.dto.StudentGroup;
import com.musala.simple.students.db.exceptions.MongoConnectionException;
import com.musala.simple.students.db.exceptions.MySQLConnectionException;
import com.musala.simple.students.db.mongo.MongoStudentDAO;
import com.musala.simple.students.db.mysql.MySQLStudentDAO;
import com.musala.simple.students.db.response.DatabaseResponse;
import com.musala.simple.students.db.response.Response;
import com.musala.simple.students.db.response.State;

/**
 * 
 * This class is used for preparing the data (the {@link Response}) and to execute commands from the
 * {@link StudentController}
 *
 */
@Component
public class StudentModel {

    final static Logger LOGGER = LoggerFactory.getLogger(StudentModel.class);

    @Autowired
    protected MySQLStudentDAO mysql;

    @Autowired
    protected MongoStudentDAO mongo;

    /**
     * This method is used for adding a student ({@link StudentDTO}) in the Database
     * 
     * @param dbTypes - String array with the Databases that should be used
     * @param studentDTO - the student that should be saved in the Database
     * @return {@link Response} containing information if the student was successfully added in the database and
     *         information for the student
     */
    public Response addStudent(String[] dbTypes, StudentDTO studentDTO) {

        List<DataBaseType> dbs = convertToEnumList(dbTypes);
        Response response = new Response();
        DatabaseResponse dbResponse = new DatabaseResponse();

        for (DataBaseType db : dbs) {

            try {
                addStudent(db, studentDTO);
                dbResponse = initDBResponse(db, studentDTO);
                dbResponse.setState(State.SUCCESS);
                response.getDbResponses().add(dbResponse);
            } catch (MySQLConnectionException e) {
                dbResponse = initDBResponse(db, studentDTO);
                dbResponse.setState(State.CONNECTION_PROBLEM);
                response.getDbResponses().add(dbResponse);

                return response;
            } catch (MongoConnectionException e) {
                dbResponse = initDBResponse(db, studentDTO);
                dbResponse.setState(State.CONNECTION_PROBLEM);
                response.getDbResponses().add(dbResponse);

                return response;
            }
        }

        return response;
    }

    /**
     * Method for obtaining a particular student from the Database by ID
     * 
     * @param dbTypes - String array with the Databases that should be used
     * @param id - number of type long
     * @return {@link Response} containing information if the student is presented in the database and information for
     *         the student(if exists in the Database)
     *
     */
    public Response getStudentByID(String[] dbTypes, long id) {

        List<DataBaseType> dbs = convertToEnumList(dbTypes);
        Response response = new Response();
        DatabaseResponse dbResponse = new DatabaseResponse();

        for (DataBaseType db : dbs) {

            try {
                if (mapToDb(db).getStudentById(id) != null) {
                    dbResponse = initDBResponse(db, mapToDb(db).getStudentById(id));
                    dbResponse.setState(State.SUCCESS);
                    response.addDbResponse(dbResponse);
                } else {
                    response.addDbResponse(new DatabaseResponse(State.NO_SUCH_ID, db.toString()));
                }
            } catch (MySQLConnectionException e) {
                response.addDbResponse(new DatabaseResponse(State.CONNECTION_PROBLEM, db.toString()));
            } catch (MongoConnectionException e) {
                response.addDbResponse(new DatabaseResponse(State.CONNECTION_PROBLEM, db.toString()));
            }
        }

        return response;
    }

    /**
     * Method for obtaining all students from the database
     * 
     * @param dbTypes - String array with the Databases that should be used
     * @return {@link Response} - containing all students if presented in the Database
     */
    public Response getAllStudents(String[] dbTypes) {

        List<DataBaseType> dbs = convertToEnumList(dbTypes);
        Response response = new Response();
        DatabaseResponse dbResponse;

        for (DataBaseType db : dbs) {

            dbResponse = new DatabaseResponse();

            try {
                dbResponse.setDbType(db.toString());
                dbResponse.setStudents(mapToDb(db).getStudents());
                dbResponse.setState(State.SUCCESS);
                response.addDbResponse(dbResponse);
            } catch (MySQLConnectionException e) {
                response.addDbResponse(new DatabaseResponse(State.CONNECTION_PROBLEM, db.toString()));
            } catch (MongoConnectionException e) {
                response.addDbResponse(new DatabaseResponse(State.CONNECTION_PROBLEM, db.toString()));
            }

        }

        return response;
    }

    private void addStudent(DataBaseType dbType, StudentDTO studentDTO)
            throws MySQLConnectionException, MongoConnectionException {

        mapToDb(dbType).insertStudent(studentDTO);
    }

    private StudentDAO mapToDb(DataBaseType db) {

        switch (db) {
            case MONGO:
                return mongo;
            case MYSQL:
                return mysql;
            default:
                return null;
        }
    }

    private List<DataBaseType> convertToEnumList(String[] dbTypes) {

        List<DataBaseType> dbs = new ArrayList<>();

        for (String db : dbTypes) {
            dbs.add(DataBaseType.valueOf(db.toUpperCase()));
        }

        return dbs;
    }

    private DatabaseResponse initDBResponse(DataBaseType db, StudentDTO student) {

        StudentGroup students = new StudentGroup();
        students.addStudent(student);
        DatabaseResponse dbResponse = new DatabaseResponse();
        dbResponse.setDbType(db.toString());
        dbResponse.setStudents(students);

        return dbResponse;
    }
}
