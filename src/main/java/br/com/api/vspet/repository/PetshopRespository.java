package br.com.api.vspet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.vspet.model.petShop.PetShop;

public interface PetshopRespository extends JpaRepository <PetShop , Long> {
    
}
