package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Entidade.Clinica;
import br.com.fiap.SuperBicho.Repositorio.ClinicaRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")

public class ClinicaControle {

    @Autowired
    private ClinicaRepositorio repositorio;

    @GetMapping
    public List<Clinica> listar() {

        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Clinica buscarPorId(@PathVariable Integer id) {

        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Clinica cadastrar(@Valid @RequestBody Clinica clinica) {

        return repositorio.save(clinica);
    }

    @PutMapping("/{id}")
    public Clinica atualizar(@PathVariable Integer id,
                             @Valid @RequestBody Clinica clinicaAtualizada) {

        Clinica clinica = repositorio.findById(id).orElse(null);

        if (clinica != null) {

            clinica.setNome(clinicaAtualizada.getNome());
            clinica.setEndereco(clinicaAtualizada.getEndereco());
            clinica.setTelefone(clinicaAtualizada.getTelefone());

            return repositorio.save(clinica);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        repositorio.deleteById(id);
    }
}