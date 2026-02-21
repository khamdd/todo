package com.khamdd.todo.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.khamdd.todo.model.Todo;
import com.khamdd.todo.service.TodoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo, Model model) {
        if (todo.getDueDate() != null && todo.getDueDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("error", "Due date and time cannot be in the past");
            model.addAttribute("listTodos", todoService.getAllTodos());
            return "index";
        }
        todo.setCompleted(false);
        todo.setCreatedDate(LocalDateTime.now());
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo todo) {
        if(todo.getId() == null) {
            return "redirect:/";
        }

        Todo existingTodo = todoService.getTodoById(todo.getId());
        if (existingTodo == null) {
            return "redirect:/";
        }

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setDueDate(todo.getDueDate());
        existingTodo.setCompleted(todo.getCompleted());

        todoService.saveTodo(existingTodo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable UUID id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable UUID id, Model model) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "edit";
        }
        return "redirect:/";
    }
}
