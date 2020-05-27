package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.TipUsuPessoaEntid} entity.
 */
public class TipUsuPessoaEntidDTO implements Serializable {
    
    private String id;


    private String pessoaId;

    private String entidadeId;

    private String tipoUsuarioId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String PessoaId) {
        this.pessoaId = PessoaId;
    }

    public String getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(String EntidadeId) {
        this.entidadeId = EntidadeId;
    }

    public String getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(String TipoUsuarioId) {
        this.tipoUsuarioId = TipoUsuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipUsuPessoaEntidDTO)) {
            return false;
        }

        return id != null && id.equals(((TipUsuPessoaEntidDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipUsuPessoaEntidDTO{" +
            "id=" + getId() +
            ", pessoaId='" + getPessoaId() + "'" +
            ", entidadeId='" + getEntidadeId() + "'" +
            ", tipoUsuarioId='" + getTipoUsuarioId() + "'" +
            "}";
    }
}
