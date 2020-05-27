package com.chonus.app.repository;

import com.chonus.app.domain.ClassificacaoUsuario;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ClassificacaoUsuario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassificacaoUsuarioRepository extends MongoRepository<ClassificacaoUsuario, String> {
}
