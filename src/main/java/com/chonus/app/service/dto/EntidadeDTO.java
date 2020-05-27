package com.chonus.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.chonus.app.domain.enumeration.TipoCadastro;
import com.chonus.app.domain.enumeration.TipoUnidade;
import com.chonus.app.domain.enumeration.TipoLicneca;

/**
 * A DTO for the {@link com.chonus.app.domain.Entidade} entity.
 */
public class EntidadeDTO implements Serializable {
    
    private String id;

    @NotNull
    private TipoCadastro tipoCadastro;

    @NotNull
    private TipoUnidade tipoUnidade;

    @NotNull
    private String razaoSocial;

    private String nomeFantasia;

    private String cnpj;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{3}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    private String telefone;

    
    private String email;

    @NotNull
    private TipoLicneca tipoLicneca;

    private Long quantLicencas;


    private String enderecoId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoCadastro getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(TipoCadastro tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public TipoUnidade getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(TipoUnidade tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoLicneca getTipoLicneca() {
        return tipoLicneca;
    }

    public void setTipoLicneca(TipoLicneca tipoLicneca) {
        this.tipoLicneca = tipoLicneca;
    }

    public Long getQuantLicencas() {
        return quantLicencas;
    }

    public void setQuantLicencas(Long quantLicencas) {
        this.quantLicencas = quantLicencas;
    }

    public String getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(String EnderecoId) {
        this.enderecoId = EnderecoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntidadeDTO)) {
            return false;
        }

        return id != null && id.equals(((EntidadeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntidadeDTO{" +
            "id=" + getId() +
            ", tipoCadastro='" + getTipoCadastro() + "'" +
            ", tipoUnidade='" + getTipoUnidade() + "'" +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", inscricaoEstadual='" + getInscricaoEstadual() + "'" +
            ", inscricaoMunicipal='" + getInscricaoMunicipal() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", email='" + getEmail() + "'" +
            ", tipoLicneca='" + getTipoLicneca() + "'" +
            ", quantLicencas=" + getQuantLicencas() +
            ", enderecoId='" + getEnderecoId() + "'" +
            "}";
    }
}
