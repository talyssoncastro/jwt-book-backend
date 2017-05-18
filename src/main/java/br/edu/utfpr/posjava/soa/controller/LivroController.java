package br.edu.utfpr.posjava.soa.controller;

import br.edu.utfpr.posjava.soa.model.Livro;
import br.edu.utfpr.posjava.soa.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by talyssondecastro on 14/05/17.
 */
@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/")
    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return livroRepository.findOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void inserir(@RequestBody Livro livro) {
        livroRepository.save(livro);
    }

    @PutMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public void alterar(@RequestBody Livro livro) {
        livroRepository.save(livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void remover(@PathVariable Long id) {
        livroRepository.delete(id);
    }

}
