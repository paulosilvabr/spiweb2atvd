package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Utilizando Spring Data JPA.
 * A interface JpaRepository já nos dá de "brinde" métodos como save(), findAll(), findById(), deleteById().
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Não é necessário escrever mais nada aqui dentro! O Spring faz a magia.
}