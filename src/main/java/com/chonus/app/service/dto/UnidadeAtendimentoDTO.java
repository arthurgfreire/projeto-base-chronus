package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.UnidadeAtendimento} entity.
 */
public class UnidadeAtendimentoDTO implements Serializable {
    
    private String id;

    @NotNull
    private String razaoSocial;

    private String nomeFantasia;


    private String enderecoId;

    private String entidadeId;
    
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

    public String getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(String EnderecoId) {
        this.enderecoId = EnderecoId;
    }

    public String getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(String EntidadeId) {
        this.entidadeId = EntidadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnidadeAtendimentoDTO)) {
            return false;
        }

        return id != null && id.equals(((UnidadeAtendimentoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnidadeAtendimentoDTO{" +
            "id=" + getId() +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            ", enderecoId='" + getEnderecoId() + "'" +
            ", entidadeId='" + getEntidadeId() + "'" +
            "}";
    }
}
