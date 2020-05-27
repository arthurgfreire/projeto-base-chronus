package com.chonus.app.repository;

import com.chonus.app.domain.TipoUsuario;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TipoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoUsuarioRepository extends MongoRepository<TipoUsuario, String> {
}
