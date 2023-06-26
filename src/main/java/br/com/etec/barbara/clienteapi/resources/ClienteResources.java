package br.com.etec.barbara.clienteapi.resources;

import br.com.etec.barbara.clienteapi.model.Cliente;
import br.com.etec.barbara.clienteapi.repository.ClienteRepository;
import br.com.etec.barbara.clienteapi.repository.filter.ClienteFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteResources {

    @Autowired
    private ClienteRepository clienterepository;

    @GetMapping()
    public Page<Cliente> pesquisar(ClienteFilter clientefilter, Pageable pageable){
        return clienterepository.Filtrar(clientefilter, pageable);
    }

    @GetMapping("/todos")
    public List<Cliente> listarTodosClientes(){
        return clienterepository.findAll();
    }



}
