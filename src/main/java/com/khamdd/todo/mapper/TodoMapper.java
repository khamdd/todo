package com.khamdd.todo.mapper;

import java.time.LocalDate;

import com.khamdd.todo.dto.TodoResponseDTO;
import com.khamdd.todo.model.Todo;

public class TodoMapper {
    public static TodoResponseDTO toDto(Todo todo) {
        TodoResponseDTO dto = new TodoResponseDTO();
        dto.setTitle(todo.getTitle());
        dto.setCreatedDate(todo.getCreatedDate().toString());
        dto.setDueDate(todo.getDueDate().toString());
        dto.setCompleted(todo.getCompleted());
        return dto;
    }

    public static Todo toModel(TodoResponseDTO dto) {
        Todo todo = new Todo();
        todo.setTitle(dto.getTitle());
        todo.setCreatedDate(LocalDate.parse(dto.getCreatedDate()));
        todo.setDueDate(LocalDate.parse(dto.getDueDate()));
        todo.setCompleted(dto.getCompleted());
        return todo;
    }
}
