package br.com.proposta.microservico.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/usuarioLogado")
    public String usuariLogado() {
        return "Usuario Logado";
    }
}
