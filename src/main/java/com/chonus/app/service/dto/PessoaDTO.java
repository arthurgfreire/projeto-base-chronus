package com.chonus.app.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.chonus.app.domain.enumeration.Genero;
import com.chonus.app.domain.enumeration.EstadoCivil;

/**
 * A DTO for the {@link com.chonus.app.domain.Pessoa} entity.
 */
public class PessoaDTO implements Serializable {
    
    private String id;

    private byte[] foto;

    private String fotoContentType;
    @NotNull
    private String nome;

    private String cpf;

    private String rg;

    private String cnh;

    private String passaporte;

    private String certidaoNascimento;

    @NotNull
    private Boolean estrangeiro;

    @NotNull
    private Boolean MoraOutrasPessoas;

    @NotNull
    @Pattern(regexp = "(\\(?\\d{3}\\)?\\s)?(\\d{4,5}\\-\\d{4})")
    private String telefone;

    
    private String email;

    private Boolean temFilhos;

    private Long quantFilhos;

    @NotNull
    private Genero genero;

    @NotNull
    private EstadoCivil estadoCivil;

    private LocalDate dataExpiracao;


    private String enderecoId;

    private String racaId;

    private String tipoSanguineoId;

    private String classificacaoUsuarioId;
    
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

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public String getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(String EnderecoId) {
        this.enderecoId = EnderecoId;
    }

    public String getRacaId() {
        return racaId;
    }

    public void setRacaId(String RacaId) {
        this.racaId = RacaId;
    }

    public String getTipoSanguineoId() {
        return tipoSanguineoId;
    }

    public void setTipoSanguineoId(String TipoSanguineoId) {
        this.tipoSanguineoId = TipoSanguineoId;
    }

    public String getClassificacaoUsuarioId() {
        return classificacaoUsuarioId;
    }

    public void setClassificacaoUsuarioId(String ClassificacaoUsuarioId) {
        this.classificacaoUsuarioId = ClassificacaoUsuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PessoaDTO)) {
            return false;
        }

        return id != null && id.equals(((PessoaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PessoaDTO{" +
            "id=" + getId() +
            ", foto='" + getFoto() + "'" +
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
            ", dataExpiracao='" + getDataExpiracao() + "'" +
            ", enderecoId='" + getEnderecoId() + "'" +
            ", racaId='" + getRacaId() + "'" +
            ", tipoSanguineoId='" + getTipoSanguineoId() + "'" +
            ", classificacaoUsuarioId='" + getClassificacaoUsuarioId() + "'" +
            "}";
    }
}
