package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.Uf} entity.
 */
public class UfDTO implements Serializable {
    
    private String id;

    private String descricao;


    private String paisId;
    
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

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String PaisId) {
        this.paisId = PaisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UfDTO)) {
            return false;
        }

        return id != null && id.equals(((UfDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UfDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", paisId='" + getPaisId() + "'" +
            "}";
    }
}
