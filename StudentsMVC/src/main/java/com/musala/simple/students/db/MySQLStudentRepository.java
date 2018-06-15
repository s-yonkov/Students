package com.musala.simple.students.db;

import org.springframework.data.repository.CrudRepository;

public interface MySQLStudentRepository extends CrudRepository<Student, Integer> {

}
