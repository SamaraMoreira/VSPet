package br.com.api.vspet.model.petShop;

import br.com.api.vspet.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record PetShopInputDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String razaoSocial,
        @CNPJ String cnpj,
        @NotBlank String telefone,
        @Valid EnderecoDTO endereco
) {
}
