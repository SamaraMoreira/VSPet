package br.com.api.vspet.model.petShop;


import br.com.api.vspet.model.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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