package jpa.training.shop.domain;

import jakarta.persistence.Entity;

@Entity
public class CreditCard extends BillingDetail {

    private CreditCardType type;

    private int expiryMonth;

    private int expiryYear;

    public CreditCard(Customer customer, String number, CreditCardType type, int expiryMonth, int expiryYear) {
        super(customer, number);
        this.type = type;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
    }
}
