package br.com.fiap.SuperBicho.Servico;

import br.com.fiap.SuperBicho.Dto.LoginDTO;
import br.com.fiap.SuperBicho.Dto.LoginRespostaDTO;

import br.com.fiap.SuperBicho.Entidade.Tutor;
import br.com.fiap.SuperBicho.Exception.UsuarioNaoEncontrado;
import br.com.fiap.SuperBicho.Repositorio.TutorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class Login {

    @Autowired
    private TutorRepositorio repositorio;

    public LoginRespostaDTO autenticar(LoginDTO loginDTO) {

        Tutor tutor = repositorio.findByEmail(loginDTO.getEmail());

        if (tutor != null &&
                tutor.getSenha().equals(loginDTO.getSenha())) {

            return new LoginRespostaDTO(
                    tutor.getId(),
                    tutor.getNome(),
                    tutor.getEmail(),
                    tutor.getTipo()
            );
        }

        throw new UsuarioNaoEncontrado(
                "email ou senha incorretos"
        );
    }
}