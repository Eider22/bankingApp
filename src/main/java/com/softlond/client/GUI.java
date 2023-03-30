package com.softlond.client;



import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.services.CustomerService;

public class GUI {
    private boolean running = true;
    private CustomerService customerService;

    public GUI() {
        customerService = new CustomerService();
    }

	public void start() {
        System.out.println("Bienvenido al sistema de persistencia de clientes");

        while (running) {
            System.out.println("1. Crear cliente");
            System.out.println("2. Listar cliente");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("6. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            select(opcion);
        }

    }

	private void select(int select) {
        switch (select) {
            case 1:
                createCustomer();
                break;
            case 2:
                listCustomer();
                break;
            case 3:
                findByIdentity();
                break;
            case 4:
                delete();
                break;
            case 6:
				finish();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void createCustomer() {    	
    	try {			
			System.out.println("Crear cliente");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Primer nombre: ");
			String firtsName = scanner.nextLine();
			System.out.println("Segundo nombre: ");
			String secondName = scanner.nextLine();
			System.out.println("Primer apellido: ");
			String firstLastname = scanner.nextLine();
			System.out.println("Segundo apellido: ");
			String secondLastname = scanner.nextLine();
			System.out.println("Número de identificación: ");
			String identityNumber = scanner.nextLine();
			System.out.println("Fecha de nacimiento(AAAA-MM-DD): ");
			LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

			Customer customer = new Customer(secondLastname, secondName, firstLastname, secondLastname, identityNumber,
					dateOfBirth);
			customerService.save(customer);
			 System.out.println("Cliente creado");
		} catch (DateTimeParseException e) {
			System.out.println("Formato incorrecto de fecha");
		}

    }

    private void listCustomer() {
        System.out.println("Listando clientes");
        List<Customer> customersDb = customerService.list();

        for (Customer customerDb : customersDb) {
            System.out.println(customerDb.getFirstName());
        }
    }

    private void findByIdentity() {
        System.out.println("Buscar cliente");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Identificacion: ");
        String identificacion = scanner.nextLine();
        try {
            Customer encontrada = customerService.findByIdentity(identificacion);
            System.out.println("Cliente encontrado " + encontrada.getFirstName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete() {
        System.out.println("Eliminar cliente");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Identificacion: ");
        String identificacion = scanner.nextLine();
        try {
            customerService.delete(identificacion);
            System.out.println("Cliente eliminado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void finish() {
        System.out.println("Salir");
        running = false;
    }

}
