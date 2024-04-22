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

    public Todo addTodo(Todo todo) {
        return todoRepository.save(Todo.builder()
                .targetDate(todo.getTargetDate())
                .description(todo.getDescription())
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

    public Todo updateTodo(Todo todo) {
        Todo existingTodo = getTodoByID(todo.getId());
        existingTodo.setTargetDate(todo.getTargetDate());
        existingTodo.setDescription(todo.getDescription());
        return todoRepository.save(existingTodo);
    }
}
