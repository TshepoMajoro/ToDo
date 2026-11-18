package org.springtech.todo.DTOs;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(
        @NotNull(message = "Title is a required field.")
        @Size(min = 5,max = 25, message = "Title must not be less than 5 characters or exceed 25 characters")
        String title,
        @NotNull(message = "Description is a required field.")
        @Size(min = 10, max = 50, message = "Description cannot be less than 10 characters or more than 50 characters.")
        String description,
        @NotNull(message = "Due date is a required field.")
        LocalDate due
) {
}
