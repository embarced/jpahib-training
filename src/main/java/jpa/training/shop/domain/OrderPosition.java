package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Order purchaseOrder;

    private int quantity;

    public OrderPosition(Order purchaseOrder, Article article, int quantity) {
        this.purchaseOrder = purchaseOrder;
        this.article = article;
        this.quantity = quantity;
    }
}
