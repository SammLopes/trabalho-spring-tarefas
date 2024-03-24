package br.task.task.services;

import java.util.List;
import org.springframework.data.domain.Sort;
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
    
    public List<Task> getAllTasks(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC));
    }
}
