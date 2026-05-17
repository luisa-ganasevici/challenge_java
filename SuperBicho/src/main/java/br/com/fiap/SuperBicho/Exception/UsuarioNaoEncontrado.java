package br.com.fiap.SuperBicho.Exception;

public class UsuarioNaoEncontrado extends RuntimeException {

    public UsuarioNaoEncontrado(String mensagem) {

        super(mensagem);
    }
}