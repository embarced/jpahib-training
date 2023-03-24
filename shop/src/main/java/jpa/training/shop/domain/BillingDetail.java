package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "billingtype", discriminatorType = DiscriminatorType.STRING)
public abstract class BillingDetail extends AbstractEntity {

	@ManyToOne
	private Customer customer;

	private String number;

	@Temporal(TemporalType.DATE)
	private Date created = new Date();

	public BillingDetail() {
		super();
	}

	public BillingDetail(Customer customer, String number) {
		super();
		this.customer = customer;
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
}
