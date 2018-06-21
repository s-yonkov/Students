package com.musala.simple.students.db.mongo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoStudentRepository extends CrudRepository<MongoStudent, Long> {

}