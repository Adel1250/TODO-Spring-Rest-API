package com.adel.todo.controller;

import com.adel.todo.model.Todo;
import com.adel.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{username}/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> findByUsername(@PathVariable("username") String username) {
        return todoService.getTodosByUsername(username);
    }

    @GetMapping("/{id}")
    public Todo findById(@PathVariable("id") Long id) {
        return todoService.getTodoByID(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
