package com.adel.todo.service;

import com.adel.todo.model.Todo;
import com.adel.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodosByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    public void addTodo(Todo todo) {
        todoRepository.save(Todo.builder()
                .targetDate(todo.getTargetDate())
                .description(todo.getDescription())
                .done(false)
                .username(todo.getUsername()).build());
    }

    public void deleteTodo(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(todoRepository::delete);
    }

    public Todo getTodoByID(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    public void updateTodo(Todo todo) {
        deleteTodo(todo.getId());
        todoRepository.save(todo);
    }
}
