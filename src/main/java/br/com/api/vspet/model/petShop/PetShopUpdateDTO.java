package br.com.api.vspet.model.petShop;

import br.com.api.vspet.model.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PetShopUpdateDTO(
        @NotBlank String razaoSocial,
        @NotBlank String telefone,
        @Valid EnderecoDTO endereco
) {
}
