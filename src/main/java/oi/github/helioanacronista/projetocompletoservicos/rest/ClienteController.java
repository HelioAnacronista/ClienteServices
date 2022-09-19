package oi.github.helioanacronista.projetocompletoservicos.rest;

import oi.github.helioanacronista.projetocompletoservicos.model.entitiy.Cliente;
import oi.github.helioanacronista.projetocompletoservicos.model.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository repositoryCliente;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return repositoryCliente.save(cliente);
    }

    @GetMapping("{id}")
    public Cliente findById(@PathVariable Integer id) {
        return repositoryCliente
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repositoryCliente.findById(id)
                .map( cliente -> {
                   repositoryCliente.delete(cliente);
                   return cliente;
                } )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id, @RequestBody Cliente cliente) {
        repositoryCliente.findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repositoryCliente.save(cliente);
                    return clienteExistente;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find (Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);
        return repositoryCliente.findAll(example);
    }
}
