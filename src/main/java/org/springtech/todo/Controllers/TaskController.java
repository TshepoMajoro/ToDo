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

    @GetMapping
    public ResponseEntity<TaskResponse> getById(@RequestParam long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<TaskResponse> getByTile(@RequestParam String title) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@Valid @RequestBody TaskRequest task){
        taskService.addNewTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam long id) throws ResourceNotFoundException {
        taskService.deleteTaskById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskByTitle(@RequestParam String title) throws ResourceNotFoundException {
        taskService.deleteTaskByTitle(title);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable long id,@Valid @RequestBody TaskRequest task) throws ResourceNotFoundException {
        taskService.updateTask(id, task);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
