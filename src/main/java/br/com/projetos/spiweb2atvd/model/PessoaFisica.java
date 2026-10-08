package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor // para o SuperBuilder
@NoArgsConstructor // para o Hibernate
public abstract class PessoaFisica extends Pessoa {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[\\p{L} .'-]+", message = "O nome deve conter apenas letras e caracteres válidos")
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "CPF inválido. Informe no formato 000.000.000-00 ou somente os 11 dígitos")
    private String cpf;
}