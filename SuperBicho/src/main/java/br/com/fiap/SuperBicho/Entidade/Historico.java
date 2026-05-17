package br.com.fiap.SuperBicho.Entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "SB_TB_HISTORICO")

public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "Data de registro é obrigatória")
    private String dataRegistro;

    @ManyToOne
    @JoinColumn(name = "pet_id")

    private Pet pet;

    public Historico() {
    }

    public Historico(Integer id, String descricao,
                     String tipo, String dataRegistro,
                     Pet pet) {

        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataRegistro = dataRegistro;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}