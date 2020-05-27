package com.chonus.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A UnidadeAtendimento.
 */
@Document(collection = "icid_unidade_atendimento")
public class UnidadeAtendimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("razao_social")
    private String razaoSocial;

    @Field("nome_fantasia")
    private String nomeFantasia;

    @DBRef
    @Field("endereco")
    private Endereco endereco;

    @DBRef
    @Field("entidade")
    @JsonIgnoreProperties(value = "UnidadeAtendimentos", allowSetters = true)
    private Entidade entidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco Endereco) {
        this.endereco = Endereco;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade Entidade) {
        this.entidade = Entidade;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnidadeAtendimento)) {
            return false;
        }
        return id != null && id.equals(((UnidadeAtendimento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnidadeAtendimento{" +
            "id=" + getId() +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            "}";
    }
}
