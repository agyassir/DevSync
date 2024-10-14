<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>


<div class="flex h-screen bg-indigo-700">
    <div class="w-full max-w-xs m-auto bg-indigo-100 rounded p-5">
        <header>
            <img class="w-20 mx-auto mb-5" src="https://img.icons8.com/fluent/344/year-of-tiger.png" />
        </header>
        <% String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { %>
        <p style="color:red;"><%= errorMessage %></p>
        <% } %>
        <form action="./signup" method="POST">
            <div>
                <label class="block mb-2 text-indigo-500" for="username">Firstname</label>
                <input class="w-full p-2 mb-6 text-indigo-700 border-b-2 border-indigo-500 outline-none focus:bg-gray-300"
                       type="text" id="firstname" name="fname" placeholder="Enter your firstname" required>
            </div>
            <div>
                <label class="block mb-2 text-indigo-500" for="username">Lastname</label>
                <input class="w-full p-2 mb-6 text-indigo-700 border-b-2 border-indigo-500 outline-none focus:bg-gray-300"
                       type="text" id="lastname" name="lname" placeholder="Enter your lastname" required>
            </div>
            <div>
                <label class="block mb-2 text-indigo-500" for="username">Username</label>
                <input class="w-full p-2 mb-6 text-indigo-700 border-b-2 border-indigo-500 outline-none focus:bg-gray-300"
                       type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>

            <div>
                <label class="block mb-2 text-indigo-500" for="email">Email</label>
                <input class="w-full p-2 mb-6 text-indigo-700 border-b-2 border-indigo-500 outline-none focus:bg-gray-300"
                       type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div>
                <label class="block mb-2 text-indigo-500" for="password">Password</label>
                <input class="w-full p-2 mb-6 text-indigo-700 border-b-2 border-indigo-500 outline-none focus:bg-gray-300"
                       type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <label class="block mb-2 text-indigo-500" for="password">Manager?</label>
            <label
                    class="relative inline-block h-8 w-14 cursor-pointer rounded-full bg-gray-500 transition [-webkit-tap-highlight-color:_transparent] has-[:checked]:bg-gray-900"
            >

                <input class="peer sr-only" id="AcceptConditions" name="is_chef" type="checkbox" value="yes" />
                <span
                        class="absolute inset-y-0 start-0 m-1 size-6 rounded-full bg-gray-300 ring-[6px] ring-inset ring-white transition-all peer-checked:start-8 peer-checked:w-2 peer-checked:bg-white peer-checked:ring-transparent"
                ></span>
            </label>


            <div>
                <input class="w-full bg-indigo-700 hover:bg-pink-700 text-white font-bold py-2 px-4 mb-6 rounded"
                       type="submit" value="Sign Up">
            </div>
        </form>

        <footer>
            <a class="text-indigo-700 hover:text-pink-700 text-sm float-left" href="#">Forgot Password?</a>
            <a class="text-indigo-700 hover:text-pink-700 text-sm float-right" href="./login">login</a>
        </footer>
    </div>
</div>
</body>
</html>
