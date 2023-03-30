package com.softlond.bankingApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.services.CustomerService;
import com.softlond.client.GUI;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		GUI gui = new GUI();
		gui.start();
//		Server server = new Server(8080);
//		server.setHandler(new DefaultHandler());
//		
//		ServletContextHandler context = new ServletContextHandler();
//		
//		server.start();
//		try {
//
//			//Create customer service
//			CustomerService customerService = new CustomerService();
//			
//			//delete customers
////			customerService.delete("100273254288888");
////			customerService.delete("1002732542");
////			customerService.delete("1000000000");
//
//			//Create customer instances
////			Customer customer1 = new Customer("John", "Eider", "Cardona", null, "1002732542", LocalDate.of(1995, 9, 22));
//			Customer customer2 = new Customer("Juan", null, "Garcia", null, "1000000000", LocalDate.of(2000, 3, 11));
//
//			//Save customer instances
//			customerService.save(null);
////			customerService.save(customer2);
//
//			//list customer instances
//			List<Customer> customers = customerService.list();
//			for (Customer customer : customers) {
//				System.out.println("===================================");
//				System.out.println(customer.getFirstName());
//				System.out.println(customer.getSecondName());
//				System.out.println(customer.getFirstLastname());
//				System.out.println(customer.getSecondLastname());
//				System.out.println(customer.getIdentityNumber());
//				System.out.println(customer.getDateOfBirth());
//				System.out.println("===================================");
//			}
//
//			//Find customer by identityNumber
//			Customer customerFind = customerService.findByIdentity("10027325422");
////			System.out.println(customerFind.getFirstName());
////			System.out.println(customerFind.getDateOfBirth());
//			
//			
//			
//			Customer customer3 = new Customer("Juan", "José", "Martinez", null, "1002732542", LocalDate.of(1996, 9, 22));			
//			customerService.update("1002732542", customer3);
////			
////			
//			List<Customer> customers2 = customerService.list();
//			
//			for (Customer customer : customers2) {
//				System.out.println("===================================");
//				System.out.println(customer.getFirstName());
//				System.out.println(customer.getSecondName());
//				System.out.println(customer.getFirstLastname());
//				System.out.println(customer.getSecondLastname());
//				System.out.println(customer.getIdentityNumber());
//				System.out.println(customer.getDateOfBirth());
//				System.out.println("===================================");
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}
