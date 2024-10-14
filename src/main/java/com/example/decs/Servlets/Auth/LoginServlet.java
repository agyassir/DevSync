package com.example.decs.Servlets.Auth;

import com.example.decs.Service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {


    private UserService userService;

    public LoginServlet() {
        this.userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            if (userService.login(username, password)) {
                // Login successful, set session attribute
                HttpSession session = request.getSession();

                session.setAttribute("user", userService.findUserbyUsername(username));
                response.sendRedirect("./"); // Redirect to a dashboard or home page
            } else {
                // Login failed, set error message
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("/login").forward(request, response);
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., service errors, etc.)
            request.setAttribute("errorMessage", "An error occurred during login: " + e.getMessage());
            request.getRequestDispatcher("pages/Auth/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        if(request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/");
        }
        request.getRequestDispatcher("pages/Auth/login.jsp").forward(request, response);
    }
}
