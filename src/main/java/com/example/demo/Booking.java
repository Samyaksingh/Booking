package com.example.demo;

import java.sql.Timestamp;

public class Booking {

    //auto generate booking id
    private Integer user_id;
    private String rc_no;
    private Timestamp start;
    private Timestamp end;


    public Booking(int user_id, String rc_no, Timestamp start, Timestamp end) {
        this.user_id = user_id;
        this.rc_no = rc_no;
        this.start = start;
        this.end = end;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRc_no() {
        return rc_no;
    }

    public void setRc_no(String rc_no) {
        this.rc_no = rc_no;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}