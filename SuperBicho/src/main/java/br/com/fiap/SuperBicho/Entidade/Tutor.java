package br.com.fiap.SuperBicho.Entidade;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "SB_TB_TUTOR")

public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank(message = "nome obrigatorio")
    private String nome;

    @Email(message = "email")
    @NotBlank(message = "é necessario digitar o email")
    @Column(unique = true)

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    @NotBlank(message = "é necessario digitar a senha")
    @Size(min = 4, message = "a senha deve ter 4 caracteres ou mais")

    private String senha;

    @NotBlank(message = "tipo obrigatório")
    private String tipo;

    public Tutor() {
    }

    public Tutor(Integer id, String nome,
                 String email, String senha,
                 String tipo) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}