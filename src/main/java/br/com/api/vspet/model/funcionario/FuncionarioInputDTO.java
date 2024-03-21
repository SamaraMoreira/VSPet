package br.com.api.vspet.model.funcionario;

import br.com.api.vspet.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record FuncionarioInputDTO(
        @NotNull Long id_petshop,
        @NotBlank String nome,
        @CPF String cpf,
        @NotBlank String telefone,
        @Valid EnderecoDTO endereco

) {
}
