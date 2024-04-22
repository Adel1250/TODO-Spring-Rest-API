package com.adel.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Description is mandatory")
    private String description;
    private LocalDate targetDate;
    private String username;
}
