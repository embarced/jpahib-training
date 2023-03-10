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

        Article buch1 = articleRepository.save(new Article("12345", "Buch", BigDecimal.TEN));
        Article buch2 = articleRepository.save(new Article("12346", "Buch 2", BigDecimal.TEN));
        Article buch3 = articleRepository.save(new Article("12347", "Buch 3", BigDecimal.TEN));

        c1.orderArticle(buch1);
        c1.orderArticle(buch2);

        c2.orderArticle(buch3);
        c2.orderArticle(buch2);
    }
}
