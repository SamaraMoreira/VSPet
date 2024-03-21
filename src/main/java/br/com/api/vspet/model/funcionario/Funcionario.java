package br.com.api.vspet.model.funcionario;

import br.com.api.vspet.model.endereco.Endereco;
import br.com.api.vspet.model.petShop.PetShop;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_petshop")
    private PetShop petShop;

    @Embedded
    private Endereco endereco;

    private String nome;
    private String cpf;
    private String telefone;

    public Funcionario(PetShop petshop, FuncionarioInputDTO funcionarioInput) {
        this.petShop = petshop;
        this.endereco = new Endereco(funcionarioInput.endereco());
        this.nome = funcionarioInput.nome();
        this.cpf = funcionarioInput.cpf();
        this.telefone = funcionarioInput.telefone();
    }

    public void update(FuncionarioUpdateDTO dadosFuncionario) {
        this.endereco = new Endereco(dadosFuncionario.endereco());
        this.telefone = dadosFuncionario.telefone();
    }
}
