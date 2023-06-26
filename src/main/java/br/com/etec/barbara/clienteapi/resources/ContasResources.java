package br.com.etec.barbara.clienteapi.resources;

import br.com.etec.barbara.clienteapi.model.Contas;
import br.com.etec.barbara.clienteapi.repository.ContasRepository;
import br.com.etec.barbara.clienteapi.repository.Dtos.ContasDto;
import br.com.etec.barbara.clienteapi.repository.filter.ContasFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContasResources {

    @Autowired
    private ContasRepository contasrepository;

    @GetMapping()
    public Page<ContasDto> pesquisar(ContasFilter contasfilter, Pageable pageable){
        return contasrepository.filtrar(contasfilter, pageable);
    }

    @GetMapping("/todos")
    public List<Contas> listarTodosContas(){
        return contasrepository.findAll();
    }

}
