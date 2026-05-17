package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Dto.PetDTO;
import br.com.fiap.SuperBicho.Entidade.Pet;
import br.com.fiap.SuperBicho.Entidade.Tutor;
import br.com.fiap.SuperBicho.Repositorio.PetRepositorio;
import br.com.fiap.SuperBicho.Repositorio.TutorRepositorio;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetControle {

    private final PetRepositorio petRepositorio;
    private final TutorRepositorio tutorRepositorio;

    public PetControle(PetRepositorio petRepositorio, TutorRepositorio tutorRepositorio) {
        this.petRepositorio = petRepositorio;
        this.tutorRepositorio = tutorRepositorio;
    }

    @GetMapping
    public List<Pet> listar() {
        return petRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Pet buscar(@PathVariable Integer id) {
        return petRepositorio.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));
    }

    @PostMapping
    public Pet cadastrar(@Valid @RequestBody PetDTO dto) {

        Tutor tutor = tutorRepositorio.findById(dto.getTutorId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor não encontrado"));

        Pet pet = new Pet();
        pet.setNome(dto.getNome());
        pet.setEspecie(dto.getEspecie());
        pet.setIdade(dto.getIdade());
        pet.setPeso(dto.getPeso());
        pet.setTutor(tutor);

        return petRepositorio.save(pet);
    }

    @PutMapping("/{id}")
    public Pet atualizar(@PathVariable Integer id, @Valid @RequestBody PetDTO dto) {

        Pet pet = petRepositorio.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));

        Tutor tutor = tutorRepositorio.findById(dto.getTutorId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Tutor não encontrado"));

        pet.setNome(dto.getNome());
        pet.setEspecie(dto.getEspecie());
        pet.setIdade(dto.getIdade());
        pet.setPeso(dto.getPeso());
        pet.setTutor(tutor);

        return petRepositorio.save(pet);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

        if (!petRepositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado");
        }

        petRepositorio.deleteById(id);
    }
}