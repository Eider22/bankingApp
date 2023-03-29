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

			CustomerService customerService = new CustomerService();
			customerService.delete("1002732542");
			customerService.delete("1000000000");

			Customer customer1 = new Customer("John", null, "Cardona", null, "1002732542", LocalDate.of(1995, 9, 22));
			Customer customer2 = new Customer("Juan", null, "Garcia", null, "1000000000", LocalDate.of(2000, 3, 11));

			customerService.save(customer1);
			customerService.save(customer2);

			List<Customer> customers = customerService.listarPersonas();

			for (Customer customer : customers) {
				System.out.println(customer.getFirstName());
			}

			Customer customerFind = customerService.find("1002732542");
			System.out.println(customerFind.getFirstName());
			System.out.println(customerFind.getDateOfBirth());
			
			
			
			Customer customer3 = new Customer("Eider", null, "Cardona", null, "1002732542", LocalDate.of(1995, 9, 22));			
			customerService.update("1002732542", customer3);
			
			
			List<Customer> customers2 = customerService.listarPersonas();
			
			for (Customer customer : customers2) {
				System.out.println(customer.getFirstName());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
