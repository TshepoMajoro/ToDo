package org.springtech.todo.Services;

import org.springframework.stereotype.Service;
import org.springtech.todo.Entities.Task;
import org.springtech.todo.Repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository repository;

    TaskService(TaskRepository repo){
        repository = repo;
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Task of Id "+id+" does not exist");
        }
        return repository.findById(id).get();
    }

    public Task getTaskByTitle(String title){
        if (!repository.existsByTitle(title)){
            throw new ResourceNotFoundException("Task of Title "+title+" does not exist");
        }
        return repository.findByTitle(title).get();
    }
}
