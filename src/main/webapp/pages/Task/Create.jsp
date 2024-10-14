<%@ page import="com.example.decs.Entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.decs.Entity.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="com.example.decs.Entity.Tag" %>
<%@ page import="com.example.decs.Entity.Enums.Status" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 2024-10-08
  Time: 9:50 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../includes/header.jsp"></jsp:include>

<div class="flex flex-col w-full items-center pt-4 gap-4 bg-gradient-to-b from-blue-600 via-blue-300 to-blue-100">
    <p class="text-4xl md:text-6xl font-extrabold text-white"> <% User user=(User) session.getAttribute("user");
        List<Tag> tags=(List<Tag>) request.getAttribute("tags");
        if (!user.isChef()) { %>
        Your Tasks</p>
    <% } else { %>
    All Tasks
    <% } %></p>

    <form action="" class="max-w-[480px] w-full px-4">
        <div class="relative">
            <input type="text" name="q" class="w-full border h-12 shadow p-4 rounded-full" placeholder="search">
            <button type="submit">
                <svg class="text-gray-400 h-5 w-5 absolute top-3.5 right-3 fill-current"
                     xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1"
                     x="0px" y="0px" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 56.966 56.966;"
                     xml:space="preserve">
                        <path
                                d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z" />
                    </svg>
            </button>
        </div>
    </form>
</div>
<div class="w-100 flex justify-center items-center py-3 gap-3">
<button data-modal-target="add-modal" data-modal-toggle="add-modal" class="bg-gray-600 rounded-full w-40 h-10 text-white font-semibold">
    <div class="flex gap-3   justify-center items-center">
      <span>
          <svg class="w-5 h-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" >
           <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 6h9.75M10.5 6a1.5 1.5 0 11-3 0m3 0a1.5 1.5 0 10-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-9.75 0h9.75" />
          </svg>
      </span>
        <span class="text-lg">Add Task</span>
    </div>
</button>
    <button data-modal-target="tag-modal" data-modal-toggle="tag-modal" class="bg-gray-600 rounded-full w-40 h-10 text-white font-semibold">
        <div class="flex gap-3   justify-center items-center">
      <span>
          <svg class="w-5 h-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" >
           <path stroke-linecap="round" stroke-linejoin="round" d="M10.5 6h9.75M10.5 6a1.5 1.5 0 11-3 0m3 0a1.5 1.5 0 10-3 0M3.75 6H7.5m3 12h9.75m-9.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-3.75 0H7.5m9-6h3.75m-3.75 0a1.5 1.5 0 01-3 0m3 0a1.5 1.5 0 00-3 0m-9.75 0h9.75" />
          </svg>
      </span>
            <span class="text-lg">Add Tag</span>
        </div>
    </button></div>

<div class="shadow-lg rounded-lg overflow-hidden mx-4 md:mx-10">
    <table class="w-full table-fixed">
        <thead>
        <tr class="bg-gray-100">
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Title</th>
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Description</th>
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">User</th>
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Status</th>
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Due date</th>
            <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">tags</th>
        </tr>
        </thead>
        <tbody class="bg-white">
        <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            List<User> users =(List<User>) request.getAttribute("users");
            for (Task task : tasks) { %>
        <tr>
            <td class="py-4 px-6 border-b border-gray-200"><%= task.getTitle() %></td>
            <td class="py-4 px-6 border-b border-gray-200 truncate"><%= task.getDescription() %></td>
            <td class="py-4 px-6 border-b border-gray-200">
                <% LocalDate today=(LocalDate) request.getAttribute("today");
                    long daysUntilDue = ChronoUnit.DAYS.between(today, task.getDueDate());
                    if (!(daysUntilDue <= 3 && daysUntilDue >= 0 || task.getStatus()== Status.TERMINE)){%>
                <button data-modal-target="crud-modal<%=task.getId()%>" data-modal-toggle="crud-modal<%=task.getId()%>" class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
                    <% if (task.getAssignedTo() != null) { %>
                    <option selected><%= task.getAssignedTo().getUsername() %></option>
                    <% } else { %>
                    unassigned
                    <%}%>
                </button>
                <div id="crud-modal<%=task.getId()%>" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
                    <div class="relative p-4 w-full max-w-md max-h-full">
                        <!-- Modal content -->
                        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                            <!-- Modal header -->
                            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                                <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                                    Create New Product
                                </h3>
                                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="crud-modal<%=task.getId()%>">
                                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                                    </svg>
                                    <span class="sr-only">Close modal</span>
                                </button>
                            </div>
                            <!-- Modal body -->
                            <form id="assign-user-form" action="./task" method="post">
                                <input type="hidden" name="action" value="assignto">
                                <input type="hidden" name="task" value="<%= task.getId() %>">

                                <label for="user-select" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                    Select an user
                                </label>

                                <select id="user-select" name="usera" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                    <% if (task.getAssignedTo() != null) { %>
                                    <option value="<%= task.getAssignedTo().getId() %>" selected>
                                        <%= task.getAssignedTo().getUsername() %>
                                    </option>
                                    <% } else { %>
                                    <option value="" selected>Unassigned</option>
                                    <% } %>

                                    <% for (User usera : users) { %>
                                    <option value="<%= usera.getId() %>" <%= task.getAssignedTo() != null && task.getAssignedTo().getId() == usera.getId() ? "selected" : "" %>>
                                        <%= usera.getUsername() %>
                                    </option>
                                    <% } %>
                                </select>

                                <!-- Optionally, a submit button if needed -->
                                <button type="submit" id="assign-user-submit" class="bg-blue-500 text-white py-2 px-4 rounded mt-4">
                                    Save Changes
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
<%}else{%>
                    <button disabled class="block text-white bg-gray-200 hover:bg-gray-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-red-600 dark:hover:bg-gray-700 dark:focus:ring-blue-800" type="button">
                <% if (task.getAssignedTo() != null) { %>
                <option selected><%= task.getAssignedTo().getUsername() %></option>
                <% } else { %>
                unassigned
                <%}%>
                </button>
<%}%>
            </td>
            <td class="py-4 px-6 border-b border-gray-200">
            <span class="<%= task.getStatus().name().equalsIgnoreCase("DONE") ? "bg-green-500" : "bg-yellow-500" %> text-white py-1 px-2 rounded-full text-xs">
                <%= task.getStatus().name() %>
            </span>
            </td>
            <td class="py-4 px-6 border-b border-gray-200"><%= task.getDueDate() != null ? task.getDueDate().toString() : "No due date" %></td>
            <td class="py-4 px-6 border-b border-gray-200">
                <%for (Tag tag: task.getTags()){%>
                <span class="border border-red-300 rounded-full px-4 text-sm text-gray-700 py-0.5">
              <%=tag.getName()%>
                    </span>
                <%}%>
            </td>
        </tr>
        <% } %>
        </tbody>



        <!-- Add more rows here -->
        </tbody>
    </table>
