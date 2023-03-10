package jpa.training.shop;

import jpa.training.shop.domain.*;
import jpa.training.shop.repository.ArticleRepository;
import jpa.training.shop.repository.CountryRepository;
import jpa.training.shop.repository.CustomerRepository;
import jpa.training.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User u1 = userRepository.save(new User("Falk", "geheim", "fs@embarc.de"));
        User u2 = userRepository.save(new User("Falk2", "geheim", "fs@embarc.de"));
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);

        Country de = new Country("DE");
        countryRepository.save(de);
        countryRepository.save(new Country("AT"));
        countryRepository.save(new Country("CH"));

        Address a1 = new Address("Str.", "64295", "Darmstadt", de);

        Customer c1 = new Customer("Falk", "Sippach", u1);
        c1.addToAddresses(a1);

        Customer c2 = new Customer("Falk2", "", u2);

        customerRepository.save(c1);
        customerRepository.save(c2);

        Article buch1 = articleRepository.save(new Article("12345", "Buch", new MonetaryAmount(BigDecimal.TEN, Currency.getInstance("EUR")), "Hörbuch"));
        Article buch2 = articleRepository.save(new Article("12346", "Buch 2", new MonetaryAmount(BigDecimal.TEN, Currency.getInstance("USD")), "Hibernate", "JPA"));
        Article buch3 = articleRepository.save(new Article("12347", "Buch 3", new MonetaryAmount(BigDecimal.TEN, Currency.getInstance("EUR"))));

        buch1.addComment(CommentRanking.EXCELLENT, "blabla", u1);
        buch1.addComment(CommentRanking.BAD, "blabla", u1);
        buch1.addComment(CommentRanking.GOOD, "blabla", u2);

        Order o1 = new Order(c1);
        o1.addArticle(buch1, 1);
        o1.addArticle(buch2, 2);
        c1.addToPurchaseOrders(o1);

        Order o2 = new Order(c2);
        o2.addArticle(buch3, 5);
        o2.addArticle(buch2, 2);
        c2.addToPurchaseOrders(o2);

        Order o3 = new Order(c1);
        o3.addArticle(buch1, 1);
        o3.addArticle(buch2, 2);
        c1.addToPurchaseOrders(o3);

    }
}
