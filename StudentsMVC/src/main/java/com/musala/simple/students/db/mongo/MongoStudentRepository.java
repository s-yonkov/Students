package com.musala.simple.students.db.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("MongoRepo")
public interface MongoStudentRepository extends MongoRepository<MongoStudent, Long> {

}