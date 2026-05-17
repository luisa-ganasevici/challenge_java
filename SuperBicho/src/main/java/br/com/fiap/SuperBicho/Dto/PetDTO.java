package br.com.fiap.SuperBicho.Dto;

import jakarta.validation.constraints.*;

public class PetDTO {

    @NotBlank(message = "Nome do pet é obrigatório")
    private String nome;

    @NotBlank(message = "Espécie é obrigatória")
    private String especie;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 0, message = "Idade inválida")
    private Integer idade;

    @NotNull(message = "Peso é obrigatório")
    @Positive(message = "Peso deve ser positivo")
    private Double peso;

    @NotNull(message = "Tutor é obrigatório")
    private Integer tutorId;

    public PetDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Integer getTutorId() { return tutorId; }
    public void setTutorId(Integer tutorId) { this.tutorId = tutorId; }
}