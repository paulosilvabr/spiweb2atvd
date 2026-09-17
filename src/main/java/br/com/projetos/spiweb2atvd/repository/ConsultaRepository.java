package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Consulta> findAll() {
        return em.createQuery("SELECT c FROM Consulta c", Consulta.class).getResultList();
    }

    public Consulta findById(Long id) {
        return em.find(Consulta.class, id);
    }

    public void save(Consulta consulta) {
        if (consulta.getId() == null) {
            em.persist(consulta);
        } else {
            em.merge(consulta);
        }
    }

    public void delete(Long id) {
        Consulta consulta = em.find(Consulta.class, id);
        if (consulta != null) {
            em.remove(consulta);
        }
    }
}
