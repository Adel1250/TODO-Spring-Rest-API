package com.adel.todo.controller;

import com.adel.todo.model.Todo;
import com.adel.todo.model.TodoDto;
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

    @PutMapping("/{id}")
    public Todo update(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        return todoService.updateTodo(Todo.builder()
                .id(id)
                .description(todoDto.getDescription())
                .targetDate(todoDto.getTargetDate())
                .done(Boolean.valueOf(todoDto.getDone())).build());
    }

    @PostMapping
    public Todo save(@PathVariable("username") String username, @RequestBody TodoDto todoDto) {
        return todoService.addTodo(Todo.builder()
                .done(false)
                .username(username)
                .description(todoDto.getDescription())
                .targetDate(todoDto.getTargetDate()).build());
    }
}
