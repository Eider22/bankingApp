//package com.softlond.client;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import com.softlond.bankingApp.entities.Customer;
//import com.softlond.bankingApp.exceptions.MissingAtributeException;
//import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
//import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
//import com.softlond.bankingApp.services.CustomerService;
//
//public class GUI {
//	private boolean running = true;
//	private CustomerService customerService;
//
//	public GUI() {
//		customerService = new CustomerService();
//	}
//
//	public void start() {
//		System.out.println("Bienvenido al sistema de persistencia de clientes");
//		try {
//
//			while (running) {
//				System.out.println("1. Crear cliente");
//				System.out.println("2. Listar cliente");
//				System.out.println("3. Buscar cliente por identificación");
//				System.out.println("4. Eliminar cliente");
//				System.out.println("5. Editar cliente");
//				System.out.println("6. Buscar cliente por id");
//				System.out.println("0. Salir");
//				Scanner scanner = new Scanner(System.in);
//				int opcion = scanner.nextInt();
//				select(opcion);
//			}
//		} catch (MissingAtributeException e) {
//			System.out.println("→ " + e.getMessage());
//			System.out.println("");
//			start();
//		} catch (Exception e) {
//			System.out.println("→ " + e.getMessage());
//			System.out.println("");
//			start();
//		}
//
//	}
//
//	private void select(int select) throws Exception {
//		switch (select) {
//		case 1:
//			createCustomer();
//			break;
//		case 2:
//			listCustomer();
//			break;
//		case 3:
//			findByIdentity();
//			break;
//		case 4:
//			delete();
//			break;
//		case 5:
//			update();
//			break;
//		case 6:
//			findById();
//			break;
//		case 0:
//			finish();
//			break;
//		default:
//			System.out.println("Opcion no valida");
//			break;
//		}
//	}
//
//	private void createCustomer() throws Exception {
//
//		System.out.println("Crear cliente");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Primer nombre: ");
//		String firstName = scanner.nextLine();
//		System.out.println("Segundo nombre: ");
//		String secondName = scanner.nextLine();
//		System.out.println("Primer apellido: ");
//		String firstLastname = scanner.nextLine();
//		System.out.println("Segundo apellido: ");
//		String secondLastname = scanner.nextLine();
//		System.out.println("Número de identificación: ");
//		String identityNumber = scanner.nextLine();
//		System.out.println("Fecha de nacimiento(AAAA-MM-DD): ");
//		String dateOfBirth = scanner.nextLine();
//
//		Map<String, Object> customerMap = new HashMap<>();
//		customerMap.put("firstName", firstName);
//		customerMap.put("secondName", secondName);
//		customerMap.put("firstLastname", firstLastname);
//		customerMap.put("secondLastname", secondLastname);
//		customerMap.put("identityNumber", identityNumber);
//		customerMap.put("dateOfBirth", dateOfBirth);
//
//		CustomerRepositoryDto createdCustomer = customerService.save(customerMap);
//		if (createdCustomer == null) {
//			throw new Exception("Falló la creación del cliente");
//		}
//		System.out.println("===================================");
//		System.out.println("Cliente creado");
//		System.out.println("===================================");
//		System.out.println(createdCustomer.getFirstName());
//		System.out.println(createdCustomer.getSecondName());
//		System.out.println(createdCustomer.getFirstLastname());
//		System.out.println(createdCustomer.getSecondLastname());
//		System.out.println(createdCustomer.getIdentityNumber());
//		System.out.println(createdCustomer.getDateOfBirth());
//		System.out.println("===================================");
//
//	}
//
//	private void listCustomer() {
//		List<CustomerRepositoryDto> customersDb = customerService.list();
//
//		System.out.println("===================================");
//		System.out.println("Listando clientes");
//		for (CustomerRepositoryDto customerDb : customersDb) {
//			System.out.println("===================================");
//			System.out.println(customerDb.getId());
//			System.out.println(customerDb.getFirstName());
//			System.out.println(customerDb.getSecondName());
//			System.out.println(customerDb.getFirstLastname());
//			System.out.println(customerDb.getSecondLastname());
//			System.out.println(customerDb.getIdentityNumber());
//			System.out.println(customerDb.getDateOfBirth());
//			System.out.println("===================================");
//		}
//	}
//
//	private void findByIdentity() throws NotFoundCustomerException {
//		System.out.println("Buscar cliente por identificación");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Identificacion: ");
//		String identificacion = scanner.nextLine();
//		Customer customerDb = customerService.findByIdentity(identificacion);
//		System.out.println("===================================");
//		System.out.println("Cliente encontrado");
//		System.out.println("===================================");
//		System.out.println(customerDb.getId());
//		System.out.println(customerDb.getFirstName());
//		System.out.println(customerDb.getSecondName());
//		System.out.println(customerDb.getFirstLastname());
//		System.out.println(customerDb.getSecondLastname());
//		System.out.println(customerDb.getIdentityNumber());
//		System.out.println(customerDb.getDateOfBirth());
//		System.out.println("===================================");
//	}
//
//	private void findById() throws NotFoundCustomerException {
//		System.out.println("Buscar cliente por id");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Id: ");
//		String id = scanner.nextLine();
//		CustomerRepositoryDto customerDb = customerService.findById(id);
//		System.out.println("===================================");
//		System.out.println("Cliente encontrado");
//		System.out.println("===================================");
//		System.out.println(customerDb.getId());
//		System.out.println(customerDb.getFirstName());
//		System.out.println(customerDb.getSecondName());
//		System.out.println(customerDb.getFirstLastname());
//		System.out.println(customerDb.getSecondLastname());
//		System.out.println(customerDb.getIdentityNumber());
//		System.out.println(customerDb.getDateOfBirth());
//		System.out.println("===================================");
//	}
//
//	private void delete() throws Exception {
//		System.out.println("Eliminar cliente");
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Identificacion: ");
//		String identificacion = scanner.nextLine();
//		if (!customerService.delete(identificacion)) {
//			throw new Exception("Falló la eliminación del cliente");
//		}
//		;
//		System.out.println("Cliente eliminado");
//
//	}
//
//	private void update() throws NotFoundCustomerException, MissingAtributeException, Exception {
//		try {
//			System.out.println("Editar cliente");
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("Número de identificación del cliente que quiere editar: ");
//			String identityNumber = scanner.nextLine();
//
//			Customer customerDb = customerService.findByIdentity(identityNumber);
//			System.out.println("===================================");
//			System.out.println("Este es el cliente que quieres editar");
//			System.out.println("===================================");
//			System.out.println(customerDb.getFirstName());
//			System.out.println(customerDb.getSecondName());
//			System.out.println(customerDb.getFirstLastname());
//			System.out.println(customerDb.getSecondLastname());
//			System.out.println(customerDb.getDateOfBirth());
//			System.out.println("===================================");
//
//			System.out.println("NOTA: Si desea dejar un atributo como estaba solo oprima enter para saltarlo");
//			System.out.println("Primer nombre: ");
//			String firtsName = scanner.nextLine();
//			System.out.println("Segundo nombre: ");
//			String secondName = scanner.nextLine();
//			System.out.println("Primer apellido: ");
//			String firstLastname = scanner.nextLine();
//			System.out.println("Segundo apellido: ");
//			String secondLastname = scanner.nextLine();
//			System.out.println("Fecha de nacimiento(AAAA-MM-DD): ");
//			String dateOfBirth = scanner.nextLine();
//
//			LocalDate newDateOfBirth = null;
//			if (dateOfBirth == "") {
//				newDateOfBirth = customerDb.getDateOfBirth();
//			} else {
//				newDateOfBirth = LocalDate.parse(dateOfBirth);
//			}
//
//			Customer newCustomer = new Customer(firtsName, secondName, firstLastname, secondLastname, identityNumber,
//					newDateOfBirth);
//			Customer updatedCustomer = customerService.update(identityNumber, newCustomer);
//			if (updatedCustomer == null) {
//				throw new Exception("Falló la edición del cliente");
//			}
//			System.out.println("===================================");
//			System.out.println("Cliente editado");
//			System.out.println("===================================");
//			System.out.println(updatedCustomer.getFirstName());
//			System.out.println(updatedCustomer.getSecondName());
//			System.out.println(updatedCustomer.getFirstLastname());
//			System.out.println(updatedCustomer.getSecondLastname());
//			System.out.println(updatedCustomer.getIdentityNumber());
//			System.out.println(updatedCustomer.getDateOfBirth());
//			System.out.println("===================================");
//
//		} catch (DateTimeParseException e) {
//			throw new Exception("Formato invalido para fecha");
//		}
//
//	}
//
//	private void finish() {
//		System.out.println("Salir");
//		running = false;
//	}
//
//}
