package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.chonus.app.domain.Endereco} entity.
 */
public class EnderecoDTO implements Serializable {
    
    private String id;

    @NotNull
    private String bairro;

    private String rua;

    private String numero;

    private String cep;


    private String paisId;

    private String ufId;

    private String cidadeId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPaisId() {
        return paisId;
    }

    public void setPaisId(String PaisId) {
        this.paisId = PaisId;
    }

    public String getUfId() {
        return ufId;
    }

    public void setUfId(String UfId) {
        this.ufId = UfId;
    }

    public String getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(String CidadeId) {
        this.cidadeId = CidadeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnderecoDTO)) {
            return false;
        }

        return id != null && id.equals(((EnderecoDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EnderecoDTO{" +
            "id=" + getId() +
            ", bairro='" + getBairro() + "'" +
            ", rua='" + getRua() + "'" +
            ", numero='" + getNumero() + "'" +
            ", cep='" + getCep() + "'" +
            ", paisId='" + getPaisId() + "'" +
            ", ufId='" + getUfId() + "'" +
            ", cidadeId='" + getCidadeId() + "'" +
            "}";
    }
}
