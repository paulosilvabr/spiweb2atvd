package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_medico")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Medico extends PessoaFisica {

    private String crm;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
