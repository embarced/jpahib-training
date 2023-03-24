package jpa.training.shop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import jpa.training.shop.domain.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ArticleCustomRepositoryQueryTest {

	@Autowired
	BootStrap bootStrap;

	@Autowired
	private ArticleRepository articleRepository;


	@BeforeEach
	@Transactional
	public void setUp() throws Exception {
		assertNotNull(bootStrap);
		bootStrap.bootStrap();
		assertNotNull(articleRepository);
	}

	@Test
	public void testFindAll() {
		List<Article> result = articleRepository.findAll();
		printArticles(result, "findAll");
		assertEquals(3, result.size());
	}

	@Test
	public void testFindAllByPriceLessThan() {
		List<Article> result = articleRepository.findAllByPriceLessThan(BigDecimal.valueOf(5));
		printArticles(result, "findAllByPriceLessThan");
		assertEquals(2, result.size());
 	}

	@Test
	public void testFindAllSortByPriceAsc() {
		List<Article> result = articleRepository.findAllSortByPriceAsc();
		printArticles(result, "findAllSortByPriceAsc");
		assertEquals(3, result.size());
		assertEquals(BigDecimal.ONE, result.stream().findFirst().get().getPrice().getAmount());
	}

	@Test
	public void testFindMaxAndAvgPrice() {
		Object[] result = articleRepository.findMaxAndAvgPrice();
		System.out.println("\n--- findMaxAndAvgPrice: ---");
		System.out.printf("max. price: %s\n", result[0]);
		System.out.printf("avg. price: %s\n", result[1]);
		assertEquals(new BigDecimal("10.00"), result[0]);
		assertEquals(4., result[1]);
	}

	private void printArticles(List<Article> result, String description) {
		System.out.printf("\n--- %s: ---\n", description);
		for (Article article: result) {
			System.out.println(article);
		}
	}

}
