package com.example.DAO;

import com.example.model.Todo;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TodoDAO {
    public void saveTodo(Todo todo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(todo);
        transaction.commit();
        session.close();

    }

    public List<Todo> getTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Todo> todos = session.createQuery("from Todo", Todo.class).list();
        session.close();
        return todos;
    }

    public void updateTodo(Todo todo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(todo);
        transaction.commit();
        session.close();
    }

    public void deleteTodo(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Todo todo = session.get(Todo.class, id);
        if (todo != null) {
            session.delete(todo);
        }
        transaction.commit();
        session.close();
    }

    public Todo findById(Long id) {
        Session session = null;
        Todo todo = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();

            todo = session.get(Todo.class, id);
        } catch (Exception e) {
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return todo;
    }
}
