package br.com.costa.cadastrodeprodutosSecurity.enitity;

import br.com.costa.cadastrodeprodutosSecurity.enitity.productenum.ProductEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @Enumerated(EnumType.STRING)
    private ProductEnum productEnum;

    public ProductEntity(String name, String description, BigDecimal price, String category, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.productEnum = ProductEnum.ACTIVE;
    }
}
