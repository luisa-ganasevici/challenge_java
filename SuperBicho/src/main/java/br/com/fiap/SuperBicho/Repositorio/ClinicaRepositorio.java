package br.com.fiap.SuperBicho.Repositorio;

import br.com.fiap.SuperBicho.Entidade.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicaRepositorio extends JpaRepository<Clinica, Integer> {

}