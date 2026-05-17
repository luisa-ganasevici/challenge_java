package br.com.fiap.SuperBicho.Controle;

import br.com.fiap.SuperBicho.Dto.LoginDTO;
import br.com.fiap.SuperBicho.Dto.LoginRespostaDTO;

import br.com.fiap.SuperBicho.Servico.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")

public class LoginControle {

    @Autowired
    private Login loginServico;

    @PostMapping
    public LoginRespostaDTO login(
            @RequestBody LoginDTO loginDTO) {

        return loginServico.autenticar(loginDTO);
    }
}