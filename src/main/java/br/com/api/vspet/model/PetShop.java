package br.com.api.vspet.model;

import java.util.Random;

public record PetShop(
    Long id,
    String usename, 
    String password,
    String razaoSocial,
    String cnpj, 
    String telefone,
    String cep,
    String estado,
    String rua,
    String cidade,
    String complemento,
    String numero
) {

    public PetShop(Long id, String usename,  String password,String razaoSocial,String cnpj, String telefone,String cep,String estado,String rua,String cidade,String complemento,String numero){
        var key = (id != null) ? id : Math.abs( new Random().nextLong() );
        this.id = key;
        this.usename = usename;
        this.password = password;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.cep = cep;
        this.estado = estado;
        this.rua = rua;
        this.cidade = cidade;
        this.complemento = complemento;
        this.numero = numero;
    }
} 