package jpa.training.shop;

import jpa.training.shop.domain.User;
import jpa.training.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("Falk", "geheim", "fs@embarc.de"));
        userRepository.save(new User("Falk2", "geheim", "fs@embarc.de"));
        List<User> allUsers = userRepository.findAll();
        System.out.println(allUsers);

    }
}
