package org.springtech.todo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springtech.todo.Entities.Task;
import org.springtech.todo.Exceptions.ResourceNotFoundException;
import org.springtech.todo.Services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService service){
        taskService = service;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> getById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Task> getByTile(@PathVariable String title) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }
}
