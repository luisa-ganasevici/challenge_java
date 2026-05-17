package br.com.fiap.SuperBicho.Dto;

public class LoginRespostaDTO {

    private Integer id;
    private String nome;
    private String email;
    private String tipo;

    public LoginRespostaDTO() {
    }

    public LoginRespostaDTO(Integer id,
                            String nome,
                            String email,
                            String tipo) {

        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
//so utilizar se for ter index