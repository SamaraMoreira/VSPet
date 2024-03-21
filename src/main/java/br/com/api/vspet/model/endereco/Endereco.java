package br.com.api.vspet.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String estado;
    private String rua;
    private String cidade;
    private String complemento;
    private String numero;

    public Endereco(EnderecoDTO endereco) {
        this.cep = endereco.cep();
        this.estado = endereco.estado();
        this.rua = endereco.rua();
        this.cidade = endereco.cidade();
        this.complemento = endereco.complemento();
        this.numero = endereco.numero();
    }
}
