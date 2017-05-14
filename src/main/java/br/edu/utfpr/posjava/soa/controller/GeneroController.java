package br.edu.utfpr.posjava.soa.controller;

import br.edu.utfpr.posjava.soa.model.Genero;
import br.edu.utfpr.posjava.soa.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by talyssondecastro on 14/05/17.
 */
@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping("/")
    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genero buscar(@PathVariable Long id) {
        return generoRepository.findOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void inserir(@RequestBody Genero genero) {
        generoRepository.save(genero);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void remover(@PathVariable Long id) {
        generoRepository.delete(id);
    }

}
