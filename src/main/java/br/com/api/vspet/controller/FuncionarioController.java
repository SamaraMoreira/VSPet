package br.com.api.vspet.controller;

import br.com.api.vspet.model.funcionario.Funcionario;
import br.com.api.vspet.model.funcionario.FuncionarioInputDTO;
import br.com.api.vspet.model.funcionario.FuncionarioResponseDTO;
import br.com.api.vspet.model.funcionario.FuncionarioUpdateDTO;
import br.com.api.vspet.repository.FuncionarioRepository;
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

@RestController
@RequestMapping("/funcionario")
@Slf4j
@Tag(name = "funcionários", description = "Endpoint relacionado aos dados de funcionários")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PetshopRespository petshopRespository;

    @GetMapping
    @Operation(summary = "Lista todos os funcionários", description = "Endpoint retorna de forma paginada todos os funcionários cadastrados, por padrão cada pagina contém 10 funcionários ordenados por nome, mas esses dados de paginação podem ser personalizados.")
    public ResponseEntity<Page<FuncionarioResponseDTO>> index(@ParameterObject @PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("listando todos os funcionarios");
        var page = repository.findAll(pageable).map(FuncionarioResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibe os detalhes do funcionário de id equivalente", description = "Endpoint retorna um objeto contendo os dados não sensíveis de um funcionário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "200", description = "Funcionário detalhado com sucesso!")
    })
    public ResponseEntity<FuncionarioResponseDTO> get(@PathVariable Long id){
        log.info("buscar funcionario com o id: {}",id);
        var funcionario = repository.getReferenceById(id);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionario));
    }

    @PostMapping
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do funcionário"),
            @ApiResponse(responseCode = "201", description = "Funcionário cadastrado com sucesso!")
    })
    @Operation(summary = "Cadastra um novo funcionário no sistema", description = "Endpoint recebe no corpo da requisição um objeto contendo os dados necessários para realizar o cadastro de um novo funcionário")
    public ResponseEntity create(@ParameterObject @RequestBody @Valid FuncionarioInputDTO funcionarioInput){
        log.info("cadastrando funcionario: {}", funcionarioInput);
        var petshop = petshopRespository.getReferenceById(funcionarioInput.id_petshop());
        var funcionarioSaved = repository.save(new Funcionario(petshop,funcionarioInput));
        var uri = buildUri(funcionarioSaved);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
            @ApiResponse(responseCode = "204", description = "Funcionário removido com sucesso!")
    })
    @Operation(summary = "Deleta um funcionário do sistema", description = "Endpoint recebe no path o id do funcionário a ser deletado")
    public ResponseEntity delete(@PathVariable Long id){
        log.info("deletando funcionario com o id: {}",id);
        var funcionario = repository.getReferenceById(id);
        repository.delete(funcionario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do funcionário"),
            @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso!")
    })
    @Operation(summary = "Atualiza um novo funcionário do sistema", description = "Endpoint recebe no corpo da requisição um objeto contendo os dados necessários para atualizar os dados de um funcionário")
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable Long id, @ParameterObject @RequestBody @Valid FuncionarioUpdateDTO dadosFuncionario){
        log.info("atualizando os dados do funcionario com o id: {}", id);
        var funcionario = repository.getReferenceById(id);
        funcionario.update(dadosFuncionario);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionario));
    }

    public URI buildUri (Funcionario funcionario){
        var path = "/"+funcionario.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .build()
                .toUri();
    }
}
