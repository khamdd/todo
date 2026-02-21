package com.khamdd.todo.service;

import java.util.List;
import java.util.UUID;

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

    public void deleteTodoById(UUID id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + id));
        todoRepository.delete(todo);
    }

    public Todo getTodoById(UUID id) {
        return todoRepository.findById(id).orElse(null);
    }
}
