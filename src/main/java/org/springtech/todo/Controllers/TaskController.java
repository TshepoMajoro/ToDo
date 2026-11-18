package org.springtech.todo.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springtech.todo.DTOs.TaskRequest;
import org.springtech.todo.DTOs.TaskResponse;
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
    public ResponseEntity<List<TaskResponse>> getAll(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<TaskResponse> getByTile(@PathVariable String title) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody TaskRequest task){
        taskService.addNewTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteTaskById(long id) throws ResourceNotFoundException {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/title/{title}")
    public ResponseEntity<Void> deleteTaskByTitle(String title) throws ResourceNotFoundException {
        taskService.deleteTaskByTitle(title);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
