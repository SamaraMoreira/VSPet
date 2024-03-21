package br.com.api.vspet.model.funcionario;

import br.com.api.vspet.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioUpdateDTO(
        @NotBlank String telefone,
        @Valid EnderecoDTO endereco
) {
}
