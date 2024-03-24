package br.task.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.task.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
