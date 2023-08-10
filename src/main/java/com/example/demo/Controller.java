package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/addUser")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(bookingService.addUser(user),HttpStatus.OK );
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Allusers")
    public ResponseEntity<List<User>> getAllUser(){
        try {
            return new ResponseEntity<>(bookingService.getAllUser() , HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @PostMapping("/Vehicle")
    public ResponseEntity<HttpStatus> addVehicle(@RequestBody Vehicle vehicle) {
        try {
            bookingService.addVehicle(vehicle);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/Vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        try {
            return new ResponseEntity<>(bookingService.getAllVehicle() , HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


    @GetMapping("/Book")
    public ResponseEntity<List<Vehicle>> available(@RequestParam("start") Timestamp start, @RequestParam("end")Timestamp end){
        try {
            return new ResponseEntity<>((bookingService.availableVehicle(start,end)) , HttpStatus.OK);
        }
        catch (Exception e ) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/Book")
    public String book(@RequestBody Booking booking){
        try {
            System.out.println("Inside post book controlller");
            return bookingService.bookVehicle(booking);
        }
        catch (Exception e ) {
            return "Error";
        }

    }


/*
    @GetMapping("/abc/{firstName}/{lastName}/")
    public Student studentPa(@PathVariable("firstName")String firstName, @PathVariable("lastName")String lastName) {
        return new Student(firstName, lastName);
    }

    @PostMapping("/getname")
    public Student getfullname(@RequestBody Student s1){
        return new Student(s1.getFirstName(),studentService.getLast(s1.getFirstName()) );
    }
*/
}
