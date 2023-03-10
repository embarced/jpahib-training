package jpa.training.shop.domain;

import jakarta.persistence.Entity;

@Entity
public class BankAccount extends BillingDetail {

    private String bankName;

    private String bankCode;

    public BankAccount(Customer customer, String number, String bankName, String bankCode) {
        super(customer, number);
        this.bankName = bankName;
        this.bankCode = bankCode;
    }
}
