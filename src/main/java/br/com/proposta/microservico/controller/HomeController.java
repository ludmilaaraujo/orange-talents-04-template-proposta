package br.com.proposta.microservico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class HomeController {

    @GetMapping(value = "/usuarioLogado")
    public String usuariLogado() {
        return "Usuario Logado";
    }
}
