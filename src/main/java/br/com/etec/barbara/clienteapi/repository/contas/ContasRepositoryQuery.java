package br.com.etec.barbara.clienteapi.repository.contas;

import br.com.etec.barbara.clienteapi.repository.Dtos.ContasDto;
import br.com.etec.barbara.clienteapi.repository.filter.ContasFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContasRepositoryQuery {

    public Page<ContasDto> filtrar(ContasFilter contasfilter, Pageable pageable);


}
