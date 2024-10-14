package com.example.decs.Servlets;

import com.example.decs.Entity.Enums.Status;
import com.example.decs.Entity.Task;
import com.example.decs.Service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/createTask")
public class TaskCreationServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        // Initialize TaskService (assume it's injected or instantiated here)
        taskService = new TaskService(); // adjust according to how your service is instantiated
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get task details from request
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDateStr = request.getParameter("dueDate");  // format yyyy-MM-dd

        // Create a new Task instance
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setDueDate(LocalDate.parse(dueDateStr));
        newTask.setStatus(Status.EN_ATTENTE);// parse date string

        // Create the task through the service layer
        Task isCreated = taskService.createTask(newTask); // Adjust method name if needed

        // Prepare response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (isCreated!=null) {
            out.println("<html><body>");
            out.println("<h2>Task Created Successfully!</h2>");
            out.println("<p>Title: " + title + "</p>");
            out.println("<p>Description: " + description + "</p>");
            out.println("<p>Due Date: " + dueDateStr + "</p>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Failed to Create Task</h2>");
            out.println("</body></html>");
        }
    }

    @Override
    public void destroy() {
        // Clean up if necessary
    }
}
