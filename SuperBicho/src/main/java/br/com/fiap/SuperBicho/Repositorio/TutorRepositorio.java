package br.com.fiap.SuperBicho.Repositorio;

import br.com.fiap.SuperBicho.Entidade.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepositorio extends JpaRepository<Tutor, Integer> {

    Tutor findByEmail(String email);

}