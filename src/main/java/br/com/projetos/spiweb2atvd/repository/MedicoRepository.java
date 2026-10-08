package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A anotação @Repository avisa ao Spring que esta é uma classe de acesso a dados (DAO),
 * registrando-a como um componente gerenciado.
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
