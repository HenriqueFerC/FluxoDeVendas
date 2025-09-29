package br.com.learning.EstoqueKafka.controller;

import br.com.learning.EstoqueKafka.domain.Stock;
import br.com.learning.EstoqueKafka.dto.stockDto.DetailsStockDto;
import br.com.learning.EstoqueKafka.dto.stockDto.RegisterStockDto;
import br.com.learning.EstoqueKafka.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetailsStockDto> registerStock(@RequestBody RegisterStockDto stockDto, UriComponentsBuilder uriBuilder) {
        var stock = new Stock(stockDto);
        stockRepository.save(stock);
        var uri = uriBuilder.path("stock/{id}").buildAndExpand(stock.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsStockDto(stock));
    }

    @GetMapping("{id}")
    public ResponseEntity<DetailsStockDto> findById(@PathVariable("id") Long id) {
        try {
            var stock = stockRepository.getReferenceById(id);
            return ResponseEntity.ok().body(new DetailsStockDto(stock));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
