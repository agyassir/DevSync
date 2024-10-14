package com.example.decs.Servlets;

import com.example.decs.schedule.CoinScheduler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "",loadOnStartup = 1)
public class defaultServlet extends HttpServlet{
    private String message;

    public void init() {
        try {

            CoinScheduler coinScheduler = new CoinScheduler();
            coinScheduler.startCoinIncrementJob();
        } catch (Exception e) {
            // Handle the exception
            message = "Error connecting to the database: " + e.getMessage();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
