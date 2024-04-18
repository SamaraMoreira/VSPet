package br.com.api.vspet.model.pet;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PetUpdateDTO(
        @NotBlank String nome,
        @NotBlank String nomeResponsavel,
        @NotBlank @CPF String cpfResponsavel,
        @NotBlank String telefone,
        @NotBlank String raca,
        String observacoes
) {
}
