package jpa.training.shop.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Article extends AbstractEntity {
	private String number;
	
	private String name;
	
	private String description;

	@Embedded
	private MonetaryAmount price;

	@ElementCollection
	private List<Comment> comments;
	
	public Article() {
		super();
	}

	public Article(String number, String name, String description,
			MonetaryAmount price) {
		super();
		this.number = number;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MonetaryAmount getPrice() {
		return price;
	}

	public void setPrice(MonetaryAmount price) {
		this.price = price;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return String.format("#%s, %s (%s)", number, name, price);
	}
}
