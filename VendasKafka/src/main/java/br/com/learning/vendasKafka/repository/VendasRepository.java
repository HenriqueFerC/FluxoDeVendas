package br.com.learning.vendasKafka.repository;

import br.com.learning.vendasKafka.domain.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendasRepository extends JpaRepository<Vendas, Long> {
}
