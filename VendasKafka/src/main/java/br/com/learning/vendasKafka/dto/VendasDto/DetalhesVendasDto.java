package br.com.learning.vendasKafka.dto;

import br.com.learning.vendasKafka.domain.Vendas;

import java.time.LocalDateTime;

public record DetalhesVendasDto(Long id, LocalDateTime date) {
    public DetalhesVendasDto(Vendas sales){
        this(sales.getId(), sales.getSaleDate());
    }
}