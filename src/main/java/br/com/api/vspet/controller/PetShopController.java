package br.com.api.vspet.controller;

import br.com.api.vspet.model.petShop.PetShopInputDTO;
import br.com.api.vspet.model.petShop.PetShopUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.api.vspet.model.petShop.PetShop;
import br.com.api.vspet.model.petShop.PetShopResponseDTO;
import br.com.api.vspet.repository.PetshopRespository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/petshop")
@Slf4j
public class PetShopController {
    
    @Autowired
    private PetshopRespository repository;

    @GetMapping
    public ResponseEntity<Page<PetShopResponseDTO>> index(@PageableDefault(size = 10, sort = {"razaoSocial"})Pageable pageable) {
        log.info("listando todos os petshops");
        var page = repository.findAll(pageable).map(PetShopResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetShopResponseDTO> get(@PathVariable Long id){
        log.info("buscar pet com o id: {}",id);
        var petshop = repository.getReferenceById(id);
        return ResponseEntity.ok(new PetShopResponseDTO(petshop));
    }
 
    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PetShopInputDTO petShop){
        log.info("cadastrando petshop: {}", petShop);
        var petshopSaved = repository.save(new PetShop(petShop));
        var uri = buildUri(petshopSaved);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        log.info("deletando petshop com o id: {}",id);
        var petshop = repository.getReferenceById(id);
        repository.delete(petshop);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetShopResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PetShopUpdateDTO dadosPetShop){
        log.info("atualizando os dados do petshop com o id: {}", id);
        var petShop = repository.getReferenceById(id);
        petShop.update(dadosPetShop);
        return ResponseEntity.ok(new PetShopResponseDTO(petShop));
    }

    public URI buildUri (PetShop petShop){
        var path = "/"+petShop.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .build()
                .toUri();
    }
}
