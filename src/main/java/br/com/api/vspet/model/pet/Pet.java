package br.com.api.vspet.model.pet;

import br.com.api.vspet.controller.PetController;
import br.com.api.vspet.model.petShop.PetShop;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Pet extends EntityModel<Pet> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String telefone;
    private String raca;
    private String observacoes;

    @JsonManagedReference
    @JoinColumn(name = "id_petshop")
    @ManyToOne
    private PetShop petShop;

    public Pet(PetShop petshop, PetInputDto petInputDto) {
        this.nome = petInputDto.nome();
        this.nomeResponsavel = petInputDto.nomeResponsavel();
        this.cpfResponsavel = petInputDto.cpfResponsavel();
        this.telefone = petInputDto.telefone();
        this.raca = petInputDto.raca();
        this.observacoes = petInputDto.observacoes();
        this.petShop = petshop;
    }

    public void update(PetUpdateDTO petUpdateDTO) {
        this.nome = petUpdateDTO.nome();
        this.nomeResponsavel = petUpdateDTO.nomeResponsavel();
        this.cpfResponsavel = petUpdateDTO.cpfResponsavel();
        this.telefone = petUpdateDTO.telefone();
        this.raca = petUpdateDTO.raca();
        this.observacoes = petUpdateDTO.observacoes();
    }
    public EntityModel<Pet> toEntityModel() {
        return EntityModel.of(
                this,
                linkTo(methodOn(PetController.class).get(id)).withSelfRel(),
                linkTo(methodOn(PetController.class).delete(id)).withRel("delete"),
                linkTo(methodOn(PetController.class).index(null)).withRel("contents")
        );
    }
}
