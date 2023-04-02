package com.softlond.bankingApp.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;
import com.softlond.bankingApp.controllers.dtos.TransactionControllerDto;
import com.softlond.bankingApp.services.CustomerService;
import com.softlond.bankingApp.services.TransactionService;
import com.softlond.bankingApp.services.contracts.ICustomerService;
import com.softlond.bankingApp.services.contracts.ITransactionService;

public class TransactionController extends HttpServlet{
	
	private ITransactionService transactionService;
	private ObjectMapper mapper;

	public TransactionController() {
		this.transactionService = new TransactionService();
		mapper = new ObjectMapper();
	}
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path == null) {
			response.setStatus(404);
			Map<String, String> error = new HashMap<>();
			error.put("error", "No se encontró el recurso");
			String json = mapper.writeValueAsString(error);
			response.setContentType("application/json");
			response.getWriter().println(json);
		} else {
			switch (path) {
			case "/listByAccountId":
				Integer id = Integer.parseInt(request.getParameter("id"));
				try {
					List<?> transactions = this.transactionService.listByAccountId(id);
					String json = mapper.writeValueAsString(transactions);
					response.setContentType("application/json");
					response.getWriter().println(json);
				} catch (Exception e) {
					response.setStatus(404);
					Map<String, String> error = new HashMap<>();
					error.put("error", e.getMessage());
					String json = mapper.writeValueAsString(error);
					response.setContentType("application/json");
					response.getWriter().println(json);
				}
				break;
			default:
				response.setStatus(404);
				Map<String, String> error = new HashMap<>();
				error.put("error", "No se encontro el recurso");
				String json = mapper.writeValueAsString(error);
				response.setContentType("application/json");
				response.getWriter().println(json);
				break;
			}

		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String content = request.getContentType();

		if (content != null && content.equals("application/json")) {
			Map<String, Object> transactionMap = mapper.readValue(request.getInputStream(), HashMap.class);
			try {
				TransactionControllerDto savedCustomer = transactionService.save(transactionMap);
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.setContentType("application/json");
				String json = mapper.writeValueAsString(savedCustomer);
				response.getWriter().println(json);

			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				Map<String, String> error = new HashMap<>();
				error.put("error", e.getMessage());
				String json = mapper.writeValueAsString(error);
				response.setContentType("application/json");
				response.getWriter().println(json);
			}

		} else {
			response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			Map<String, String> error = new HashMap<>();
			error.put("error", "El contenido debe ser JSON");
			String json = mapper.writeValueAsString(error);
			response.setContentType("application/json");
			response.getWriter().println(json);
		}
		
		
		
		
//		String content = request.getContentType();
//
//		if (content != null && content.equals("application/json")) {
//			Map<String, Object> transactionMap = mapper.readValue(request.getInputStream(), HashMap.class);
//			try {
//				TransactionControllerDto savedCustomer = transactionService.save(transactionMap);
//				response.setStatus(HttpServletResponse.SC_CREATED);
//				response.setContentType("application/json");
//				String json = mapper.writeValueAsString(savedCustomer);
//				response.getWriter().println(json);
//
//			} catch (Exception e) {
//				response.setStatus(HttpServletResponse.SC_CONFLICT);
//				Map<String, String> error = new HashMap<>();
//				error.put("error", e.getMessage());
//				String json = mapper.writeValueAsString(error);
//				response.setContentType("application/json");
//				response.getWriter().println(json);
//			}
//
//		} else {
//			response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
//			Map<String, String> error = new HashMap<>();
//			error.put("error", "El contenido debe ser JSON");
//			String json = mapper.writeValueAsString(error);
//			response.setContentType("application/json");
//			response.getWriter().println(json);
//		}
	}
}
