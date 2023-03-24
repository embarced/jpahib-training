package jpa.training.shop.domain;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;

@Embeddable
public class MonetaryAmount {
	private BigDecimal amount;
	private Currency currency;

	public MonetaryAmount() {
		super();
	}

	public MonetaryAmount(BigDecimal amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", amount, currency.getSymbol());
	}
}
