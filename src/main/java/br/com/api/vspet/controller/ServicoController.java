package br.com.api.vspet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @GetMapping
    public void index(){

    }

    @GetMapping("/agendados")
    public void agendados(){

    }
    @GetMapping("/{id}")
    public void get(){

    }

    @PostMapping("/agendar")
    public void agendar(){

    }

    @PostMapping
    public void create(){

    }

    @PutMapping("/{id}")
    public void update(){

    }

    @DeleteMapping("/{id}")
    public void delete(){

    }
}
