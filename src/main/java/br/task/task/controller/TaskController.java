package br.task.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.task.task.model.Task;
import br.task.task.services.TaskService;

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
}
