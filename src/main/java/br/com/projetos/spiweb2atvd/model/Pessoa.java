package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @NotBlank
    @Pattern(regexp = "\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}", message = "Telefone inválido. Use o formato (11) 98765-4321 ou similar")
    private String telefone;
}
