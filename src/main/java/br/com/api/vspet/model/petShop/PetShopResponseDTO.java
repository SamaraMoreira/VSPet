package br.com.api.vspet.model.petShop;

import br.com.api.vspet.model.endereco.EnderecoDTO;

public record PetShopResponseDTO(
    String razaoSocial,
    String cnpj,
    String telefone,
    EnderecoDTO endereco
) 
{
    public PetShopResponseDTO(PetShop petShop) {
        this(petShop.getRazaoSocial(),petShop.getCnpj(),petShop.getTelefone(), new EnderecoDTO(petShop.getEndereco()));

    }
}