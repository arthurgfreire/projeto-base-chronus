package com.chonus.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.ClassificacaoUsuario} entity.
 */
public class ClassificacaoUsuarioDTO implements Serializable {
    
    private String id;

    private String descricao;

    private String cor;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassificacaoUsuarioDTO)) {
            return false;
        }

        return id != null && id.equals(((ClassificacaoUsuarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClassificacaoUsuarioDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", cor='" + getCor() + "'" +
            "}";
    }
}
