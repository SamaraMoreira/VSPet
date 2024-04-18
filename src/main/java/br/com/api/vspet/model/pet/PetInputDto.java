package br.com.api.vspet.model.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PetInputDto(
        @NotNull Long id_petshop,
        @NotBlank String nome,
        @NotBlank String nomeResponsavel,
        @NotBlank @CPF String cpfResponsavel,
        @NotBlank String telefone,
        @NotBlank String raca,
        String observacoes
) {
}
