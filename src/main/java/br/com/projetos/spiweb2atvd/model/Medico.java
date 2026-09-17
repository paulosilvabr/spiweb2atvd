package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A anotação @Entity indica que esta classe representa uma tabela no banco de dados.
 * O JPA irá mapeá-la para realizar as operações de persistência.
 */
@Entity
public class Medico {
    
    /**
     * @Id indica que este campo é a chave primária.
     * @GeneratedValue com a estratégia IDENTITY faz com que o banco gere o valor automaticamente (auto-incremento).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String crm;

    /**
     * @OneToMany define que um Médico pode ter várias Consultas.
     * O 'mappedBy = "medico"' mostra que este é o "lado fraco" (inverso) do relacionamento,
     * pois a tabela Médico não terá a coluna de chave estrangeira.
     * O 'cascade = CascadeType.ALL' garante que as operações em Médico afetem suas consultas.
     */
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {}

    public Medico(Long id, String nome, String crm) {
        this.id = id;
        this.nome = nome;
        this.crm = crm;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }

    public String dados() {
        return "Médico: " + this.nome + " - CRM: " + this.crm;
    }

    public String consultas() {
        return "Total de consultas: " + this.consultas.size();
    }
}
