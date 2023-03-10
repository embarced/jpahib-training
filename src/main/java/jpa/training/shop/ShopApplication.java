package jpa.training.shop;

import jpa.training.shop.domain.Address;
import jpa.training.shop.domain.Country;
import jpa.training.shop.domain.Customer;
import jpa.training.shop.domain.User;
import jpa.training.shop.repository.CountryRepository;
import jpa.training.shop.repository.CustomerRepository;
import jpa.training.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userRepository.save(new User("Falk", "geheim", "fs@embarc.de"));
        userRepository.save(new User("Falk2", "geheim", "fs@embarc.de"));
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);

        Country de = new Country("DE");
        countryRepository.save(de);
        countryRepository.save(new Country("AT"));
        countryRepository.save(new Country("CH"));

        Address a1 = new Address("Str.", "64295", "Darmstadt", de);

        Customer c1 = new Customer("Falk", "Sippach");
        c1.addToAddresses(a1);

        customerRepository.save(c1);
    }
}
