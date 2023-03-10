package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    private String name;

    @Embedded
    private MonetaryAmount price;

    @ElementCollection
    private List<Comment> comments = new ArrayList<>();

    @ElementCollection
    private List<String> alternativeNames = new ArrayList<>();

    public Article(String number, String name, MonetaryAmount price, String... alternativeNames) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.alternativeNames.addAll(Arrays.asList(alternativeNames));
    }

    public void addComment(CommentRanking ranking, String text, User user) {
        comments.add(new Comment(text, ranking, user));
    }
}
