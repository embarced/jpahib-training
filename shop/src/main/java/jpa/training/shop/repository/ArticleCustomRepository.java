package jpa.training.shop.repository;

import jpa.training.shop.domain.Article;

import java.math.BigDecimal;
import java.util.List;

public interface ArticleCustomRepository {
    List<Article> findAll();
    List<Article> findAllByPriceLessThan(BigDecimal price);
    List<Article> findAllSortByPriceAsc();
    Object[] findMaxAndAvgPrice();
}
