package com.softlond.bankingApp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.softlond.bankingApp.controllers.AccountController;
import com.softlond.bankingApp.controllers.CustomerController;
//import com.softlond.client.GUI;
import com.softlond.bankingApp.controllers.TransactionController;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
//		GUI gui = new GUI();
//		gui.start();
		 Server server = new Server(3001);
	        server.setHandler(new DefaultHandler());

	        ServletContextHandler context = new ServletContextHandler();

	        context.setContextPath("/");
	        context.addServlet(CustomerController.class, "/customer/*");
	        context.addServlet(AccountController.class, "/account/*");
	        context.addServlet(TransactionController.class, "/transaction/*");

	        server.setHandler(context);

	        try{
	            server.start();
	            server.join();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
