package jpa.training.shop.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetail {

	private String bankName;

	private String bankCode;

	public BankAccount() {
		super();
	}

	public BankAccount(Customer customer, String number, String bankName,
			String bankCode) {
		super(customer, number);
		this.bankName = bankName;
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
