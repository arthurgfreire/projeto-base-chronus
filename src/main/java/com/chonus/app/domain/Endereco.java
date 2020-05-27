package com.chonus.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Endereco.
 */
@Document(collection = "icid_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("bairro")
    private String bairro;

    @Field("rua")
    private String rua;

    @Field("numero")
    private String numero;

    @Field("cep")
    private String cep;

    @DBRef
    @Field("pais")
    @JsonIgnoreProperties(value = "Pessoas", allowSetters = true)
    private Pais pais;

    @DBRef
    @Field("uf")
    @JsonIgnoreProperties(value = "Pessoas", allowSetters = true)
    private Uf uf;

    @DBRef
    @Field("cidade")
    @JsonIgnoreProperties(value = "Pessoas", allowSetters = true)
    private Cidade cidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais Pais) {
        this.pais = Pais;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf Uf) {
        this.uf = Uf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade Cidade) {
        this.cidade = Cidade;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Endereco)) {
            return false;
        }
        return id != null && id.equals(((Endereco) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Endereco{" +
            "id=" + getId() +
            ", bairro='" + getBairro() + "'" +
            ", rua='" + getRua() + "'" +
            ", numero='" + getNumero() + "'" +
            ", cep='" + getCep() + "'" +
            "}";
    }
}
