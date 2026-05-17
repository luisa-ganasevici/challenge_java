package br.com.fiap.SuperBicho.Entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "SB_TB_PET")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "nome do pet é obrigatório")
    private String nome;

    @NotBlank(message = "espécie é obrigatória")
    private String especie;

    @NotNull(message = "idade é obrigatória")
    @Min(value = 0, message = "idade inválida")
    private Integer idade;

    @NotNull(message = "peso é obrigatório")
    @Positive(message = "peso deve ser positivo")
    private Double peso;

    @ManyToOne
    @JoinColumn(name = "tutor_id")

    private Tutor tutor;

    public Pet() {
    }

    public Pet(Integer id, String nome, String especie,
               Integer idade, Double peso, Tutor tutor) {

        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.peso = peso;
        this.tutor = tutor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}