package jpa.training.shop.repository;

import jpa.training.shop.domain.Address;
import jpa.training.shop.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerCustomRepositoryQueryTest {

	@Autowired
	BootStrap bootStrap;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	@Transactional
	void setUp() throws Exception {
		assertNotNull(bootStrap);
		bootStrap.bootStrap();
		assertNotNull(customerRepository);
	}

	@Test
	void testFindAll() {
		List<Customer> result = customerRepository.findAll();
		printCustomers(result, "findAll");
		assertEquals(6, result.size());
	}

	@Test
	void testFindAllByLastName() {
		List<Customer> result = customerRepository.findAllByLastName("%c%");
		printCustomers(result, "findAllByLastName");
		assertEquals(3, result.size());
	}

	@Test
	void testFindAllByZipCode() {
		List<Customer> result = customerRepository.findAllByZipCode("12345");
		printCustomers(result, "findAllByZipCode");
		assertEquals(1, result.size());
	}

	@Test
	void testFindAllByZipCodeBetween() {
		List<Customer> result = customerRepository.findAllByZipCodeBetween("30000", "60000");
		printCustomers(result, "findAllByZipCodeBetween");
		assertEquals(4, result.size());
	}

	@Test
	void testFindAllJoinedAddresses() {
		List<Customer> result = customerRepository.findAllCustomersWithJoinedAddresses();
		printCustomersWithAddress(result, "findAllCustomersWithJoinedAddresses");
		assertEquals(6, result.size());
	}

	@Test
	void testFindAllByAtLeastOneAddress() {
		List<Customer> result = customerRepository.findAllCustomersWithAtLeastOneAddress();
		printCustomers(result, "findAllCustomersWithAtLeastOneAddress");
		assertEquals(4, result.size());
	}

	private void printCustomers(List<Customer> result, String description) {
		System.out.printf("\n--- %s: ---\n", description);
		for (Customer customer : result) {
			System.out.println(customer);
		}
	}
	
	private void printCustomersWithAddress(List<Customer> result, String description) {
		System.out.printf("\n--- %s: ---\n", description);
		for (Customer customer : result) {
			System.out.println(customer);
			for (Address address : customer.getAddresses()) {
				System.out.printf("   %s\n", address);
			}
		}
	}

}
