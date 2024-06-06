package com.example.demo.controllers;

import com.example.demo.entities.Home;
import com.example.demo.repositories.UserHomeRepository;
import com.example.demo.services.HomeService;
import com.example.demo.services.UserHomeService;
import com.sun.jdi.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.google.gson.Gson;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;
    @Autowired
    private UserHomeService userHomeService;
    @GetMapping("/{id}")
    public void getHomeById(@PathVariable Long id) {
	    homeService.getHomeById(id);
    }

    @PostMapping
    public Home createHome(@RequestBody Home home) {
        return homeService.saveHome(home);
    }

    @DeleteMapping("/{id}")
    public void deleteHome(@PathVariable Long id) {
	    homeService.deleteHome(id);
    }


    @PostMapping("/get/code")
    public ResponseEntity getHouseCodeConid(@Param("id") String id){
        System.out.println("IS HOME PARA VER CODIGO: ");
        System.out.println(id);
        String home = homeService.getHouseCodeById(Long.valueOf(id));
        System.out.println(home);
        return new ResponseEntity<>(home,HttpStatus.OK);
    }

    @PostMapping("/delUserHome")
    public ResponseEntity detelesuerhome(@Param("id") String id,@Param("id_home") String id_home){
        System.out.println("ID USER PAA ELIMINAR: "+id);
        System.out.println("ID HOME PAA ELIMINAR: "+id_home);
        homeService.deleteUserHome(Long.valueOf(id),Long.valueOf(id_home)); //homeService.deleteUserHome(id_user,id_home);
        homeService.deleteUserHomeTaskServiceMethod(Long.valueOf(id),Long.valueOf(id_home));
        int a = asignarTask(Long.valueOf(id),Long.valueOf(id_home));
        if(a != 0){
            return new ResponseEntity<>("error al cargar las tareas",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    
    // Otros endpoints según sea necesario
	//
    @PostMapping("/register")
    public ResponseEntity registerNewHome(@Param("nombre") String nombre){

        String housecode = newHouseCode();

        if (nombre.isEmpty() ) {
            return new ResponseEntity<>("Por favor ingrese un nombre un nombre", HttpStatus.BAD_REQUEST);
        }
        if (housecode.isEmpty() ) {
            return new ResponseEntity<>("Error al generar el codigo", HttpStatus.BAD_REQUEST);
        }

        System.out.println("El código de la casa es :"+housecode);

        int result = homeService.registerNewhomeServiceMethod(nombre,housecode);

        if (result !=1) {
            return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
        }

        //Home home = homeService.getHomeDetailsByHouseCode(housecode);


        return new ResponseEntity<>(housecode,HttpStatus.OK);
    }

    public String generateHousecode(){
        Random random = new Random();
        int min = 100000;
        int max = 999999;

        String code = String.valueOf(random.nextInt((max - min) + 1) + min);

        return code;

    }


    public String newHouseCode(){
        String housecode = generateHousecode();
        List<String> housecodeList = homeService.checkHouseCode(housecode);

        while (!housecodeList.isEmpty()) {
            housecode = generateHousecode();
            //housecodeList = homeService.checkHouseCode(housecode);
        }

        return housecode;
    }

    @PostMapping("/register/usehome")
    public ResponseEntity registerUserHome(@Param("housecode") String housecode,@Param("id") String id){

        if (housecode == null || housecode.isEmpty() ) {
            return new ResponseEntity<>("Por favor ingrese un código", HttpStatus.BAD_REQUEST);
        }

// Obtener id de la casa utilizando el código de la casa
        System.out.println("El código de la casa es :"+housecode);

        Long home;
        try {
            home = homeService.getHomeDetailsByHouseCode(housecode);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener detalles de la casa", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (home == null) {
            return new ResponseEntity<>("Casanoencontrada",HttpStatus.OK);
        }

        System.out.println("ID user:"+id);
        System.out.println("ID home:"+home);
        List<Long> check = homeService.checkUserHome(Long.valueOf(id),home);
        if (!check.isEmpty()) {
            System.out.println("ya estas registrado a esta casa");
            return new ResponseEntity<>("pertenece",HttpStatus.OK);
        }



        // Registrar el usuario con la casa
        int result;
        try {
            result = homeService.registerUserHomeServiceMethod(Long.valueOf(id), home);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrarte en la casa",HttpStatus.BAD_REQUEST);
        }

// Verificar el resultado de la operación
        if (result !=1) {
            return new ResponseEntity<>("failed add house",HttpStatus.BAD_REQUEST);
        }

        if(asignarTask(Long.valueOf(id), home) != 0){
            return new ResponseEntity<>("error al cargar las tareas",HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>("success",HttpStatus.OK);
    }
    public void processUserHomeAssignments() {
        List<Object[]> userHomeList = userHomeService.getUserIdAndHomeId();
        for (Object[] userHome : userHomeList) {
            Long userId = (Long) userHome[0];
            Long homeId = (Long) userHome[1];
            asignarTask(userId, homeId);
        }
    }

    public int asignarTask(Long user_id,Long home_id){
        int maxNumber = 13;
        List<Long> usersid;
        try { //generar lista de ids de usuarios de esta casa
            usersid = homeService.getUsersByIdHome(home_id);
        } catch (Exception e) {
            return 1;
        }
        System.out.println("Lista usuarios casa "+home_id +" : "+usersid);

        if (usersid.isEmpty()) {
            return 1;
        }


        int numPeople = usersid.size();
        List<Integer> numbers = new ArrayList<>();

        // Crear la lista de números del 1 al maxNumber
        for (int i = 1; i <= maxNumber; i++) {
            numbers.add(i);

        }
        System.out.println(numbers);
        // Barajar la lista de números
        Collections.shuffle(numbers);

        System.out.println(numbers);

        // Calcular cuántos números recibe cada persona y cuántos sobran
        int numbersPerPerson = maxNumber / numPeople;
        int remainingNumbers = maxNumber % numPeople;
        int repremainingNumbers =numbersPerPerson * numPeople;

        System.out.println("Reciben" + numbersPerPerson);
        System.out.println("Sobran" + remainingNumbers);


        List<Long> numbersList2 = new ArrayList<>();
        int index = 0;
        for (Long person : usersid) {
            numbersList2 = homeService.chekUserHomeTaskServiceMethod(person,home_id);
            homeService.deleteUserHomeTaskServiceMethod(person,home_id);
            // Asignar los números correspondientes a cada persona
            for (int i = 0; i < numbersPerPerson; i++) {
                System.out.println("asignacion de tareas(user,home,tarea)");
                homeService.registerUserHomeTaskServiceMethod(person,home_id, Long.valueOf(numbers.get(index)));
                System.out.println(person +"," + home_id +"," + numbers.get(index));
                index++;
            }
        }

        // Asignar los números sobrantes (si hay) de forma equitativa
        for (int i = 0; i < remainingNumbers; i++) {
            System.out.println("asignacion de tareas");
            homeService.registerUserHomeTaskServiceMethod(usersid.get(i),home_id, Long.valueOf(numbers.get(index)));
            index++;
        }
        return 0;
    }


    @PostMapping("/getHomes")
    public ResponseEntity getHomesByUser(@Param("id") String id){

        if (id == null || id.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id", HttpStatus.BAD_REQUEST);
        }

// Obtener lista de casas utilizando el id de usuario


        List<Long> homesid;
        try {
            homesid = homeService.getHomesByIdUser(Long.valueOf(id));
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la lista de los ids", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("ID user:"+id);
        System.out.println("lista ID casas:" +
                homesid);

        //conseguir lista de casas con la lista de sus ids
        List<Home> homes = new ArrayList<>();
        for (Long i : homesid){
            System.out.println(i);
            homes.add(homeService.getHomesByIdHome(i));
        }

        System.out.println("lista casas:" +
                homes);

        String homesJson = convertToJson(homes);

        return new ResponseEntity<>(homesJson,HttpStatus.OK);
    }

    public static String convertToJson(List<Home> casas) {
        Gson gson = new Gson();
        return gson.toJson(casas);
    }

}