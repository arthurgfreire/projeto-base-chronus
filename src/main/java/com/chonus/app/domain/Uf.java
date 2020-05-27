package com.chonus.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Uf.
 */
@Document(collection = "icid_uf")
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("descricao")
    private String descricao;

    @DBRef
    @Field("pais")
    @JsonIgnoreProperties(value = "Ufs", allowSetters = true)
    private Pais pais;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais Pais) {
        this.pais = Pais;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Uf)) {
            return false;
        }
        return id != null && id.equals(((Uf) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Uf{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
