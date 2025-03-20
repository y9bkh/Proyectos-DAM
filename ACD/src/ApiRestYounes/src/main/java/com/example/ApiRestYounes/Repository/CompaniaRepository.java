package com.example.ApiRestYounes.Repository;

import com.example.ApiRestYounes.Model.CompaniaAerea;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompaniaRepository extends MongoRepository<CompaniaAerea, ObjectId> {}


