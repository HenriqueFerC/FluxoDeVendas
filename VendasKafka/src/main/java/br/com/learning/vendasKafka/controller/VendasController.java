package br.com.learning.vendasKafka.controller;

import br.com.learning.vendasKafka.domain.Items;
import br.com.learning.vendasKafka.domain.Vendas;
import br.com.learning.vendasKafka.dto.VendasDto.RegisterVendasDto;
import br.com.learning.vendasKafka.dto.ItemsDto.RegisterItemsDto;
import br.com.learning.vendasKafka.repository.VendasRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    private VendasRepository vendasRepository;

    @Autowired
    @Qualifier("stringKafkaTemplate")
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    @Qualifier("jsonKafkaTemplate")
    private KafkaTemplate<Long, Items> jsonKafkaTemplate;


    @PostMapping
    public ResponseEntity<String> cadastrarVenda(@RequestBody String mensagem) {
        stringKafkaTemplate.send("sale-topic", mensagem);
        return ResponseEntity.ok("mensagem enviada" + mensagem);
    }

    @PostMapping("item")
    public ResponseEntity<Void> registerItem(@RequestBody RegisterItemsDto itemsDto){
        Items item = new Items(itemsDto);
        jsonKafkaTemplate.send("sale-topic", item.getIdEstoque(), item);
        return ResponseEntity.ok().build();
    }

    @PostMapping("criar")
    @Transactional
    public ResponseEntity<Void> criarVenda(RegisterVendasDto vendasDto) {
        Vendas venda = new Vendas();
        vendasRepository.save(venda);
        return ResponseEntity.ok().build();
    }
}
