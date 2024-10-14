package com.example.decs.Servlets.Task;

import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Service.TaskService;
import com.example.decs.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/action")
public class actionServlet extends HttpServlet {
    TaskService taskService = new TaskService();
    UserService userService= new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Long id= Long.parseLong(request.getParameter("task"));
        Task task= taskService.findById(id);
        User user=(User) request.getSession().getAttribute("user");
        if (action.equals("accept")){
            taskService.change_status(task,1);
        }else if(action.equals("todo")) {
            taskService.change_status(task,3);
        } else if (action.equals("done")) {
            taskService.change_status(task,4);
        } else{
                if (user.getCoins()<=2){
                    userService.minusCoins(2,user);
                    taskService.change_status(task,2);
                }
        }
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);  // Redirect to the previous page
        } else {
            response.sendRedirect("/defaultPage");  // Fallback to a default page if referer is null
        }
    }
}
