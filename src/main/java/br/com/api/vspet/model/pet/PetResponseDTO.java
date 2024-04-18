package br.com.api.vspet.model.pet;

import br.com.api.vspet.model.petShop.PetShopResponseDTO;

public record PetResponseDTO(
        Long id,
        String nome,
        String nomeResponsavel,
        String cpfResponsavel,
        String telefone,
        String raca,
        String observacoes,
        PetShopResponseDTO petshop
) {

    public PetResponseDTO(Pet pet){
        this(pet.getId(),pet.getNome(), pet.getNomeResponsavel(), pet.getCpfResponsavel(), pet.getTelefone(), pet.getRaca(), pet.getObservacoes(), new PetShopResponseDTO(pet.getPetShop()));
    }
}