</div>






<div id="add-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
    <div class="relative p-4 w-full max-w-md max-h-full">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                    Create New Product
                </h3>
                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="add-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
            </div>
            <!-- Modal body -->
            <form id="add-user-form" action="./task" method="post">
                <input type="hidden" name="action" value="create">

                <label for="user-select" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    add a task
                </label>
                <div class="mb-4">
                    <label for="title" class="block text-gray-700 text-sm font-bold mb-2">Title</label>
                    <input type="text" name="title" id="title" placeholder="Enter task title" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>

                <!-- Description -->
                <div class="mb-4">
                    <label for="description" class="block text-gray-700 text-sm font-bold mb-2">Description</label>
                    <textarea name="description" id="description" placeholder="Enter task description" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required></textarea>
                </div>


                <!-- Due Date -->
                <div class="mb-4">
                    <label for="dueDate" class="block text-gray-700 text-sm font-bold mb-2">Due Date</label>
                    <input type="date" name="dueDate" id="dueDate" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>
                <div class="mb-4">
                    <label for="tags" class="block text-gray-700 text-sm font-bold mb-2">Select Tags</label>
                    <% for (Tag tag : tags) { %>
                    <div>
                        <input type="checkbox" name="tags" value="<%= tag.getId() %>">
                        <label><%= tag.getName() %></label>
                    </div>
                    <% } %>
                </div>

                <!-- Optionally, a submit button if needed -->
                <button type="submit" id="add-user-submit" class="bg-blue-500 text-white py-2 px-4 rounded mt-4">
                    Save Changes
                </button>
            </form>
        </div>
    </div>
</div>
<div id="tag-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
    <div class="relative p-4 w-full max-w-md max-h-full">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                <h3 class="text-lg font-semibold text-gray-900 dark:text-white">
                    Create New Product
                </h3>
                <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white" data-modal-toggle="tag-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
            </div>
            <!-- Modal body -->
            <form id="add-tag-form" action="./tag" method="post">
                <label for="user-select" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                    add a tag
                </label>
                <div class="mb-4">
                    <label for="title" class="block text-gray-700 text-sm font-bold mb-2">Title</label>
                    <input type="text" name="title"  placeholder="Enter tag title" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>

                <!-- Optionally, a submit button if needed -->
                <button type="submit" id="add-tag-submit" class="bg-blue-500 text-white py-2 px-4 rounded mt-4">
                    Save Tag
                </button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
<script>
    // Get today's date and add 3 days
    let today = new Date();
    today.setDate(today.getDate() + 3);  // Adds 3 days to today's date

    // Format the date as YYYY-MM-DD for the input field
    let minDate = today.toISOString().split('T')[0];

    // Set the min attribute of the dueDate input
    document.getElementById('dueDate').setAttribute('min', minDate);
</script>
</body>

</html>
