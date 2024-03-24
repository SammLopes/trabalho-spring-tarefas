package br.task.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.task.task.model.Task;
import br.task.task.repository.TaskRepository;

/**getAllTasks, getTaskById, createTask, updateTask, deleteTask*/ 

@Service
public class TaskService {
 
    @Autowired
    TaskRepository repository;

    public Task getTaskById(Long id){
        Task task = this.repository.findById(id).get();
        if(task != null){
            return task;
        } 
        return null;
    }
    
}
