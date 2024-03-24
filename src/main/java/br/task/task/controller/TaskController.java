package br.task.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Task createTask(@RequestParam Task createTask){
        Task t = this.service.createTask(createTask);
        
        if(t == null){
            return null;
        }

        return t;
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestParam Task newTask){
        return this.service.updateTask(id, newTask);
    }
}
