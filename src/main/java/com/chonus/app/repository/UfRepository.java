package com.chonus.app.repository;

import com.chonus.app.domain.Uf;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Uf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UfRepository extends MongoRepository<Uf, String> {
}
