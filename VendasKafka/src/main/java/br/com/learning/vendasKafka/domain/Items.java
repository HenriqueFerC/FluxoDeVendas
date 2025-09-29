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

@Entity
@Table(name = "TB_ITEM")
public class Items {

    @Id
    @Column(name = "ID_ESTOQUE")
    private Long idEstoque;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "UNITY_PRICE")
    private double unityPrice;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;


    public Items(RegisterItemsDto itemsDto){
        idEstoque = itemsDto.idEstoque();
        quantity = itemsDto.quantity();
        unityPrice = itemsDto.unityPrice();
        totalPrice = itemsDto.quantity() * itemsDto.unityPrice();
    }
}
