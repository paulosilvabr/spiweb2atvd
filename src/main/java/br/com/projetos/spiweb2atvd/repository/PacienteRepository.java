package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Paciente> findAll() {
        return em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
    }

    public Paciente findById(Long id) {
        return em.find(Paciente.class, id);
    }

    public void save(Paciente paciente) {
        if (paciente.getId() == null) {
            em.persist(paciente);
        } else {
            em.merge(paciente);
        }
    }

    public void delete(Long id) {
        Paciente paciente = em.find(Paciente.class, id);
        if (paciente != null) {
            em.remove(paciente);
        }
    }
}
