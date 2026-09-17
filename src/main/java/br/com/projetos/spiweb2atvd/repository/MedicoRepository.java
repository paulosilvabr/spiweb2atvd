package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Medico> findAll() {
        return em.createQuery("SELECT m FROM Medico m", Medico.class).getResultList();
    }

    public Medico findById(Long id) {
        return em.find(Medico.class, id);
    }

    public void save(Medico medico) {
        if (medico.getId() == null) {
            em.persist(medico);
        } else {
            em.merge(medico);
        }
    }

    public void delete(Long id) {
        Medico medico = em.find(Medico.class, id);
        if (medico != null) {
            em.remove(medico);
        }
    }
}
