package br.com.api.vspet.controller;

import br.com.api.vspet.model.funcionario.Funcionario;
import br.com.api.vspet.model.funcionario.FuncionarioInputDTO;
import br.com.api.vspet.model.funcionario.FuncionarioResponseDTO;
import br.com.api.vspet.model.funcionario.FuncionarioUpdateDTO;
import br.com.api.vspet.model.pet.Pet;
import br.com.api.vspet.model.pet.PetInputDto;
import br.com.api.vspet.model.pet.PetResponseDTO;
import br.com.api.vspet.model.pet.PetUpdateDTO;
import br.com.api.vspet.model.petShop.PetShop;
import br.com.api.vspet.repository.PetRepository;
import br.com.api.vspet.repository.PetshopRespository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pets")
@Slf4j
@CrossOrigin("*")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetshopRespository petshopRespository;

    @GetMapping
    public ResponseEntity<Page<PetResponseDTO>> index(
            @PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        log.info("listando todos os pets");

        var page = repository.findAll(pageable).map(PetResponseDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> get(@PathVariable Long id){
        log.info("buscar pet com o id: {}",id);

        Pet pet = repository.getReferenceById(id);

        return ResponseEntity.ok(new PetResponseDTO(pet));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<PetResponseDTO>> getByCpf(@PathVariable String cpf){
        log.info("buscar pet com o cpf: {}",cpf);

        List<PetResponseDTO> pets = repository.findByCpfResponsavel(cpf).stream().map(PetResponseDTO::new).toList();

        return ResponseEntity.ok(pets);
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PetInputDto petInputDto){
        log.info("cadastrando pet: {}", petInputDto);

        PetShop petshop = petshopRespository.getReferenceById(petInputDto.id_petshop());
        Pet petSaved = repository.save(new Pet(petshop,petInputDto));

        var uri = buildUri(petSaved);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        log.info("deletando pet com o id: {}",id);

        Pet pet = repository.getReferenceById(id);
        repository.delete(pet);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetResponseDTO> update(@PathVariable Long id, @RequestBody @Valid PetUpdateDTO petUpdateDTO){
        log.info("atualizando os dados do pet com o id: {}", id);

        Pet pet = repository.getReferenceById(id);
        pet.update(petUpdateDTO);

        return ResponseEntity.ok(new PetResponseDTO(pet));
    }

    public URI buildUri (Pet pet){
        var path = "/"+pet.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .build()
                .toUri();
    }
}
