package br.com.learning.vendasKafka.service;

import br.com.learning.vendasKafka.domain.Items;
import br.com.learning.vendasKafka.domain.Vendas;
import br.com.learning.vendasKafka.dto.VendasDto.RegisterVendasDto;
import br.com.learning.vendasKafka.repository.VendasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    @Qualifier("jsonKafkaTemplate")
    private KafkaTemplate<Long, Items> jsonKafkaTemplate;

    @Autowired
    private VendasRepository vendasRepository;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "user-topic", partitions = {"0"}), groupId = "vendas-group", containerFactory = "jsonKafkaListenerContainerFactory")
    @Transactional
    public void realizarVenda(Items item) {
        RegisterVendasDto sale = new RegisterVendasDto(item.getTotalPrice(), item.getIdEstoque());
        Vendas saleFinished = new Vendas(sale);
        vendasRepository.save(saleFinished);
        jsonKafkaTemplate.send("sale-topic", item);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "stock-topic", partitions = {"0"}), groupId = "vendas-group", containerFactory = "stringKafkaListenerContainerFactory")
    public void processarVenda(String mensagem) {
        System.out.println(mensagem);
    }

}
