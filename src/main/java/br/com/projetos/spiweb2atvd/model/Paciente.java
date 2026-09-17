package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_paciente")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Paciente extends PessoaFisica {

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente() {
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
