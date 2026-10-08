package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tb_pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_pessoa")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor // para o SuperBuilder
@NoArgsConstructor // para o Hibernate
public class PessoaJuridica extends Pessoa {

    @NotBlank
    @Size(min = 2, max = 150)
    @Pattern(regexp = "[\\p{L}\\p{N} .,'&-]+", message = "A razão social contém caracteres inválidos")
    private String razaoSocial;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}", message = "CNPJ inválido. Informe no formato 00.000.000/0000-00 ou somente os 14 dígitos")
    private String cnpj;
}