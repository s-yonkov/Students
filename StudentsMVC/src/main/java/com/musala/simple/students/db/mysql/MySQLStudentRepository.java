package com.musala.simple.students.db.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySQLStudentRepository extends CrudRepository<MySQLStudent, Long> {

}
