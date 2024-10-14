<%@ page import="com.example.decs.Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 2024-10-08
  Time: 10:02 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!DOCTYPE html>
  <html>
  <head>
    <title>JSP - Hello World</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <style>
      /* From Uiverse.io by Shoh2008 */
      .loader {
        transform: translateZ(1px);
      }

      .loader:after {
        content: '$';
        display: inline-block;
        width: 15px;
        height: 15px;
        border-radius: 50%;
        text-align: center;
        line-height: 8px;
        font-size: 10px;
        font-weight: bold;
        background: #FFD700;
        color: #DAA520;
        border: 4px double;
        box-sizing: border-box;
        box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, .1);
        animation: coin-flip 4s cubic-bezier(0, 0.2, 0.8, 1) infinite;
      }

      @keyframes coin-flip {
        0%, 100% {
          animation-timing-function: cubic-bezier(0.5, 0, 1, 0.5);
        }

        0% {
          transform: rotateY(0deg);
        }

        50% {
          transform: rotateY(1800deg);
          animation-timing-function: cubic-bezier(0, 0.5, 0.5, 1);
        }

        100% {
          transform: rotateY(3600deg);
        }
      }

    </style>
  </head>
  <body>
  <header class="flex flex-col items-center justify-between max-w-full md:max-w-6xl px-2 md:px-6 py-5 mx-auto md:flex-row">
    <a href="/" class="text-indigo-900 z-10 active">
      <img src="https://www.svgrepo.com/show/489282/brand.svg" class="w-24 py-8 md:py-0 g-image">
    </a>
    <nav class="z-10">
      <%
        User user=(User) session.getAttribute("user");
      if (user==null){
      %>
      <ul class="flex flex-row items-center px-6 py-4 text-indigo-100 bg-indigo-900 rounded-lg">
        <li class="pr-8">
          <a href="./login">login</a>
        </li>
        <li class="pr-8">
          <a href="./signup">signup</a>
        </li>
      </ul>
      <%
      }else {
      %>
      <ul class="flex flex-row items-center px-6 py-4 text-indigo-100 bg-indigo-900 rounded-lg">

        <li class="">
          <p class="text-white"><%=user.getCoins()%></p>
        </li>

        <li class="pr-8">
          <div class="loader"></div>
        </li>
        <li class="pr-8 ">
          <a href="./profile">profile</a>
        </li>
        <li class="pr-8">
          <a href="./task">MyTasks</a>
        </li>
        <li >
          <a href="./logout">logout</a>
        </li>
      </ul>
      <%
        }%>
    </nav>
  </header>

</body>
</html>
