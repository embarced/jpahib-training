package jpa.training.shop.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public class MonetaryAmount {
    private BigDecimal amount;
    private Currency currency;

    public MonetaryAmount() {
    }

    public MonetaryAmount(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s %s", amount, currency.getSymbol());
    }

    public Currency getCurrency() {
        return currency;
    }
}
