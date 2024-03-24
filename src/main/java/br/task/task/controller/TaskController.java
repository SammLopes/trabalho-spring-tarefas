package br.task.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.task.task.model.Task;
import br.task.task.services.TaskService;

@CrossOrigin 
@RestController
@RequestMapping("gravar/tarefas")
public class TaskController {
    
    @Autowired
    TaskService service;

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable Long id){
        
        Task task = service.getTaskById(id);

        if(task == null){
            return null;
        }

        return task;

    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @PostMapping("/task")
    public Task createTask(/**@RequestParam Task createTask*/@RequestParam String title, @RequestParam String description, @RequestParam(required = false, defaultValue = "false") Boolean completed){
        Task createTask = new Task();
        createTask.setTitle(title);
        createTask.setDescription(description);
        createTask.setCompleted(completed);
        
        Task t = this.service.createTask(createTask);
        
        if(t == null){
            return null;
        }

        return t;
    }

    @PutMapping("/task/{id}")
    public Task updateTask(@PathVariable Long id, /**@RequestBody Task newTask*/@RequestParam String title, @RequestParam String description, @RequestParam(required = false, defaultValue = "false") Boolean completed){
        
        if(this.getTaskById(id) != null){
            //return this.getTaskById(id);
            
            Task newTask = new Task();
            newTask.setId(id);
            newTask.setTitle(title);
            newTask.setDescription(description);
            newTask.setCompleted(completed);
            return this.service.updateTask(id, newTask);
        
        }

        return null;
    }

    @DeleteMapping("task/{id}")
    public Task deleteTask(@PathVariable Long id){
        return this.service.deleteTask(id);
    }
}
