package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Entidade.Historico;
import br.com.fiap.SuperBicho.Entidade.Pet;

import br.com.fiap.SuperBicho.Repositorio.HistoricoRepositorio;
import br.com.fiap.SuperBicho.Repositorio.PetRepositorio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")

public class HistoricoControle {

    @Autowired
    private HistoricoRepositorio repositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @GetMapping
    public List<Historico> listar() {

        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Historico buscarPorId(@PathVariable Integer id) {

        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Historico cadastrar(@Valid @RequestBody Historico historico) {

        if (historico.getPet() == null ||
                historico.getPet().getId() == null) {

            throw new RuntimeException("Pet obrigatório");
        }

        Pet petCompleto =
                petRepositorio.findById(historico.getPet().getId())
                        .orElseThrow(() ->
                                new RuntimeException("pet nao foi encontrado"));

        historico.setPet(petCompleto);

        return repositorio.save(historico);
    }

    @PutMapping("/{id}")
    public Historico atualizar(@PathVariable Integer id,
                               @Valid @RequestBody Historico historicoAtualizado) {

        Historico historico = repositorio.findById(id).orElse(null);

        if (historico != null) {

            historico.setDescricao(historicoAtualizado.getDescricao());
            historico.setTipo(historicoAtualizado.getTipo());
            historico.setDataRegistro(historicoAtualizado.getDataRegistro());

            if (historicoAtualizado.getPet() != null &&
                    historicoAtualizado.getPet().getId() != null) {

                Pet petCompleto =
                        petRepositorio.findById(
                                        historicoAtualizado.getPet().getId())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "pet nao foi encontrado"));

                historico.setPet(petCompleto);
            }

            return repositorio.save(historico);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        repositorio.deleteById(id);
    }
}