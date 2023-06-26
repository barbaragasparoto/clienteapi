package br.com.etec.barbara.clienteapi.repository;

import br.com.etec.barbara.clienteapi.model.Contas;
import br.com.etec.barbara.clienteapi.repository.contas.ContasRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContasRepository extends JpaRepository<Contas, Integer>, ContasRepositoryQuery {
}
