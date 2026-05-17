package br.com.fiap.SuperBicho.Entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SB_TB_EXAME")

public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "Tipo do exame é obrigatório")
    private String tipoExame;

    @NotBlank(message = "Data do exame é obrigatória")
    private String dataExame;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    @NotBlank(message = "Observação é obrigatória")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pet_id")

    private Pet pet;

    public Exame() {
    }

    public Exame(Integer id, String tipoExame,
                 String dataExame, String status,
                 String observacao, Pet pet) {

        this.id = id;
        this.tipoExame = tipoExame;
        this.dataExame = dataExame;
        this.status = status;
        this.observacao = observacao;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoExame() {
        return tipoExame;
    }

    public void setTipoExame(String tipoExame) {
        this.tipoExame = tipoExame;
    }

    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}