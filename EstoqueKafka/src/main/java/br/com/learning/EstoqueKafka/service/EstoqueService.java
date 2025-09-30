package br.com.learning.EstoqueKafka.service;

import br.com.learning.EstoqueKafka.domain.Items;
import br.com.learning.EstoqueKafka.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private KafkaTemplate<Long, Items> stringKafkaTemplate;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "user-topic", partitions = {"0"}), groupId = "estoque-group")
    @Transactional
    public void processarVenda(Items item){
        var id = item.getIdEstoque();
        var stock = stockRepository.getReferenceById(id);
        if (stock.getQuantity() < item.getQuantity()) {
            item.setQuantity(0);
            stringKafkaTemplate.send("stock-topic", item);
            return;
        }
        stock.atualizarEstoque(item.getQuantity());
        stockRepository.save(stock);
        stringKafkaTemplate.send("stock-topic", item);
    }

}
