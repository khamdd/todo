package com.khamdd.todo.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.khamdd.todo.model.Todo;
import com.khamdd.todo.service.TodoService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listTodos", todoService.getAllTodos());
        model.addAttribute("todo", new Todo());
        return "index";
    }

    @GetMapping("/save")
    public String saveTodo(@ModelAttribute Todo todo) {
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable UUID id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }
}
