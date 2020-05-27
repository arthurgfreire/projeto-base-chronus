package com.chonus.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.chonus.app.domain.enumeration.Genero;

import com.chonus.app.domain.enumeration.EstadoCivil;

/**
 * A Pessoa.
 */
@Document(collection = "icid_pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("foto")
    private byte[] foto;

    @Field("foto_content_type")
    private String fotoContentType;

    @NotNull
    @Field("nome")
    private String nome;

    @Field("cpf")
    private String cpf;

    @Field("rg")
    private String rg;

    @Field("cnh")
    private String cnh;

    @Field("passaporte")
    private String passaporte;

    @Field("certidao_nascimento")
    private String certidaoNascimento;

    @NotNull
    @Field("estrangeiro")
    private Boolean estrangeiro;

    @NotNull
    @Field("mora_outras_pessoas")
    private Boolean MoraOutrasPessoas;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{3}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    @Field("telefone")
    private String telefone;

    
    @Field("email")
    private String email;

    @Field("tem_filhos")
    private Boolean temFilhos;

    @Field("quant_filhos")
    private Long quantFilhos;

    @NotNull
    @Field("genero")
    private Genero genero;

    @NotNull
    @Field("estado_civil")
    private EstadoCivil estadoCivil;

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

    @DBRef
    @Field("raca")
    @JsonIgnoreProperties(value = "Pessoas", allowSetters = true)
    private Raca raca;

    @DBRef
    @Field("tipoSanguineo")
    @JsonIgnoreProperties(value = "Pessoas", allowSetters = true)
    private TipoSanguineo tipoSanguineo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCertidaoNascimento() {
        return certidaoNascimento;
    }

    public void setCertidaoNascimento(String certidaoNascimento) {
        this.certidaoNascimento = certidaoNascimento;
    }

    public Boolean isEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(Boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public Boolean isMoraOutrasPessoas() {
        return MoraOutrasPessoas;
    }

    public void setMoraOutrasPessoas(Boolean MoraOutrasPessoas) {
        this.MoraOutrasPessoas = MoraOutrasPessoas;
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

    public Boolean isTemFilhos() {
        return temFilhos;
    }

    public void setTemFilhos(Boolean temFilhos) {
        this.temFilhos = temFilhos;
    }

    public Long getQuantFilhos() {
        return quantFilhos;
    }

    public void setQuantFilhos(Long quantFilhos) {
        this.quantFilhos = quantFilhos;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
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

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca Raca) {
        this.raca = Raca;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo TipoSanguineo) {
        this.tipoSanguineo = TipoSanguineo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pessoa)) {
            return false;
        }
        return id != null && id.equals(((Pessoa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + getId() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            ", nome='" + getNome() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", cnh='" + getCnh() + "'" +
            ", passaporte='" + getPassaporte() + "'" +
            ", certidaoNascimento='" + getCertidaoNascimento() + "'" +
            ", estrangeiro='" + isEstrangeiro() + "'" +
            ", MoraOutrasPessoas='" + isMoraOutrasPessoas() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", email='" + getEmail() + "'" +
            ", temFilhos='" + isTemFilhos() + "'" +
            ", quantFilhos=" + getQuantFilhos() +
            ", genero='" + getGenero() + "'" +
            ", estadoCivil='" + getEstadoCivil() + "'" +
            "}";
    }
}
