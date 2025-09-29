package br.com.learning.EstoqueKafka.service;

import br.com.learning.EstoqueKafka.domain.Items;
import br.com.learning.EstoqueKafka.dto.ItemDto.RegisterItemDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @KafkaListener(topicPartitions = @TopicPartition(topic = "sale-topic", partitions = {"0"}), groupId = "estoque-group")
    public void processarVenda(Items item){

        System.out.println("Venda recebida! ID: " + item.getIdEstoque() + " Valor do Item: " + item.getTotalPrice() + " quantidade: " + item.getQuantity());
    }

}
