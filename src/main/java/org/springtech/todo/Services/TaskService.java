package org.springtech.todo.Services;

import org.springframework.stereotype.Service;
import org.springtech.todo.DTOs.TaskRequest;
import org.springtech.todo.DTOs.TaskResponse;
import org.springtech.todo.Entities.Task;
import org.springtech.todo.Exceptions.ResourceNotFoundException;
import org.springtech.todo.Repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    TaskService(TaskRepository repo){
        repository = repo;
    }

    public List<TaskResponse> getAllTasks(){
        return repository.findAll().stream().map(task -> mapTaskToTaskReponse(task)).toList();
    }

    public TaskResponse getTaskById(long id) throws ResourceNotFoundException {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Task of Id "+id+" does not exist");
        }
        Task task = repository.findById(id).get();
        return mapTaskToTaskReponse(task);
    }

    public TaskResponse getTaskByTitle(String title) throws ResourceNotFoundException {
        if (!repository.existsByTitle(title)){
            throw new ResourceNotFoundException("Task of Title "+title+" does not exist");
        }
        Task task = repository.findByTitle(title).get();
        return mapTaskToTaskReponse(task);
    }

    public void addNewTask(TaskRequest task){
        Task _task = mapTaskRequestToTask(task);
        repository.save(_task);
    }

    public void deleteTaskById(long id) throws ResourceNotFoundException {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Task of Id "+id+" does not exist");
        }
        repository.deleteById(id);
    }

    public void deleteTaskByTitle(String title) throws ResourceNotFoundException {
        if (!repository.existsByTitle(title)){
            throw new ResourceNotFoundException("Task of Title "+title+" does not exist");
        }
        repository.deleteByTitle(title);
    }

    private Task mapTaskRequestToTask(TaskRequest task){
        Task _task = new Task();
        _task.setTitle(task.title());
        _task.setDescription(task.description());
        _task.setDue(task.due());

        return _task;
    }

    private TaskResponse mapTaskToTaskReponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDue()
        );
    }
}
