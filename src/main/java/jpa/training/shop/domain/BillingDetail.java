package jpa.training.shop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetail extends AbstractEntity {

    @ManyToOne
    private Customer customer;

    private String number;

    private LocalDateTime creationDate = LocalDateTime.now();

    public BillingDetail(Customer customer, String number) {
        super();
        this.customer = customer;
        this.number = number;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
