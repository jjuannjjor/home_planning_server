package com.example.demo.services;

import com.example.demo.entities.Home;
import com.example.demo.repositories.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;


    public Home saveHome(Home home) {
        return homeRepository.save(home);
    }
    
    public void deleteHome(Long id) {
		homeRepository.deleteById(id);
	}
    public Home getHomeById(Long id) {
		return homeRepository.findById(id).orElse(null);
	}

    // Otros métodos según sea necesario

    public int registerNewhomeServiceMethod(String fname, String fhousecode) {
        return homeRepository.registerNewhome(fname,fhousecode);
    }
    public List<String> checkHouseCode(String houseCode) {
        return homeRepository.checkHouseCode(houseCode);
    }

    public Long getHomeDetailsByHouseCode(String houseCode) {
        return homeRepository.getHomeDetailsByHouseCode(houseCode);
    }

    public int registerUserHomeServiceMethod(Long id_user,Long id_home) {
        return homeRepository.registerUserHome(id_user,id_home);
    }
    public List<Long> getHomesByIdUser(Long id){
        return homeRepository.getHomes(id);
    }

    public int registerUserHomeTaskServiceMethod(Long id_user,Long id_home, Long id_task) {
        String terminado = "false";
        return homeRepository.registerUserHomeTask(id_user,id_home,id_task,terminado);
    }

    public List<Long> getUsersByIdHome(Long id){
        return homeRepository.getUsers(id);
    }


    public Home getHomesByIdHome (Long id){

        return homeRepository.findById(id).orElse(null);
    }

    public List<Long> chekUserHomeTaskServiceMethod(Long person, Long homeId) {
        return homeRepository.chekUserHomeTask(person,homeId);
    }

    public void deleteUserHomeTaskServiceMethod(Long person, Long homeId) {
        homeRepository.deleteUserHomeTask(person,homeId);
    }

    public List<Long> checkUserHome(Long user, Long home) {
        return homeRepository.checkUserHome(user,home);
    }

    public String getHouseCodeById(Long aLong) {
        return homeRepository.getHouseCode(aLong);
    }

    public void deleteUserHome(Long idUser, Long idHome) {
        homeRepository.deleteUserHome(idUser,idHome);
    }

}