package com.example.decs.Servlets.Task;

import com.example.decs.Entity.Enums.Status;
import com.example.decs.Entity.Tag;
import com.example.decs.Entity.Task;
import com.example.decs.Entity.User;
import com.example.decs.Service.TagService;
import com.example.decs.Service.TaskService;
import com.example.decs.Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    TaskService taskService = new TaskService();
    UserService userService= new UserService();
    TagService tagService=new TagService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        }else if ("edit".equals(action)) {
            updateTask(request, response);
        } else if ("assignto".equals(action)) {
            assignTo(request,response);
        }
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);  // Redirect to the previous page
        } else {
            response.sendRedirect("/defaultPage");  // Fallback to a default page if referer is null
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= (HttpSession) request.getSession(false);
        User user = (User) session.getAttribute("user");

        if(user == null) {
            request.getRequestDispatcher("pages/Auth/login.jsp").forward(request, response);
        }
        request.setAttribute("tags",tagService.getAllTags());
        LocalDate currentDate = LocalDate.now();
        request.setAttribute("today",currentDate);
        request.setAttribute("tags",tagService.getAllTags());
        if (user.isChef()){
            request.setAttribute("tasks",taskService.findall());
            request.setAttribute("users",userService.findAll());
            request.getRequestDispatcher("pages/Task/Create.jsp").forward(request, response);
        }
        else{
        request.setAttribute("tasks",taskService.findByUser(user));
        request.getRequestDispatcher("pages/Task/User.jsp").forward(request, response);
        }

    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDateStr = request.getParameter("dueDate");  // format yyyy-MM-dd
        // Create a new Task instance
        String[] tagIds = request.getParameterValues("tags");
        List<Tag> tagSet = new ArrayList<>();
        if (tagIds != null) {
            for (String tagId : tagIds) {
                Long id = Long.parseLong(tagId);
                Tag tag = tagService.getTagById(id); // Assuming you have a service method to get tags by ID
                tagSet.add(tag);
            }
        }
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(description);
        newTask.setDueDate(LocalDate.parse(dueDateStr));
        newTask.setStatus(Status.EN_ATTENTE);
        newTask.setTags(tagSet);// parse date string

        // Create the task through the service layer
        Task isCreated = taskService.createTask(newTask); // Adjust method name if needed

    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String statusStr = request.getParameter("status");
        String dueDateStr = request.getParameter("dueDate");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));

        // Convert date strings to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Convert status string to Status enum
        Status status = Status.valueOf(statusStr);

        // Create updated Task object
        Task updatedTask = null;
        updatedTask.setTitle(title);
        updatedTask.setDescription(description);
        updatedTask.setStatus(status);
        updatedTask.setDueDate(dueDate);
        updatedTask.setStartDate(startDate);
        updatedTask.setEndDate(endDate);
        updatedTask.setCompleted(isCompleted);

        // Call the service to update the task
        taskService.updateTask(updatedTask,taskId);
    }

    public void assignTo(HttpServletRequest request,HttpServletResponse response){
        long id=Long.parseLong(request.getParameter("usera"));
        long task=Long.parseLong(request.getParameter("task"));
        Task taskToUpdate=taskService.findById(task);
        User user=userService.findById(id);
        taskService.assignto(user,taskToUpdate);
    }
}
