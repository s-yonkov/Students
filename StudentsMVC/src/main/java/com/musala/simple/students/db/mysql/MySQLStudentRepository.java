package com.musala.simple.students.db.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySQLStudentRepository extends JpaRepository<MySQLStudent, Long> {

}
