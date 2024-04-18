package br.com.api.vspet.model.pet;

import br.com.api.vspet.model.petShop.PetShop;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

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

}
