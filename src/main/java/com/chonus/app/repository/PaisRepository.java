package com.chonus.app.repository;

import com.chonus.app.domain.Pais;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Pais entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaisRepository extends MongoRepository<Pais, String> {
}
