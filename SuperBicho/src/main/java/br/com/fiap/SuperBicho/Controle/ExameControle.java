package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Entidade.Exame;
import br.com.fiap.SuperBicho.Entidade.Historico;
import br.com.fiap.SuperBicho.Entidade.Pet;

import br.com.fiap.SuperBicho.Repositorio.ExameRepositorio;
import br.com.fiap.SuperBicho.Repositorio.HistoricoRepositorio;
import br.com.fiap.SuperBicho.Repositorio.PetRepositorio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exame")

public class ExameControle {

    @Autowired
    private ExameRepositorio repositorio;

    @Autowired
    private HistoricoRepositorio historicoRepositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @GetMapping
    public List<Exame> listar() {

        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Exame buscarPorId(@PathVariable Integer id) {

        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Exame cadastrar(@Valid @RequestBody Exame exame) {

        if (exame.getPet() == null || exame.getPet().getId() == null) {

            throw new RuntimeException("Pet obrigatório");
        }

        Pet petCompleto =
                petRepositorio.findById(exame.getPet().getId())
                        .orElseThrow(() ->
                                new RuntimeException("Pet não encontrado"));

        exame.setPet(petCompleto);

        Exame novoExame = repositorio.save(exame);

        Historico historico = new Historico();

        historico.setDescricao(
                "Exame " + exame.getTipoExame() + " cadastrado"
        );

        historico.setTipo("EXAME");
        historico.setDataRegistro(exame.getDataExame());
        historico.setPet(petCompleto);

        historicoRepositorio.save(historico);

        return novoExame;
    }

    @PutMapping("/{id}")
    public Exame atualizar(@PathVariable Integer id,
                           @Valid @RequestBody Exame exameAtualizado) {

        Exame exame = repositorio.findById(id).orElse(null);

        if (exame != null) {

            exame.setTipoExame(exameAtualizado.getTipoExame());
            exame.setDataExame(exameAtualizado.getDataExame());
            exame.setStatus(exameAtualizado.getStatus());
            exame.setObservacao(exameAtualizado.getObservacao());

            if (exameAtualizado.getPet() != null &&
                    exameAtualizado.getPet().getId() != null) {

                Pet petCompleto =
                        petRepositorio.findById(
                                        exameAtualizado.getPet().getId())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "Pet não encontrado"));

                exame.setPet(petCompleto);
            }

            return repositorio.save(exame);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        repositorio.deleteById(id);
    }
}