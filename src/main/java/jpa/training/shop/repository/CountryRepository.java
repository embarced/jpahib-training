package jpa.training.shop.repository;

import jpa.training.shop.domain.Country;
import jpa.training.shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
