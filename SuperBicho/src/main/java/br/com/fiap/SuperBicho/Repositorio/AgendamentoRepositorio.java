package br.com.fiap.SuperBicho.Repositorio;

import br.com.fiap.SuperBicho.Entidade.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepositorio
        extends JpaRepository<Agendamento, Integer> {

}