package br.com.learning.UserKafka.dto.ItemsDto;

public record RegisterItemsDto(Long idEstoque, int quantity, double unityPrice, Long idUser) {
}
