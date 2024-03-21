package br.com.api.vspet.model.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
    @NotBlank String cep,
    @NotBlank String estado,
    @NotBlank String rua,
    @NotBlank String cidade,
    String complemento,
    @NotBlank String numero
) {
    public EnderecoDTO(Endereco endereco){
        this(endereco.getCep(),endereco.getEstado(), endereco.getRua(), endereco.getCidade(), endereco.getComplemento(), endereco.getNumero());
    }
}
