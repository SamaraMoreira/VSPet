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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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
@Tag(name = "pets", description = "Endpoint relacionado aos dados de pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetshopRespository petshopRespository;

    @GetMapping
    @Operation(summary = "Lista todos os pets", description = "Endpoint retorna de forma paginada todos os pets cadastrados, por padrão cada pagina contém 10 pets ordenados por nome, mas esses dados de paginação podem ser personalizados.")
    public ResponseEntity<Page<PetResponseDTO>> index(
            @PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        log.info("listando todos os pets");

        var page = repository.findAll(pageable).map(PetResponseDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibe os detalhes do pet de id equivalente", description = "Endpoint retorna um objeto contendo os dados não sensíveis de um pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "pet não encontrado"),
            @ApiResponse(responseCode = "200", description = "pet detalhado com sucesso!")
    })
    public ResponseEntity<PetResponseDTO> get(@PathVariable Long id){
        log.info("buscar pet com o id: {}",id);

        Pet pet = repository.getReferenceById(id);

        return ResponseEntity.ok(new PetResponseDTO(pet));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Exibe os pets que correspondem ao cpf de seus donos", description = "Endpoint retorna uma lista de pets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "pets não encontrado"),
            @ApiResponse(responseCode = "200", description = "pets detalhados com sucesso!")
    })
    public ResponseEntity<List<PetResponseDTO>> getByCpf(@PathVariable String cpf){
        log.info("buscar pet com o cpf: {}",cpf);

        List<PetResponseDTO> pets = repository.findByCpfResponsavel(cpf).stream().map(PetResponseDTO::new).toList();

        return ResponseEntity.ok(pets);
    }

    @PostMapping
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do pet"),
            @ApiResponse(responseCode = "201", description = "pet cadastrado com sucesso!")
    })
    @Operation(summary = "Cadastra um novo pet no sistema", description = "Endpoint recebe no corpo da requisição um objeto contendo os dados necessários para realizar o cadastro de um novo pet")
    public ResponseEntity create(@ParameterObject @RequestBody @Valid PetInputDto petInputDto){
        log.info("cadastrando pet: {}", petInputDto);

        PetShop petshop = petshopRespository.getReferenceById(petInputDto.id_petshop());
        Pet petSaved = repository.save(new Pet(petshop,petInputDto));

        var uri = buildUri(petSaved);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "pet não encontrado"),
            @ApiResponse(responseCode = "204", description = "pet removido com sucesso!")
    })
    @Operation(summary = "Deleta um pet do sistema", description = "Endpoint recebe no path o id do pet a ser deletado")
    public ResponseEntity delete(@PathVariable Long id){
        log.info("deletando pet com o id: {}",id);

        Pet pet = repository.getReferenceById(id);
        repository.delete(pet);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do pet"),
            @ApiResponse(responseCode = "201", description = "pet atualizado com sucesso!")
    })
    @Operation(summary = "Atualiza um pet no sistema", description = "Endpoint recebe no corpo da requisição um objeto contendo os dados necessários para atualizar os dados de um pet")

    public ResponseEntity<PetResponseDTO> update(@PathVariable Long id, @ParameterObject @RequestBody @Valid PetUpdateDTO petUpdateDTO){
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
