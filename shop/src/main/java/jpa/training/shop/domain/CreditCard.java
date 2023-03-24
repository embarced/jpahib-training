package jpa.training.shop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetail {

	@Enumerated(EnumType.STRING)
	private CreditCardType type;

	private int expiryMonth;

	private int expiryYear;

	public CreditCard() {
		super();
	}

	public CreditCard(Customer customer, String number, CreditCardType type,
			int expiryMonth, int expiryYear) {
		super(customer, number);
		this.type = type;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
	}

	public CreditCardType getType() {
		return type;
	}

	public void setType(CreditCardType type) {
		this.type = type;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

}
