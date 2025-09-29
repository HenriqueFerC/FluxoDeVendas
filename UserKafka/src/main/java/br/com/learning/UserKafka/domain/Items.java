package br.com.learning.UserKafka.domain;

import br.com.learning.UserKafka.dto.ItemsDto.RegisterItemsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Items {

    private Long idEstoque;

    private int quantity;

    private double unityPrice;

    private double totalPrice;


    public Items(RegisterItemsDto itemsDto){
        idEstoque = itemsDto.idEstoque();
        quantity = itemsDto.quantity();
        unityPrice = itemsDto.unityPrice();
        totalPrice = itemsDto.quantity() * itemsDto.unityPrice();
    }
}
