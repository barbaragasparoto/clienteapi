package br.com.etec.barbara.clienteapi.repository;

import br.com.etec.barbara.clienteapi.model.Cliente;
import br.com.etec.barbara.clienteapi.repository.cliente.ClienteRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, ClienteRepositoryQuery {

}
