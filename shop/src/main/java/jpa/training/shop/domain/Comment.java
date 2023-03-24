package jpa.training.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Comment {
	@ManyToOne
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CommentRanking ranking;
	
	private String text;

	public Comment() {
		super();
	}

	public Comment(User user, CommentRanking ranking, String text) {
		super();
		this.user = user;
		this.ranking = ranking;
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CommentRanking getRanking() {
		return ranking;
	}

	public void setRanking(CommentRanking ranking) {
		this.ranking = ranking;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
