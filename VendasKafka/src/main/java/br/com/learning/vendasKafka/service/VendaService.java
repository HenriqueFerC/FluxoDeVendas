package br.com.learning.vendasKafka.service;

import br.com.learning.vendasKafka.domain.Items;
import br.com.learning.vendasKafka.domain.Vendas;
import br.com.learning.vendasKafka.dto.VendasDto.RegisterVendasDto;
import br.com.learning.vendasKafka.enums.SaleStatus;
import br.com.learning.vendasKafka.repository.VendasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    @Qualifier("stringKafkaTemplate")
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    @Qualifier("listJsonKafkaTemplate")
    private KafkaTemplate<String, List<Vendas>> listKafkaTemplate;

    @Autowired
    private VendasRepository vendasRepository;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "stock-topic", partitions = {"0"}), groupId = "vendas-group", containerFactory = "jsonKafkaListenerContainerFactory")
    @Transactional
    public void processarVenda(Items item) {
        if(item.getQuantity() == 0) {
            stringKafkaTemplate.send("sale-topic", "Item sem estoque!");
            return;
        }
        RegisterVendasDto sale = new RegisterVendasDto(item.getTotalPrice(), item.getIdEstoque(), SaleStatus.CONCLUÍDO, item.getIdUser());
        Vendas saleFinished = new Vendas(sale);
        vendasRepository.save(saleFinished);
        stringKafkaTemplate.send("sale-topic",0 , null, "Compra realizada com sucesso!");
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "user-topic", partitions = {"1"}), groupId = "user-group", containerFactory = "longKafkaListenerContainerFactory")
    public void sendUserHistorical(Long id) {
        var list = vendasRepository.findByIdUser(id);
        listKafkaTemplate.send("sale-topic",1, null, list);
    }

}
