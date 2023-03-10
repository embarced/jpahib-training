package jpa.training.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Embeddable
public class Comment {
    private String text;

    @Enumerated(EnumType.ORDINAL)
    private CommentRanking ranking;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private User user;

    public Comment() {
    }

    public Comment(String text, CommentRanking ranking, User user) {
        this.text = text;
        this.ranking = ranking;
        this.user = user;
    }
}
