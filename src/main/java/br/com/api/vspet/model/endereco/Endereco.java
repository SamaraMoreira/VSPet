package br.com.api.vspet.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Endereco {
    private String cep;
    private String estado;
    private String rua;
    private String cidade;
    private String complemento;
    private String numero;    
}
