package com.example.stock.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
public class Stock {
    // id, productId, quantity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantityId;

    private Long quantity;

    @Version
    private Long version;

    public Stock(Long quantityId, Long quantity) {
        this.quantityId = quantityId;
        this.quantity = quantity;
    }

    public void decrease(Long quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("foo");
        }

        this.quantity = this.quantity - quantity;
    }

}
