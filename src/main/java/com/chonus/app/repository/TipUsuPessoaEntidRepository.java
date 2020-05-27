package com.chonus.app.repository;

import com.chonus.app.domain.TipUsuPessoaEntid;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TipUsuPessoaEntid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipUsuPessoaEntidRepository extends MongoRepository<TipUsuPessoaEntid, String> {
}
