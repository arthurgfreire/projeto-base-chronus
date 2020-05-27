package com.chonus.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A ClassificacaoUsuario.
 */
@Document(collection = "icid_classif_usuario")
public class ClassificacaoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("descricao")
    private String descricao;

    @Field("cor")
    private String cor;

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassificacaoUsuario)) {
            return false;
        }
        return id != null && id.equals(((ClassificacaoUsuario) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClassificacaoUsuario{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", cor='" + getCor() + "'" +
            "}";
    }
}
