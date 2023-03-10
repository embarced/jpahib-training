package jpa.training.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private String name;

    private BigDecimal price;

    public Article(String number, String name, BigDecimal price) {
        this.number = number;
        this.name = name;
        this.price = price;
    }
}
