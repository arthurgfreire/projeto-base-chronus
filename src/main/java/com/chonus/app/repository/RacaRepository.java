package com.chonus.app.repository;

import com.chonus.app.domain.Raca;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Raca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RacaRepository extends MongoRepository<Raca, String> {
}
