package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor // para o SuperBuilder
@NoArgsConstructor // para o Hibernate
@Getter
@Setter
@Entity
@Table(name = "tb_paciente")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Paciente extends PessoaFisica {
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();
}