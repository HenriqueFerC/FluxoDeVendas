package br.com.learning.vendasKafka.domain;

import br.com.learning.vendasKafka.dto.VendasDto.RegisterVendasDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

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
    private double totalPrice;

    @CreatedDate
    private LocalDateTime saleDate;

    @Column(name = "ID_ESTOQUE")
    private Long idEstoque;

    public Vendas(RegisterVendasDto vendasDto) {
        totalPrice = vendasDto.totalPrice();
        idEstoque = vendasDto.idEstoque();
    }
}
