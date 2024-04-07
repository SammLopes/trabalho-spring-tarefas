package br.task.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins="*") 
@RestController
@RequestMapping("gravar/tarefas")
public class TaskController {
    
    @Autowired
    TaskService service;

    @GetMapping("/task/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id){
        
        Optional<Task> task = service.getTaskById(id);

        if(task.isPresent()){
        
            return new ResponseEntity<>(task, HttpStatus.OK);
            
        }else{
        
            return ResponseEntity.status(HttpStatus.OK).body("Não existem tasks cadastradas");
            
        }

    }

    @GetMapping("/tasks")
    public ResponseEntity<Object> getAllTasks(){
        
        Optional<List<Task>> list = service.getAllTasks();
        
        if(list.isPresent()){
        
            return new ResponseEntity<>(list, HttpStatus.OK);
                    
        }else {
        
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem professores cadastrados!!!");
            
        }
        
    }

    @PostMapping("/task")
    public ResponseEntity<Object> createTask(/**@RequestParam Task createTask*/@RequestParam String title, @RequestParam String description, @RequestParam(required = false, defaultValue = "false") Boolean completed){
        Task createTask = new Task();
        createTask.setTitle(title);
        createTask.setDescription(description);
        createTask.setCompleted(completed);      
       
        Optional<Task> task = this.service.createTask(createTask);
        
        if(task.isPresent()){
        
            return new ResponseEntity<>(task, HttpStatus.OK);
        
        }

        return ResponseEntity.status(HttpStatus.FOUND).body("Já existe esta task criada");
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, /**@RequestBody Task newTask*/@RequestParam String title, @RequestParam String description, @RequestParam(required = false, defaultValue = "false") Boolean completed){
        
        Optional<Task> task = service.getTaskById(id);
        
        if(!task.isPresent()){
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não fo encontrado esta tarefa!!");
            
        }else {
            //return this.getTaskById(id);

            Task newTask = new Task();
            newTask.setId(id);
            newTask.setTitle(title);
            newTask.setDescription(description);
            newTask.setCompleted(completed);

            Optional<Task> t = this.service.updateTask(id, newTask);
            
            return new ResponseEntity<>(t, HttpStatus.OK);
        
        }
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
    
        Optional<Task> task = this.service.getTaskById(id);
        
        if( task.isPresent() ){
        
            Optional<Task> _task = this.service.deleteTask(id);
            
            return new ResponseEntity<>(_task, HttpStatus.OK);
            
        }else{
        
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada! ");
            
        }
       
    }
}
