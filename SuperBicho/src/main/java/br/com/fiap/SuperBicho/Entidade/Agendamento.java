package br.com.fiap.SuperBicho.Entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SB_TB_AGENDAMENTO")

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "Data é obrigatória")
    private String data;

    @NotBlank(message = "Horário é obrigatório")
    private String horario;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    @ManyToOne
    @JoinColumn(name = "pet_id")

    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "clinica_id")

    private Clinica clinica;

    public Agendamento() {
    }

    public Agendamento(Integer id, String data,
                       String horario, String status,
                       Pet pet, Clinica clinica) {

        this.id = id;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.pet = pet;
        this.clinica = clinica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
}