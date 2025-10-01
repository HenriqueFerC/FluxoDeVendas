package br.com.learning.vendasKafka.domain;

import br.com.learning.vendasKafka.dto.ItemsDto.RegisterItemsDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Items {


    private Long idEstoque;

    private int quantity;

    private double unityPrice;

    private double totalPrice;

    private Long idUser;

    public Items(RegisterItemsDto itemsDto){
        idEstoque = itemsDto.idEstoque();
        quantity = itemsDto.quantity();
        unityPrice = itemsDto.unityPrice();
        totalPrice = itemsDto.quantity() * itemsDto.unityPrice();
    }
}
