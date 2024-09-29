package com.example.services;

import com.example.DAO.TodoDAO;
import com.example.model.Todo;
import java.util.List;

public class TodoService {
    private TodoDAO todoDAO = new TodoDAO();

    public void addTodo(String task) {
        Todo todo = new Todo(task);
        todoDAO.saveTodo(todo);
    }

    public List<Todo> getTodos() {
        return todoDAO.getTodos();
    }

    public void completeTodo(Long id) {
        Todo todo = todoDAO.getTodos().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if (todo != null) {
            todo.setCompleted(true);
            todoDAO.updateTodo(todo);
        }
    }

    public boolean deleteTodo(Long id) {
        Todo todo = todoDAO.findById(id);
        if (todo != null) {
            todoDAO.deleteTodo(id);
            return true;
        }
        return false;
    }


        public void updateTodo(Long id, String newTask) {
            Todo todo = todoDAO.findById(id);
            if (todo != null) {
                todo.setTask(newTask);
                todoDAO.updateTodo(todo);
            }

    }
}
