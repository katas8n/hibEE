package com.example.servlets;

import com.example.services.TodoService;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoServlet<Todo> extends HttpServlet {
    private Configuration freemarkerConfig;
    private TodoService todoService = new TodoService();

    public TodoServlet(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Todo> todos = (List<Todo>) todoService.getTodos();

        for (Todo todo : todos) {
            System.out.println("Todo task: " + todo.getClass()); 
        }


        if (todos == null || todos.isEmpty()) {
            System.out.println("No todos found or list is null");
        }

        Map<String, Object> model = new HashMap<>();
        model.put("todos", todos);

        Template template = freemarkerConfig.getTemplate("todos.ftl");
        StringWriter writer = new StringWriter();
        try {
            template.process(model, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.getWriter().write(writer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getPathInfo();

        if ("/complete".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            todoService.completeTodo(id);
        } else if ("/update".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            String task = req.getParameter("task");
            todoService.updateTodo(id, task);
        } else if ("/delete".equals(action)) {
            Long id = Long.parseLong(req.getParameter("id"));
            todoService.deleteTodo(id);
        }

        resp.sendRedirect("/todos");
    }
}
