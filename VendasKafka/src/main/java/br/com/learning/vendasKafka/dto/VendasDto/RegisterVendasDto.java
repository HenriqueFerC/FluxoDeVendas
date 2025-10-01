package br.com.learning.vendasKafka.dto.VendasDto;

import br.com.learning.vendasKafka.enums.SaleStatus;

public record RegisterVendasDto(double totalPrice, Long idEstoque, SaleStatus saleStatus, Long idUser) {
}
