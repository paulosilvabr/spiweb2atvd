package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A anotação @Repository avisa ao Spring que esta é uma classe de acesso a dados (DAO),
 * registrando-a como um componente gerenciado.
 */
@Repository
public class MedicoRepository {

    /**
     * @PersistenceContext é usado para injetar o EntityManager.
     * O EntityManager é a interface da JPA responsável por gerenciar as instâncias 
     * e o ciclo de vida das entidades no banco de dados.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Persiste um novo médico no banco de dados.
     */
    public void save(Medico medico) {
        em.persist(medico);
    }

    /**
     * Retorna a lista de todos os médicos através de uma consulta JPQL.
     */
    @SuppressWarnings("unchecked")
    public List<Medico> findAll() {
        Query query = em.createQuery("SELECT m FROM Medico m");
        return query.getResultList();
    }

    /**
     * Busca um médico a partir do seu ID (chave primária).
     */
    public Medico findById(Long id) {
        return em.find(Medico.class, id);
    }

    /**
     * Atualiza os dados de um médico utilizando o método merge.
     */
    public void update(Medico medico) {
        em.merge(medico);
    }

    /**
     * Busca o médico e o remove do banco de dados caso seja encontrado.
     */
    public void delete(Long id) {
        Medico medico = findById(id);
        if (medico != null) {
            em.remove(medico);
        }
    }
}
