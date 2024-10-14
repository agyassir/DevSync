<%@ page import="com.example.decs.Entity.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.decs.Entity.User" %>
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
<p class="text-4xl p-4 md:text-6xl font-extrabold text-white">
  My Tasks</p>
</div>
<div class="shadow-lg rounded-lg overflow-hidden mx-4 md:mx-10 my-5">
  <table class="w-full table-fixed">
    <thead>
    <tr class="bg-gray-100">
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Title</th>
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Description</th>
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">User</th>
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Status</th>
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Due date</th>
      <th class="w-1/6 py-4 px-6 text-left text-gray-600 font-bold uppercase">Action</th>
    </tr>
    </thead>
    <tbody class="bg-white">
    <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
      for (Task task : tasks) { %>
    <tr>
      <td class="py-4 px-6 border-b border-gray-200"><%= task.getTitle() %></td>
      <td class="py-4 px-6 border-b border-gray-200 truncate"><%= task.getDescription() %></td>
      <td class="py-4 px-6 border-b border-gray-200">
        <button  class="block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
          <% if (task.getAssignedTo() != null) { %>
          <option selected><%= task.getAssignedTo().getUsername() %></option>
          <% } else { %>
          unassigned
          <%}%>
        </button>

      </td>
      <td class="py-4 px-6 border-b border-gray-200">
            <span class="<%= task.getStatus().name().equalsIgnoreCase("DONE") ? "bg-green-500" : "bg-yellow-500" %> text-white py-1 px-2 rounded-full text-xs">
                <%= task.getStatus().name() %>
            </span>
      </td>
      <td class="py-4 px-6 border-b border-gray-200"><%= task.getDueDate() != null ? task.getDueDate().toString() : "No due date" %></td>
      <td class="px-6 py-4 whitespace-nowrap">
        <form action="./action" method="post">
          <input type="hidden" name="task" value="<%=task.getId()%>">
        <%if (task.getStatus()== Status.EN_ATTENTE){%>

 <!-- Pass task ID -->

          <!-- Accept Button -->
          <button type="submit" name="action" value="accept"
                  class="px-4 py-2 font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">
            Start
          </button>

          <!-- Refuse Button -->
          <button type="submit" name="action" value="refuse"
                  class="ml-2 px-4 py-2 font-medium text-white bg-red-600 rounded-md hover:bg-red-500 focus:outline-none focus:shadow-outline-red active:bg-red-600 transition duration-150 ease-in-out">
            Refuse
          </button><%} else if (task.getStatus()== Status.AFAIRE) {%>
        <button type="submit" name="action" value="todo"
        class="px-4 py-2 font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">
                Start
                </button>
      <%} else if (task.getStatus()== Status.EN_COURS) {%>
          <button type="submit" name="action" value="done"
                  class="px-4 py-2 font-medium text-white bg-blue-600 rounded-md hover:bg-blue-500 focus:outline-none focus:shadow-outline-blue active:bg-blue-600 transition duration-150 ease-in-out">
            Done
          </button><%}
      %></form></td>
    </tr>

    <% } %>

    </tbody>



    <!-- Add more rows here -->
    </tbody>
  </table>
</div>

</body>
</html>
