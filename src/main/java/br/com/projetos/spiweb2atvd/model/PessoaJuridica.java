package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaJuridica extends Pessoa {

    private String razaoSocial;

    private String cnpj;

    public PessoaJuridica() {
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
