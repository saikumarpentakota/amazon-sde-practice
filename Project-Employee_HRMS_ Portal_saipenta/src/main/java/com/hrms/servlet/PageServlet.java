package com.hrms.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/employees", "/payroll", "/attendance", "/leave"})
public class PageServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        String jspPage = "/WEB-INF/jsp" + path + ".jsp";
        
        request.getRequestDispatcher(jspPage).forward(request, response);
    }
}