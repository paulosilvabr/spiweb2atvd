package br.com.projetos.spiweb2atvd.repository;

import br.com.projetos.spiweb2atvd.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
