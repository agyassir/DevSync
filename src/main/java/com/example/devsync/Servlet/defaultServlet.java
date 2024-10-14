package com.example.devsync.Servlet;
import com.example.devsync.Service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "",loadOnStartup = 1)
public class defaultServlet extends HttpServlet{
    @Override
    public void init(){
        TaskService taskService=new TaskService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.getWriter().println("Hello from DefaultServlet!");
    }
}
