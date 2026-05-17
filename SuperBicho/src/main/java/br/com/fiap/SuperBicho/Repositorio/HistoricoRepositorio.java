package br.com.fiap.SuperBicho.Repositorio;

import br.com.fiap.SuperBicho.Entidade.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepositorio extends JpaRepository<Historico, Integer> {

}