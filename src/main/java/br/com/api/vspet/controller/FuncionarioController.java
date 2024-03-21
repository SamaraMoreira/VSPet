package br.com.api.vspet.controller;

import br.com.api.vspet.model.funcionario.Funcionario;
import br.com.api.vspet.model.funcionario.FuncionarioInputDTO;
import br.com.api.vspet.model.funcionario.FuncionarioResponseDTO;
import br.com.api.vspet.model.funcionario.FuncionarioUpdateDTO;
import br.com.api.vspet.repository.FuncionarioRepository;
import br.com.api.vspet.repository.PetshopRespository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/funcionario")
@Slf4j
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PetshopRespository petshopRespository;

    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> index(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        log.info("listando todos os funcionarios");
        var page = repository.findAll(pageable).map(FuncionarioResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> get(@PathVariable Long id){
        log.info("buscar funcionario com o id: {}",id);
        var funcionario = repository.getReferenceById(id);
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid FuncionarioInputDTO funcionarioInput){
        log.info("cadastrando funcionario: {}", funcionarioInput);
        var petshop = petshopRespository.getReferenceById(funcionarioInput.id_petshop());
        var funcionarioSaved = repository.save(new Funcionario(petshop,funcionarioInput));
        var uri = buildUri(funcionarioSaved);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        log.info("deletando funcionario com o id: {}",id);
        var funcionario = repository.getReferenceById(id);
        repository.delete(funcionario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FuncionarioResponseDTO> update(@PathVariable Long id, @RequestBody @Valid FuncionarioUpdateDTO dadosFuncionario){
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
