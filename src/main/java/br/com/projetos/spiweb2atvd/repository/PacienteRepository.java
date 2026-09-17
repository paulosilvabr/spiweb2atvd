package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A anotação @Repository registra esta classe no container do Spring, 
 * permitindo que ela seja injetada em outras classes e trate exceções de banco de dados.
 */
@Repository
public class PacienteRepository {

    /**
     * O @PersistenceContext injeta o EntityManager, que é a interface central da JPA.
     * É ele o responsável por gerenciar o ciclo de vida das entidades, efetuando as operações 
     * de persistência diretamente no banco de dados.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Salva um novo registro no banco. O método persist torna a entidade gerenciada.
     */
    public void save(Paciente paciente) {
        em.persist(paciente);
    }

    /**
     * Busca todos os registros usando JPQL (Java Persistence Query Language), 
     * fazendo a consulta na classe Paciente e não na tabela física.
     */
    @SuppressWarnings("unchecked")
    public List<Paciente> findAll() {
        Query query = em.createQuery("SELECT p FROM Paciente p");
        return query.getResultList();
    }

    /**
     * Busca uma entidade específica pela sua chave primária.
     */
    public Paciente findById(Long id) {
        return em.find(Paciente.class, id);
    }

    /**
     * Atualiza uma entidade existente. O merge sincroniza o objeto modificado 
     * com o registro no banco de dados.
     */
    public void update(Paciente paciente) {
        em.merge(paciente);
    }

    /**
     * Deleta a entidade do banco. Primeiro busca a entidade, e se existir, 
     * usa o remove para excluí-la.
     */
    public void delete(Long id) {
        Paciente paciente = findById(id);
        if (paciente != null) {
            em.remove(paciente);
        }
    }
}
