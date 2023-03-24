package jpa.training.shop;

import jpa.training.shop.domain.Country;
import jpa.training.shop.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShopApplication.class);
        // application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (countryRepository.count() == 0) {
            countryRepository.save(new Country("DE"));
            countryRepository.save(new Country("AT"));
            countryRepository.save(new Country("CH"));
        }
    }
}
