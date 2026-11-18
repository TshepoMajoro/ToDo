package org.springtech.todo.DTOs;

import java.time.LocalDateTime;

public record TaskResponse (
        long id,
        String title,
        String description,
        LocalDateTime deadline
){
}
