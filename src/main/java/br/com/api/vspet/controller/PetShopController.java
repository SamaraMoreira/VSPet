package br.com.api.vspet.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.vspet.model.*;
import br.com.api.vspet.model.petShop.PetShop;
import br.com.api.vspet.model.petShop.PetShopResponseDTO;
import br.com.api.vspet.repository.PetshopRespository;
@RestController
@RequestMapping("/petshop")
public class PetShopController {
    
    @Autowired
    PetshopRespository repository;

    @GetMapping
    public List<PetShop> index() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetShopResponseDTO> get(@PathVariable("id") Long id){
        var petshop = repository.getReferenceById(id);
        return ResponseEntity.ok(new PetShopResponseDTO(petshop));
    }
 
    @PostMapping
    public ResponseEntity<PetShop>create(@RequestBody PetShop petShop){
        repository.save(petShop);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petShop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>delete(@PathVariable Long id){
        var petshop = repository.getReferenceById(id);
        repository.delete(petshop);
        return ResponseEntity.noContent().build();
    }
}
