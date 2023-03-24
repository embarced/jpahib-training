package jpa.training.shop.repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.training.shop.domain.Customer;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Customer> findAll() {
				return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findAllByLastName(String lastName) {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findAllByZipCode(String zipCode) {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findAllByZipCodeBetween(String zipCode1,
												  String zipCode2) {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findAllCustomersWithJoinedAddresses() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}

	@Override
	public List<Customer> findAllCustomersWithAtLeastOneAddress() {
		return em.createQuery("select c from Customer c", Customer.class).getResultList();
	}
}
