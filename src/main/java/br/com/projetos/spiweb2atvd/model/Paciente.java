package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A anotação @Entity indica que esta classe representa uma tabela no banco de dados.
 * O JPA irá gerenciar esta entidade e mapear seus atributos para colunas na tabela.
 */
@Entity
public class Paciente {
    
    /**
     * @Id define este atributo como a chave primária (PK) da tabela.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) delega ao banco de dados 
     * a responsabilidade de gerar o ID (auto-incremento).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String telefone;
    
    /**
     * @OneToMany indica um relacionamento de um (Paciente) para muitos (Consulta).
     * O atributo 'mappedBy = "paciente"' define que o Paciente é o "lado fraco" (inverso) da relação.
     * Ou seja, a chave estrangeira fica na tabela da Consulta.
     * O 'cascade = CascadeType.ALL' propaga as operações (salvar, deletar) para as consultas associadas.
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente() {}

    public Paciente(Long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }

    public String dados() {
        return "Paciente: " + this.nome + " - Tel: " + this.telefone;
    }

    public String consultas() {
        return "Total de consultas: " + this.consultas.size();
    }
}
