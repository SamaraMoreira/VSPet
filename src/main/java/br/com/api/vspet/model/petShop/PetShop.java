package br.com.api.vspet.model.petShop;


import br.com.api.vspet.model.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data

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

} 