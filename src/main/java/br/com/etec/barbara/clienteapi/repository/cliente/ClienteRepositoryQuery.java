package br.com.etec.barbara.clienteapi.repository.cliente;

import br.com.etec.barbara.clienteapi.model.Cliente;
import br.com.etec.barbara.clienteapi.repository.filter.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClienteRepositoryQuery {

    public Page<Cliente> Filtrar(ClienteFilter clientefilter, Pageable pageable);

}
