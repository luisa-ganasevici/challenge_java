package br.com.fiap.SuperBicho.Repositorio;

import br.com.fiap.SuperBicho.Entidade.Pet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepositorio
        extends JpaRepository<Pet, Integer> {

    @Query(
            value = "SELECT p FROM Pet p JOIN FETCH p.tutor",
            countQuery = "SELECT COUNT(p) FROM Pet p"
    )
    Page<Pet> findAllWithTutor(Pageable pageable);

    List<Pet> findByTutorId(Integer tutorId);

    List<Pet> findByEspecie(String especie);
}