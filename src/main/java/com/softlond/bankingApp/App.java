package com.softlond.bankingApp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.softlond.bankingApp.controllers.CustomerController;
//import com.softlond.client.GUI;

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
	        // context.addServlet(HolaMundo.class, "/hola/*");
	        context.addServlet(CustomerController.class, "/customer/*");

	        server.setHandler(context);

	        try{
	            server.start();
	            server.join();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
