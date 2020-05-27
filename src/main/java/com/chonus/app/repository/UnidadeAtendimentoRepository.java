package com.chonus.app.repository;

import com.chonus.app.domain.UnidadeAtendimento;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the UnidadeAtendimento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnidadeAtendimentoRepository extends MongoRepository<UnidadeAtendimento, String> {
}
