package com.example.demo;


import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    public int addUser(User user) {


        return DBUpdate.addUser(user);
    }
    public List<User> getAllUser(){
        return DBUpdate.getAllUser();
    }

    public void addVehicle(Vehicle vehicle){
        DBUpdate.addVehicle(vehicle);
    }
    public List<Vehicle> getAllVehicle(){
        return DBUpdate.getAllVehicle();
    }

    public List<Vehicle> availableVehicle(Timestamp start , Timestamp end){
        return DBUpdate.availableVehicle(start,end);
    }
    public String bookVehicle(Booking booking){
        String res = DBUpdate.addBooking(booking);
        return res;
    }
}
