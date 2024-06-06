package com.example.demo.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TaskDTO {
    
    private Long task_id;
    private String terminado;
    
    public TaskDTO() {}
    public TaskDTO(Long id,String terminado ) {
        this.task_id = id;
        this.terminado = terminado;
        
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + task_id +
                ", terminnado='" + terminado + '}';
    }

    public Long getId() {
        return task_id;
    }

    public void setId(Long id) {
        this.task_id = id;
    }

    public String getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado = terminado;
    }

}

