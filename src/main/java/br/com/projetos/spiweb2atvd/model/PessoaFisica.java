package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public abstract class PessoaFisica extends Pessoa {

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank
    private String cpf;

    public PessoaFisica() {}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}