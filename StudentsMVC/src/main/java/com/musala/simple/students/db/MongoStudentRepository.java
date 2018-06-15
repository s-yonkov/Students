package com.musala.simple.students.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoStudentRepository extends MongoRepository<Student, Long> {

}