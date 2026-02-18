package com.khamdd.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.khamdd.todo.model.Todo;
import com.khamdd.todo.service.TodoService;


import org.springframework.web.bind.annotation.GetMapping;

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
}
