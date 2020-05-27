package com.chonus.app.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.Raca} entity.
 */
public class RacaDTO implements Serializable {
    
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
        if (!(o instanceof RacaDTO)) {
            return false;
        }

        return id != null && id.equals(((RacaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RacaDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
