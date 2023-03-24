package jpa.training.shop.repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.training.shop.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BootStrap {
	
	private static boolean executed = false;
	
	@PersistenceContext
	private EntityManager em;

	public void bootStrap() {
		if (executed) {
			return;
		}
		List<Country> countries = createCountries();
		List<Customer> customers = createCustomers(countries);
		List<Article> articles = createArticles();
		createOrders(customers, articles);
		executed = true;
	}

	private List<Customer> createCustomers(List<Country> countries) {
		User u1 = new User("ddevelop", "geheim", "dieter.develop@oio.de");
		User u2 = new User("btuch", "geheim", "bert.tuch@xyz.org");
		User u3 = new User("hmartin", "geheim", "hans.martin@abc.com");
		User u4 = new User("sbaum", "geheim", "sieglinde.baum@wald.org");
		User u5 = new User("cschmidt", "geheim", "clara.schmidt@hier.de");
		User u6 = new User("fglueck", "geheim", "felix.glueck@all.com");

		Customer c1 = new Customer("Dieter", "Develop", u1);
		Customer c2 = new Customer("Bert", "Tuch", u2);
		Customer c3 = new Customer("Hans", "Martin", u3);
		Customer c4 = new Customer("Sieglinde", "Baum", u4);
		Customer c5 = new Customer("Clara", "Schmidt", u5);
		Customer c6 = new Customer("Felix", "Gl�ck", u6);

		Address a11 = createAddress("Platz 1", "33245", "B-Stadt",
				countries.get(0));
		Address a21 = createAddress("Hauptstrasse 10", "12345", "Mannheim",
				countries.get(0));
		Address a22 = createAddress("Malerstrasse 10", "5432", "Wien",
				countries.get(1));
		Address a31 = createAddress("Unterm Anger 30", "55432", "D-Dorf",
				countries.get(0));
		Address a41 = createAddress("Hinterm Misthaufen 24", "2343", "T-Dorf",
				countries.get(1));
		Address a42 = createAddress("Seepromenade", "3444", "C-Dorf",
				countries.get(1));

		em.persist(u1);
		em.persist(u2);
		em.persist(u3);
		em.persist(u4);
		em.persist(u5);
		em.persist(u6);

		c1.addToAddresses(a11);
		c2.addToAddresses(a21);
		c2.addToAddresses(a22);
		c2.setBillingAddress(a21);
		c2.addToBillingDetails(createCreditCard(c2, "12345",
				CreditCardType.VISA, 12, 2020));
		c3.addToAddresses(a31);
		c4.addToAddresses(a41);
		c4.addToAddresses(a42);

		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
		em.persist(c5);
		em.persist(c6);
		
		return Arrays.asList(c1, c2, c3, c4, c5, c6);
	}

	private static Address createAddress(String street, String zipCode,
			String city, Country country) {
		return new Address(street, zipCode, city, country);
	}

	private static CreditCard createCreditCard(Customer customer,
			String number, CreditCardType type, int expiryMonth, int expiryYear) {
		return new CreditCard(customer, number, type, expiryMonth, expiryYear);
	}

	private List<Country> createCountries() {
		List<Country> countries = Arrays.asList(new Country("Deutschland"),
				new Country("�sterreich"), new Country("Schweiz"));
		for (Country country : countries) {
			em.persist(country);
		}
		return countries;
	}

	private List<Article> createArticles() {
		Article a1 = new Article("1a-33", "Zahnpasta", "Die Zahnpasta",
				new MonetaryAmount(BigDecimal.ONE, Currency.getInstance("EUR")));
		Article a2 = new Article("1b-33", "Zahnbürste",
				"Rotierend und mit ...", new MonetaryAmount(BigDecimal.TEN,
						Currency.getInstance("EUR")));
		Article a3 = new Article("1c-12", "Taschentücher", "Soft und weich",
				new MonetaryAmount(BigDecimal.ONE, Currency.getInstance("EUR")));

		List<Article> articles = Arrays.asList(a1, a2, a3);
		for (Article article : articles) {
			em.persist(article);
		}
		return articles;
	}

	private void createOrders(List<Customer> customers, List<Article> articles) {
		Order o2 = new Order(customers.get(0));
		o2.addToArticles(articles.get(0), 1);
		o2.addToArticles(articles.get(1), 1);
		em.persist(o2);
	}
}
