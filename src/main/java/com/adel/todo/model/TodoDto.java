package com.adel.todo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TodoDto {
    @NotBlank(message = "Description is mandatory")
    private String description;
    private Boolean done;
    private LocalDate targetDate;
    private String username;
}
