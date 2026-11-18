package org.springtech.todo.DTOs;

import java.time.LocalDate;

public record TaskResponse (
        long id,
        String title,
        String description,
        LocalDate deadline
){
}
