package br.com.api.vspet.model.endereco;

public record EnderecoDTO(
    String cep,
    String estado,
    String rua,
    String cidade,
    String complemento,
    String numero
) {
    public EnderecoDTO(Endereco endereco){
        this(endereco.getCep(),endereco.getEstado(), endereco.getRua(), endereco.getCidade(), endereco.getComplemento(), endereco.getNumero());
    }
}
