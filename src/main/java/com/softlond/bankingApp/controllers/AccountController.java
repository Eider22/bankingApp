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
import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;
import com.softlond.bankingApp.services.AccountService;
import com.softlond.bankingApp.services.CustomerService;
import com.softlond.bankingApp.services.contracts.IAccountService;

public class AccountController extends HttpServlet {
	private IAccountService accountService;
	private ObjectMapper mapper;

	public AccountController() {
		this.accountService = new AccountService();
		this.mapper = new ObjectMapper();
	}
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path == null) {
			try {
				List<?> accounts = this.accountService.list();
				String json = mapper.writeValueAsString(accounts);
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
		} else {
			switch (path) {
			case "/listByCustomerId":
				Integer id = Integer.parseInt(request.getParameter("id"));
				try {
					List<?> accounts = this.accountService.listByCustomerId(id);
					String json = mapper.writeValueAsString(accounts);
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
			Map<String, Object> accountMapp = mapper.readValue(request.getInputStream(), HashMap.class);
			try {
				AccountControllerDto savedAccount = accountService.save(accountMapp);
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.setContentType("application/json");
				String json = mapper.writeValueAsString(savedAccount);
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
	}

}
