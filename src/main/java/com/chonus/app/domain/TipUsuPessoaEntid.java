package com.chonus.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TipUsuPessoaEntid.
 */
@Document(collection = "icid_tip_usu_pessoa_entid")
public class TipUsuPessoaEntid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @DBRef
    @Field("pessoa")
    @JsonIgnoreProperties(value = "TipUsuPessoaEntids", allowSetters = true)
    private Pessoa pessoa;

    @DBRef
    @Field("entidade")
    @JsonIgnoreProperties(value = "TipUsuPessoaEntids", allowSetters = true)
    private Entidade entidade;

    @DBRef
    @Field("tipoUsuario")
    @JsonIgnoreProperties(value = "TipUsuPessoaEntids", allowSetters = true)
    private TipoUsuario tipoUsuario;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa Pessoa) {
        this.pessoa = Pessoa;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade Entidade) {
        this.entidade = Entidade;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario TipoUsuario) {
        this.tipoUsuario = TipoUsuario;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipUsuPessoaEntid)) {
            return false;
        }
        return id != null && id.equals(((TipUsuPessoaEntid) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipUsuPessoaEntid{" +
            "id=" + getId() +
            "}";
    }
}
