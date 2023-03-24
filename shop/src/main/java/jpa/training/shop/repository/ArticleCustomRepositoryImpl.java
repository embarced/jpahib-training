package jpa.training.shop.repository;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.training.shop.domain.Article;

public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Article> findAll() {
		return em.createQuery("select a from Article a", Article.class).getResultList();
	}

	@Override
	public List<Article> findAllByPriceLessThan(BigDecimal price) {
		return em.createQuery("select a from Article a", Article.class).setParameter("price", price).getResultList();
	}

	@Override
	public List<Article> findAllSortByPriceAsc() {
		return em.createQuery("select a from Article a", Article.class).getResultList();
	}

	@Override
	public Object[] findMaxAndAvgPrice() {
		return (Object[]) em.createQuery("select a from Article a").getSingleResult();
	}

}
