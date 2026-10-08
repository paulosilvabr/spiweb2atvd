package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_medico")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor // para o SuperBuilder
@NoArgsConstructor // para o Hibernate
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Medico extends PessoaFisica {

    @NotBlank
    @Pattern(regexp = "\\d{4,6}-[A-Z]{2}", message = "O CRM deve seguir o formato 123456-UF (ex: 12345-SP)")
    private String crm;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();
}