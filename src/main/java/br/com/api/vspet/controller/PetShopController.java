package br.com.api.vspet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.api.vspet.model.*;
@RestController
@RequestMapping("/petshop")
public class PetShopController {
    
    List<PetShop> repository = new ArrayList<>();

    @GetMapping
    public List<PetShop> index() {
        return repository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PetShop>get(@PathVariable Long id){
        var optionalPetShop = buscarPetShopPorId(id);

        if (optionalPetShop.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalPetShop.get());
    }


    @PostMapping
    public ResponseEntity<PetShop>create(@RequestBody PetShop petShop){
        repository.add(petShop);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petShop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>update(@PathVariable Long id, @RequestBody PetShop petShop){

        var optionalPetShop = buscarPetShopPorId(id);

        if (optionalPetShop.isEmpty()) return ResponseEntity.notFound().build();

        var petshopEncontrado = optionalPetShop.get();
        var petshopAtualizado = new PetShop(id, petShop.usename(),petShop.password(),petShop.razaoSocial(), petShop.cnpj(), petShop.telefone(),petShop.cep(),petShop.rua(),petShop.cidade(),petShop.estado(),petShop.numero(), petShop.complemento());
        repository.remove(petshopEncontrado);
        repository.add(petshopAtualizado);

        return ResponseEntity.ok().body(petshopAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object>delete(@PathVariable Long id){
        var optionalPetShop = buscarPetShopPorId(id);

        if (optionalPetShop.isEmpty()) return ResponseEntity.notFound().build();

        repository.remove(optionalPetShop.get());

        return ResponseEntity.noContent().build();
    }
    

    private Optional<PetShop> buscarPetShopPorId(Long id) {
    var optionalPetShop = repository
            .stream()
            .filter(c -> c.id().equals(id))
            .findFirst();
        return optionalPetShop;
    }
}
