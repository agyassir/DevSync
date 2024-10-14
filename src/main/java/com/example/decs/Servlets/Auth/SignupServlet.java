package com.example.decs.Servlets.Auth;

import com.example.decs.Service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private UserService userService ;

    public SignupServlet() {
        this.userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String password = request.getParameter("password");
        String manager = request.getParameter("is_chef");
        boolean isManager = (manager != null && manager.equals("yes"));
        try {
            userService.signup(username,firstname,lastname, email, password,isManager);
            response.sendRedirect("./login");
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("pages/Auth/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/Auth/signup.jsp").forward(req, resp);

    }
}

