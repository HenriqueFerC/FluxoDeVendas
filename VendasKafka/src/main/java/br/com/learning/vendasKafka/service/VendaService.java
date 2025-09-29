package br.com.learning.vendasKafka.service;

import br.com.learning.vendasKafka.domain.Items;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "user-topic", partitions = {"0"}), groupId = "vendas-group")
    public void processarVenda(Items item) {
        System.out.println("Venda recebida! ID: " + item.getIdEstoque() + " Valor do Item: " + item.getTotalPrice() + " quantidade: " + item.getQuantity());
    }

}
