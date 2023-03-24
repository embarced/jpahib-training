package jpa.training.shop.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "BESTELLUNG")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime = new Date();
	
	@ManyToOne
	private Customer customer;

//	@ManyToMany
//	private Set<Article> articles = new HashSet<Article>();
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderPosition> positions = new HashSet<OrderPosition>();
	
	@Transient
	private BigDecimal totalPrice = BigDecimal.ZERO;
	
	public Order() {
		super();
	}

	public Order(Customer customer) {
		super();
		this.customer = customer;
	}

	public Set<OrderPosition> getPositions() {
		return positions;
	}
	
	public Set<Article> getArticles() {
		Set<Article> rv = new HashSet<Article>();
		for (OrderPosition position : positions) {
			rv.add(position.getArticle());
		}
		return rv;
	}
	
	public Order addToArticles(Article a, int quantity) {
		positions.add(new OrderPosition(this, a, quantity));
		totalPrice = totalPrice.add(a.getPrice().getAmount().multiply(BigDecimal.valueOf(quantity)));
		return this;
	}

	public BigDecimal getTotalPrice() {
		if (BigDecimal.ZERO.equals(totalPrice)) {
			for (OrderPosition position : positions) {
				totalPrice = totalPrice.add(position.getArticle().getPrice().getAmount().multiply(BigDecimal.valueOf(position.getQuantity())));
			}	
		}
		return totalPrice;
	}
	
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}
	
}
