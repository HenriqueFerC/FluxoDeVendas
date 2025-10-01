package br.com.learning.vendasKafka.domain;

import br.com.learning.vendasKafka.dto.VendasDto.RegisterVendasDto;
import br.com.learning.vendasKafka.enums.SaleStatus;
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
    @Column(name = "SALE_DATE")
    private LocalDateTime saleDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALE_STATUS")
    private SaleStatus saleStatus;

    @Column(name = "ID_ESTOQUE")
    private Long idEstoque;

    @Column(name = "ID_USER")
    private Long idUser;

    public Vendas(RegisterVendasDto vendasDto) {
        totalPrice = vendasDto.totalPrice();
        idEstoque = vendasDto.idEstoque();
        saleStatus = vendasDto.saleStatus();
        idUser = vendasDto.idUser();
    }
}
