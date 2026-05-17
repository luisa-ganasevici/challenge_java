package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Entidade.Tutor;
import br.com.fiap.SuperBicho.Repositorio.TutorRepositorio;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")

public class TutorControle {

    @Autowired
    private TutorRepositorio repositorio;

    @GetMapping
    public List<Tutor> listar() {

        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Tutor buscarPorId(@PathVariable Integer id) {

        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Tutor cadastrar(@Valid @RequestBody Tutor tutor) {

        return repositorio.save(tutor);
    }

    @PutMapping("/{id}")
    public Tutor atualizar(@PathVariable Integer id,
                           @Valid @RequestBody Tutor tutorAtualizado) {

        Tutor tutor = repositorio.findById(id).orElse(null);

        if (tutor != null) {

            tutor.setNome(tutorAtualizado.getNome());
            tutor.setEmail(tutorAtualizado.getEmail());
            tutor.setSenha(tutorAtualizado.getSenha());
            tutor.setTipo(tutorAtualizado.getTipo());

            return repositorio.save(tutor);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        repositorio.deleteById(id);
    }
}