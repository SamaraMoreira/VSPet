package br.com.api.vspet.model.petShop;


import br.com.api.vspet.model.endereco.Endereco;
import br.com.api.vspet.model.funcionario.Funcionario;
import br.com.api.vspet.model.pet.Pet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class PetShop{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;
    private String password;
    private String razaoSocial;
    private String cnpj;
    private String telefone;

    @Embedded
    private Endereco endereco;

    @JsonBackReference
    @OneToMany(mappedBy = "petShop")
    private List<Funcionario> funcionarios = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "petShop")
    private List<Pet> pets = new ArrayList<>();


    public PetShop(PetShopInputDTO petShop) {
        this.username = petShop.username();
        this.password = petShop.password();
        this.razaoSocial = petShop.razaoSocial();
        this.cnpj = petShop.cnpj();
        this.telefone = petShop.telefone();
        this.endereco = new Endereco(petShop.endereco());
    }

    public void update(PetShopUpdateDTO dadosPetShop) {
        this.razaoSocial = dadosPetShop.razaoSocial();
        this.telefone = dadosPetShop.telefone();
        this.endereco = new Endereco(dadosPetShop.endereco());
    }
}