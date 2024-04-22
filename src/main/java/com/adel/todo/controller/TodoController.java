package com.adel.todo.controller;

import com.adel.todo.model.Todo;
import com.adel.todo.model.TodoDto;
import com.adel.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/basic-auth")
    public String basicAuthCheck() {
        return "Success";
    }

    @GetMapping("/{username}/todos")
    public List<Todo> findByUsername(@PathVariable("username") String username) {
        return todoService.getTodosByUsername(username);
    }

    @GetMapping("/{username}/todos/{id}")
    public Todo findById(@PathVariable("id") Long id) {
        return todoService.getTodoByID(id);
    }

    @DeleteMapping("/{username}/todos/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/todos/{id}")
    public Todo update(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
        return todoService.updateTodo(Todo.builder()
                .id(id)
                .description(todoDto.getDescription())
                .targetDate(todoDto.getTargetDate())
                .done(Boolean.valueOf(todoDto.getDone())).build());
    }

    @PostMapping("/{username}/todos")
    public Todo save(@PathVariable("username") String username, @RequestBody TodoDto todoDto) {
        return todoService.addTodo(Todo.builder()
                .done(false)
                .username(username)
                .description(todoDto.getDescription())
                .targetDate(todoDto.getTargetDate()).build());
    }
}
