package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public abstract class PessoaFisica extends Pessoa {

    private String nome;

    private String cpf;

    public PessoaFisica() {
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
}
