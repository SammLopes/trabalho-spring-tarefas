package br.task.task.services;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.task.task.model.Task;
import br.task.task.repository.TaskRepository;
import java.util.Optional;

/**getAllTasks, getTaskById, createTask, updateTask, deleteTask*/ 

@Service
public class TaskService {
 
    @Autowired
    TaskRepository repository;

    public Optional<Task> getTaskById(Long id){
        
        Optional<Task> task = this.repository.findById(id);
        
        if(task.isPresent()){
            
            return task;
        
        }
        
        return Optional.empty();
    }
    
    public Optional<List<Task>> getAllTasks(){
        
        List<Task> listTask = this.repository.findAll(Sort.by("title").ascending().and(Sort.by("description").descending())); 

        if(listTask.isEmpty()){
            
            return Optional.empty();
        
        }
        
        return  Optional.of(listTask);
    
    }

    public Optional<Task> createTask(Task task){
        
        return Optional.ofNullable(this.repository.save(task));
        
    }

    public Optional<Task> updateTask(Long id , Task task){

        return Optional.ofNullable(this.repository.save(task));
    
    }

    public Optional<Task> deleteTask(Long id){
        
        Optional<Task> task = this.getTaskById(id);
        
        if(task.isPresent()){
        
            Task deletedTask = task.get();
            this.repository.deleteById(id); 
            return Optional.of(deletedTask);
            
        }
        
        return Optional.empty();
    }
}
