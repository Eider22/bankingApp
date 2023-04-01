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
import com.softlond.bankingApp.services.CustomerService;
import com.softlond.bankingApp.services.contracts.ICustomerService;

public class CustomerController extends HttpServlet {

	private ICustomerService customerService;
	private ObjectMapper mapper;

	public CustomerController() {
		this.customerService = new CustomerService();
		mapper = new ObjectMapper();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path == null) {
			try {
				List<?> customers = customerService.list();
				String json = mapper.writeValueAsString(customers);
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
			case "/findById":
				Integer id = Integer.parseInt(request.getParameter("id"));
				try {
					CustomerControllerDto customer = customerService.findById(id);
					String json = mapper.writeValueAsString(customer);
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
			case "/findByIdentification":
				String identification = request.getParameter("identification");
				try {
					CustomerControllerDto customer = customerService.findByIdentity(identification);
					String json = mapper.writeValueAsString(customer);
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
			Map<String, Object> customerMap = mapper.readValue(request.getInputStream(), HashMap.class);
			try {
				CustomerControllerDto savedCustomer = customerService.save(customerMap);
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
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getContentType();
		if (content == "application/json") {
			Map<String, Object> customerMap = mapper.readValue(request.getInputStream(), HashMap.class);
			Integer id = Integer.parseInt(request.getParameter("id"));

			try {
				CustomerControllerDto updatedCustomer = customerService.update(id, customerMap);

				response.setStatus(HttpServletResponse.SC_OK);
				String json = mapper.writeValueAsString(updatedCustomer);
				response.setContentType("application/json");
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

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("application/json");
		try {
			boolean ok = customerService.delete(id);
			if (!ok) {
				response.getWriter().println("No se pudo eliminar el cliente");
			}
			String json = mapper.writeValueAsString("Cliente eliminado");
			response.getWriter().println(json);
		} catch (Exception e) {
			response.setStatus(404);
			Map<String, String> error = new HashMap<>();
			error.put("error", e.getMessage());
			String json = mapper.writeValueAsString(error);
			response.setContentType("application/json");
			response.getWriter().println(json);
		}

	}

}
