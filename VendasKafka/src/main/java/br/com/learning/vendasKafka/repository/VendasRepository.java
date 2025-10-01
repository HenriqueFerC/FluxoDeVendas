package br.com.learning.vendasKafka.repository;

import br.com.learning.vendasKafka.domain.Items;
import br.com.learning.vendasKafka.domain.Vendas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
    List<Vendas> findByIdUser(Long id);
}
