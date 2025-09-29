package br.com.learning.vendasKafka.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "TB_SALE")
@EntityListeners(AuditingEntityListener.class)
public class Vendas {

    @Id
    @Column(name = "ID_SALE")
    @GeneratedValue
    private Long id;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @CreatedDate
    private LocalDateTime saleDate;

    @Column(name = "IDS_ITEMS")
    private List<Long> idsItems;
}
