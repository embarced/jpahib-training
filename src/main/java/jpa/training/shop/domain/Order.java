package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BESTELLUNGEN")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private Set<OrderPosition> positions = new HashSet<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addArticle(Article article, int quantity) {
        positions.add(new OrderPosition(this, article, quantity));
    }
}
