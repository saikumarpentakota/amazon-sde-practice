package org.example.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController extends HttpServlet {

    private final Gson gson = new Gson();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // CORS
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        String pathInfo = req.getPathInfo(); // may be /{id} or null
        try (PrintWriter out = resp.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Employee> employees = employeeRepository.findAll();
                out.write(gson.toJson(employees));
            } else {
                String id = pathInfo.substring(1);
                Employee emp = employeeRepository.findById(id);
                if (emp != null) {
                    out.write(gson.toJson(emp));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    JsonObject jo = new JsonObject();
                    jo.addProperty("error", "Employee not found");
                    out.write(gson.toJson(jo));
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Database error\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
        }
        try {
            Employee employee = gson.fromJson(sb.toString(), Employee.class);
            Employee saved = employeeRepository.save(employee);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(gson.toJson(saved));
        } catch (SQLException ex) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"Could not save employee\"}");
        } catch (Exception ex) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Invalid request\"}");
        }
    }
}