package com.example.demo.services;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskDTO;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

/*
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }
    
    public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
    
    public Task getTaskById(Long id) {
		return taskRepository.findById(id).orElse(null);
	}

    // Otros métodos según sea necesario


    public List<Task> obtenerTareasPendientes() {
        return taskRepository.findByEstado("pendiente");
    }

    public Task actualizarEstadoTarea(Long id, String estado) {
        Task tarea = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        tarea.setEstado(estado);
        return taskRepository.save(tarea);
    }

    public Task crearTarea(Task tarea) {
        return taskRepository.save(tarea);
    }
    */

    public List<Long> getTasksByUserHome(Long user_id, Long home_id){
        return taskRepository.getTasks(user_id,home_id);
    }

        public List<TaskDTO> getAllTasksByUserHome(Long userId, Long homeId) {
            List<Object[]> results = taskRepository.getTasksall(userId, homeId);
            List<TaskDTO> dtos = new ArrayList<>();
            for (Object[] result : results) {
                Long taskId = ((Number) result[0]).longValue();
                String terminado = (String) result[1];
                dtos.add(new TaskDTO(taskId, terminado));
            }
            return dtos;
        }


    public String getTasksByUserHomeDTO (Long home_id, Long task_id) {
        return taskRepository.getTasksDTO(home_id, task_id);
    }
    public Task getTasksById (Long id){
        return taskRepository.findById(id).orElse(null);
    }


    public int updateUserHomeTaskServiceMethod(Long id_user,Long id_home, Long id_task,String terminado) {
        return taskRepository.updateUserHomeTask(id_user,id_home,id_task,terminado);
    }


}