package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A anotação @Repository transforma a classe em um bean gerenciado pelo Spring
 * voltado para o acesso e persistência de dados.
 */
@Repository
public class ConsultaRepository {

    /**
     * Injeção do EntityManager da JPA via @PersistenceContext.
     * Ele atua como o gestor do ciclo de vida das entidades (Consulta), conectando o 
     * modelo orientado a objetos com as tabelas relacionais.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Salva uma nova consulta no banco de dados.
     */
    public void save(Consulta consulta) {
        em.persist(consulta);
    }

    /**
     * Retorna todas as consultas usando a linguagem JPQL (focada na entidade Consulta).
     */
    @SuppressWarnings("unchecked")
    public List<Consulta> findAll() {
        Query query = em.createQuery("SELECT c FROM Consulta c");
        return query.getResultList();
    }

    /**
     * Localiza uma consulta pela chave primária (ID).
     */
    public Consulta findById(Long id) {
        return em.find(Consulta.class, id);
    }

    /**
     * Sincroniza e atualiza os dados da consulta que sofreu alterações.
     */
    public void update(Consulta consulta) {
        em.merge(consulta);
    }

    /**
     * Localiza a consulta e a remove definitivamente do banco.
     */
    public void delete(Long id) {
        Consulta consulta = findById(id);
        if (consulta != null) {
            em.remove(consulta);
        }
    }
}
