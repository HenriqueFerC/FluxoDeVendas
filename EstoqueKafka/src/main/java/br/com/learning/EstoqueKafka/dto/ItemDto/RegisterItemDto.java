package br.com.learning.EstoqueKafka.dto.ItemDto;

public record RegisterItemDto (Long idEstoque, int quantity, double unityPrice, double totalPrice){
}
