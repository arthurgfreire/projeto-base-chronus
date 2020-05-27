package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.Cidade} entity.
 */
public class CidadeDTO implements Serializable {
    
    private String id;

    private String descricao;


    private String ufId;
    
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

    public String getUfId() {
        return ufId;
    }

    public void setUfId(String UfId) {
        this.ufId = UfId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CidadeDTO)) {
            return false;
        }

        return id != null && id.equals(((CidadeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CidadeDTO{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", ufId='" + getUfId() + "'" +
            "}";
    }
}
