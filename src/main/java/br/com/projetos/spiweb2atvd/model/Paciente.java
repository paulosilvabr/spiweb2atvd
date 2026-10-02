package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_paciente")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Paciente extends PessoaFisica {
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();
}