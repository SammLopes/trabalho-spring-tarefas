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

    public Task createTask(Task task){
        Task t = this.repository.save(task);
        
        if(t != null){
            return t;
        }

        return null;
    }

    public Task updateTask(Long id , Task task){

        if(this.getTaskById(id) == null){
            return null;
        }

        return this.createTask(task);
    }

    public Task deleteTask(Long id){
        
        Task task = this.getTaskById(id);
        if(task != null){
            this.repository.delete(task);
        }
        return task;
    }
}
