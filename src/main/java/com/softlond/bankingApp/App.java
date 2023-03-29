package com.softlond.bankingApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.services.CustomerService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {

			//Create customer service
			CustomerService customerService = new CustomerService();
			
			//delete customers
			customerService.delete("1002732542");
			customerService.delete("1000000000");

			//Create customer instances
			Customer customer1 = new Customer("John", "Eider", "Cardona", null, "1002732542", LocalDate.of(1995, 9, 22));
			Customer customer2 = new Customer("Juan", null, "Garcia", null, "1000000000", LocalDate.of(2000, 3, 11));

			//Save customer instances
			customerService.save(customer1);
			customerService.save(customer2);

			//list customer instances
			List<Customer> customers = customerService.listarPersonas();
			for (Customer customer : customers) {
				System.out.println("===================================");
				System.out.println(customer.getFirstName());
				System.out.println(customer.getSecondName());
				System.out.println(customer.getFirstLastname());
				System.out.println(customer.getSecondLastname());
				System.out.println(customer.getIdentityNumber());
				System.out.println(customer.getDateOfBirth());
				System.out.println("===================================");
			}

			//Find customer by identityNumber
			Customer customerFind = customerService.findByIdentity("10027325422");
//			System.out.println(customerFind.getFirstName());
//			System.out.println(customerFind.getDateOfBirth());
			
			
			
			Customer customer3 = new Customer("Juan", "José", "Martinez", null, "1002732542", LocalDate.of(1996, 9, 22));			
			customerService.update("1002732542", customer3);
//			
//			
			List<Customer> customers2 = customerService.listarPersonas();
			
			for (Customer customer : customers2) {
				System.out.println("===================================");
				System.out.println(customer.getFirstName());
				System.out.println(customer.getSecondName());
				System.out.println(customer.getFirstLastname());
				System.out.println(customer.getSecondLastname());
				System.out.println(customer.getIdentityNumber());
				System.out.println(customer.getDateOfBirth());
				System.out.println("===================================");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
