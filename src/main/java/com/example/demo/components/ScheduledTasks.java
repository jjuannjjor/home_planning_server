package com.example.demo.components;

import com.example.demo.controllers.HomeController;
import com.example.demo.controllers.TaskController;
import com.example.demo.repositories.HomeRepository;
import com.example.demo.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    HomeController homeController = new HomeController();

    // Ejecutar a una hora específica todos los días
    @Scheduled(cron = "0 0 19 * * ?") // A las 7 PM todos los días
    public void executeTaskAtSpecificTime() {
        System.out.println("Ejecutando tarea a las 7 PM todos los días...");
        homeController.processUserHomeAssignments();
    }
}
