package com.khamdd.todo.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.khamdd.todo.dto.TodoResponseDTO;
import com.khamdd.todo.model.Todo;

public class TodoMapper {
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static TodoResponseDTO toDto(Todo todo) {
        TodoResponseDTO dto = new TodoResponseDTO();
        if (todo.getId() != null) {
            dto.setId(todo.getId().toString());
        }
        dto.setTitle(todo.getTitle());
        dto.setCreatedDate(todo.getCreatedDate().format(DISPLAY_FORMATTER));
        dto.setDueDate(todo.getDueDate().format(DISPLAY_FORMATTER));
        dto.setCompleted(todo.getCompleted());
        return dto;
    }

    public static Todo toModel(TodoResponseDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setCreatedDate(LocalDateTime.parse(dto.getCreatedDate(), DISPLAY_FORMATTER));
        todo.setDueDate(LocalDateTime.parse(dto.getDueDate(), DISPLAY_FORMATTER));
        todo.setCompleted(dto.getCompleted());
        return todo;
    }
}
