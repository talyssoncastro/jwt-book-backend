package br.edu.utfpr.posjava.soa.controller;

import br.edu.utfpr.posjava.soa.model.Editora;
import br.edu.utfpr.posjava.soa.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by talyssondecastro on 14/05/17.
 */
@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping("/")
    public List<Editora> listar() {
        return editoraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Editora buscar(@PathVariable Long id) {
        return editoraRepository.findOne(id);
    }

    @PostMapping("/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void inserir(@RequestBody Editora editora) {
        editoraRepository.save(editora);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void remover(@PathVariable Long id) {
        editoraRepository.delete(id);
    }

}
