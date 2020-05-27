package com.chonus.app.repository.search;

import com.chonus.app.domain.Pessoa;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link Pessoa} entity.
 */
public interface PessoaSearchRepository extends ElasticsearchRepository<Pessoa, String> {
}
