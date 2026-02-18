package com.khamdd.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khamdd.todo.dto.TodoResponseDTO;
import com.khamdd.todo.mapper.TodoMapper;
import com.khamdd.todo.model.Todo;
import com.khamdd.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoResponseDTO> todoResponseDTOs = todos.stream()
            .map(TodoMapper::toDto)
            .toList();

        return todoResponseDTOs;
    }

    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
}
