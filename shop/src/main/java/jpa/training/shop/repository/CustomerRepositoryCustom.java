package jpa.training.shop.repository;

import jpa.training.shop.domain.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findAll();
    List<Customer> findAllByLastName(String lastName);
    List<Customer> findAllByZipCode(String zipCode);
    List<Customer> findAllByZipCodeBetween(String zipCode1, String zipCode2);
    List<Customer> findAllCustomersWithJoinedAddresses();
    List<Customer> findAllCustomersWithAtLeastOneAddress();
}
