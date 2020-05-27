package com.chonus.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.chonus.app.domain.enumeration.TipoCadastro;

import com.chonus.app.domain.enumeration.TipoUnidade;

import com.chonus.app.domain.enumeration.TipoLicneca;

/**
 * A Entidade.
 */
@Document(collection = "icid_entidade")
public class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("tipo_cadastro")
    private TipoCadastro tipoCadastro;

    @NotNull
    @Field("tipo_unidade")
    private TipoUnidade tipoUnidade;

    @NotNull
    @Field("razao_social")
    private String razaoSocial;

    @Field("nome_fantasia")
    private String nomeFantasia;

    @Field("cnpj")
    private String cnpj;

    @Field("inscricao_estadual")
    private String inscricaoEstadual;

    @Field("inscricao_municipal")
    private String inscricaoMunicipal;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{3}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    @Field("telefone")
    private String telefone;

    
    @Field("email")
    private String email;

    @NotNull
    @Field("tipo_licneca")
    private TipoLicneca tipoLicneca;

    @Field("quant_licencas")
    private Long quantLicencas;

    @DBRef
    @Field("endereco")
    private Endereco endereco;

    @DBRef
    @Field("unidadeAtendimento")
    private Set<UnidadeAtendimento> unidadeAtendimentos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco Endereco) {
        this.endereco = Endereco;
    }

    public Set<UnidadeAtendimento> getUnidadeAtendimentos() {
        return unidadeAtendimentos;
    }

    public void setUnidadeAtendimentos(Set<UnidadeAtendimento> UnidadeAtendimentos) {
        this.unidadeAtendimentos = UnidadeAtendimentos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entidade)) {
            return false;
        }
        return id != null && id.equals(((Entidade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entidade{" +
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
            "}";
    }
}
