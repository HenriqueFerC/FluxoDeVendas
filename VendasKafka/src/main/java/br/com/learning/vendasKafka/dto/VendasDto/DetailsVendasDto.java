package br.com.learning.vendasKafka.dto.VendasDto;

import br.com.learning.vendasKafka.domain.Vendas;

import java.time.LocalDateTime;

public record DetailsVendasDto(Long id, double totalPrice, LocalDateTime date) {
    public DetailsVendasDto(Vendas sales){
        this(sales.getId(), sales.getTotalPrice(), sales.getSaleDate());
    }
}