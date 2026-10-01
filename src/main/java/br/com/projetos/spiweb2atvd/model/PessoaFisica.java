package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public abstract class PessoaFisica extends Pessoa {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[\\p{L} .'-]+", message = "O nome deve conter apenas letras e caracteres válidos")
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF inválido. Informe no formato 000.000.000-00 ou somente os 11 dígitos")
    private String cpf;

    public PessoaFisica() {}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}