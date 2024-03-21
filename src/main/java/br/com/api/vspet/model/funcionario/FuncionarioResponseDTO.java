package br.com.api.vspet.model.funcionario;

import br.com.api.vspet.model.endereco.EnderecoDTO;
import br.com.api.vspet.model.petShop.PetShopResponseDTO;

public record FuncionarioResponseDTO(
        PetShopResponseDTO petShop,
        EnderecoDTO endereco,
        String nome,
        String cpf,
        String telefone
) {
    public FuncionarioResponseDTO(Funcionario funcionario){
        this(new PetShopResponseDTO(funcionario.getPetShop()),new EnderecoDTO(funcionario.getEndereco()), funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone());
    }
}
