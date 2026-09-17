package br.com.projetos.spiweb2atvd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * A anotação @Entity faz com que esta classe seja tratada como uma tabela pelo JPA no banco de dados.
 */
@Entity
public class Consulta {

    /**
     * @Id e @GeneratedValue mapeiam a chave primária da tabela e delegam 
     * a criação dos IDs sequenciais ao banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @DateTimeFormat é utilizado pelo Spring MVC para converter a String enviada pelo 
     * input datetime-local do HTML diretamente para o objeto LocalDateTime.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime data;
    private double valor;
    private String observacao;

    /**
     * @ManyToOne indica que muitas Consultas estão ligadas a um Paciente.
     * @JoinColumn(name = "paciente_id") diz ao banco qual será o nome da coluna que guardará a chave estrangeira.
     * A Consulta é a "dona" (lado forte) do relacionamento porque é ela quem guarda a chave estrangeira no banco de dados.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * @ManyToOne mapeia muitas Consultas para um Médico.
     * Assim como no paciente, a Consulta é o lado forte do relacionamento que mantém a referência (medico_id).
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    public Consulta() {}

    public Consulta(Long id, LocalDateTime data, double valor, String observacao, Paciente paciente, Medico medico) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    
    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
    
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public String dados() {
        return "Consulta marcada para " + this.data + " | Valor: R$ " + this.valor;
    }
}
