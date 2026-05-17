package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Entidade.Agendamento;
import br.com.fiap.SuperBicho.Entidade.Clinica;
import br.com.fiap.SuperBicho.Entidade.Historico;
import br.com.fiap.SuperBicho.Entidade.Pet;
import br.com.fiap.SuperBicho.Repositorio.AgendamentoRepositorio;
import br.com.fiap.SuperBicho.Repositorio.ClinicaRepositorio;
import br.com.fiap.SuperBicho.Repositorio.HistoricoRepositorio;
import br.com.fiap.SuperBicho.Repositorio.PetRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/agendamento")

public class AgendamentoControle {

    @Autowired
    private AgendamentoRepositorio repositorio;

    @Autowired
    private HistoricoRepositorio historicoRepositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @Autowired
    private ClinicaRepositorio clinicaRepositorio;

    @GetMapping
    public List<Agendamento> listar() {

        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Agendamento buscarPorId(@PathVariable Integer id) {

        return repositorio.findById(id).orElse(null);
    }

    @PostMapping
    public Agendamento cadastrar(@Valid @RequestBody Agendamento agendamento) {

        if (agendamento.getPet() == null ||
                agendamento.getPet().getId() == null) {

            throw new RuntimeException("obrigatorio colocar um pet");
        }

        if (agendamento.getClinica() == null ||
                agendamento.getClinica().getId() == null) {

            throw new RuntimeException("obrigatorio colocar uma clinica");
        }

        Pet petCompleto =
                petRepositorio.findById(
                                agendamento.getPet().getId())
                        .orElseThrow(() ->
                                new RuntimeException("o pet nao foi encontrado"));

        Clinica clinicaCompleta =
                clinicaRepositorio.findById(
                                agendamento.getClinica().getId())
                        .orElseThrow(() ->
                                new RuntimeException("clinica nao foi encontrada"));

        agendamento.setPet(petCompleto);
        agendamento.setClinica(clinicaCompleta);

        Agendamento novoAgendamento = repositorio.save(agendamento);

        Historico historico = new Historico();

        historico.setDescricao("o agendamento foi realizado");
        historico.setTipo("AGENDAMENTO");
        historico.setDataRegistro(agendamento.getData());
        historico.setPet(petCompleto);

        historicoRepositorio.save(historico);

        return novoAgendamento;
    }

    @PutMapping("/{id}")
    public Agendamento atualizar(@PathVariable Integer id,
                                 @Valid @RequestBody Agendamento agendamentoAtualizado) {

        Agendamento agendamento = repositorio.findById(id).orElse(null);

        if (agendamento != null) {

            agendamento.setData(agendamentoAtualizado.getData());
            agendamento.setHorario(agendamentoAtualizado.getHorario());
            agendamento.setStatus(agendamentoAtualizado.getStatus());

            if (agendamentoAtualizado.getPet() != null &&
                    agendamentoAtualizado.getPet().getId() != null) {

                Pet petCompleto =
                        petRepositorio.findById(
                                        agendamentoAtualizado.getPet().getId())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "pet nao foi encontrado"));

                agendamento.setPet(petCompleto);
            }

            if (agendamentoAtualizado.getClinica() != null &&
                    agendamentoAtualizado.getClinica().getId() != null) {

                Clinica clinicaCompleta =
                        clinicaRepositorio.findById(
                                        agendamentoAtualizado.getClinica().getId())
                                .orElseThrow(() ->
                                        new RuntimeException(
                                                "a clínica nao foi encontrada"));

                agendamento.setClinica(clinicaCompleta);
            }

            return repositorio.save(agendamento);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        repositorio.deleteById(id);
    }
}