package com.chonus.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.TipoSanguineo} entity.
 */
public class TipoSanguineoDTO implements Serializable {
    
    private String id;

    private String descricao;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoSanguineoDTO)) {
            return false;
        }

        return id != null && id.equals(((TipoSanguineoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoSanguineoDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
