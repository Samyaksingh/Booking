package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUpdate {

    public static int addUser(User user) {
        try {
            Connection con = MysqlCon.getdbconnection();
            Statement stmt = con.createStatement();
            String query = "INSERT INTO user_profile(user_name , phone , email) VALUE('" +
                    user.getName() + "','" + user.getPhone_no() + "','" + user.getEmail() + "')";

            stmt.executeUpdate(query , Statement.RETURN_GENERATED_KEYS);
            System.out.println("Debug 1");

            ResultSet res = stmt.getGeneratedKeys();
            if(res.next()) return res.getInt(1);
            con.close();
            return 0;

        } catch (Exception e) {
            System.out.println("insode catch");
            throw new RuntimeException("Exception from DBUpdate", e);

        }
    }

    public static List<User> getAllUser() {
        try {
            Connection con = MysqlCon.getdbconnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM DemoDB.user_profile";
            ResultSet res = stmt.executeQuery(query);
            List<User> users = new ArrayList<User>();
            while (res.next()) {
                users.add(new User(res.getString(2), res.getString(3), res.getString(4)));
            }
            con.close();
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Exception from DBUpdate", e);
        }
    }

    public static void addVehicle(Vehicle vehicle) {
        try {
            Connection con = MysqlCon.getdbconnection();
            PreparedStatement ptmt = con.prepareStatement("INSERT INTO vehicle VALUE(?,?,?,?,?)");

            ptmt.setString(1, vehicle.getRc_no());
            ptmt.setString(2, vehicle.getDescription());
            ptmt.setInt(3, vehicle.getTariff_day());
            ptmt.setInt(4, vehicle.getTariff_km());
            ptmt.setInt(5, vehicle.getFree_km());
            ptmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception from DBUpdate", e);
        }
    }

    public static List<Vehicle> getAllVehicle() {
        try {
            Connection con = MysqlCon.getdbconnection();
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM vehicle";
            ResultSet res = stmt.executeQuery(query);
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            while (res.next()) {
                vehicles.add(new Vehicle(res.getString(1), res.getString(2), res.getInt(3), res.getInt(4) , res.getInt(5)));
            }
            con.close();
            return vehicles;
        } catch (Exception e) {
            throw new RuntimeException("Exception from DBUpdate", e);
        }
    }

    public static List<Vehicle> availableVehicle(Timestamp start , Timestamp end){
        try {
            Connection con = MysqlCon.getdbconnection();
            String query = "SELECT \n" +
                    "    *\n" +
                    "FROM\n" +
                    "    vehicle\n" +
                    "WHERE\n" +
                    "    reg_no NOT IN (SELECT \n" +
                    "            rc_no\n" +
                    "        FROM\n" +
                    "            booking_rcno\n" +
                    "        WHERE\n" +
                    "            booking_id IN (SELECT \n" +
                    "                    booking_id\n" +
                    "                FROM\n" +
                    "                    booking_time\n" +
                    "                WHERE\n" +
                    "                    booking_time.start BETWEEN start AND end\n" +
                    "                        OR booking_time.end BETWEEN start AND end\n" +
                    "                        OR (booking_time.start < start\n" +
                    "                        AND booking_time.end > end)));";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            while (res.next()) {
                vehicles.add(new Vehicle(res.getString(1), res.getString(2), res.getInt(3), res.getInt(4) , res.getInt(5)));
            }
            con.close();
            return vehicles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String addBooking(Booking booking){
        List<Vehicle> vehicles = availableVehicle(booking.getStart() , booking.getEnd());
        boolean flag = false;
        for(Vehicle v : vehicles){
            if(v.getRc_no().equals(booking.getRc_no())){
                flag=true;
                break;
            }
        }
        if(!flag) return "Chosen vehicle is not available";

        try {
            Connection con = MysqlCon.getdbconnection();
            PreparedStatement ptmt = con.prepareStatement("INSERT INTO booking_time(start,end) VALUE(?,?)",Statement.RETURN_GENERATED_KEYS);

            ptmt.setTimestamp(1, booking.getStart());
            ptmt.setTimestamp(2, booking.getEnd());
            ptmt.executeUpdate();

            ResultSet res= ptmt.getGeneratedKeys();
            res.next();
            int bookingid= res.getInt(1);

            ptmt= con.prepareStatement("INSERT INTO booking_rcno(booking_id,rc_no) VALUE(?,?)");
            ptmt.setInt(1, bookingid);
            ptmt.setString(2, booking.getRc_no());
            ptmt.executeUpdate();

            ptmt= con.prepareStatement("INSERT INTO booking_user(booking_id,user_id) VALUE(?,?)");
            ptmt.setInt(1, bookingid);
            ptmt.setInt(2, booking.getUser_id());
            ptmt.executeUpdate();



            con.close();
            return "Booking id is "+bookingid;
        } catch (Exception e) {
            throw new RuntimeException("Exception from DBUpdate", e);
        }

    }

}
