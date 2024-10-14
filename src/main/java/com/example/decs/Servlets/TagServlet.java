package com.example.decs.Servlets;

import com.example.decs.Entity.Tag;
import com.example.decs.Service.TagService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tag")
public class TagServlet extends HttpServlet {
        TagService tagService=new TagService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("title");
        Tag tag=new Tag();
        tag.setName(name);
        tagService.createTag(tag);
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);  // Redirect to the previous page
        } else {
            response.sendRedirect("/defaultPage");  // Fallback to a default page if referer is null
        }
    }
}
