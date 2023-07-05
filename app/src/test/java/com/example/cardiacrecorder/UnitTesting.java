package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;

public class UnitTesting {

    @Test
    public void AddTest(){

        HashMap<String, String> userMap1 = new HashMap<>();

        //date, time ,systolic, diastolic, heart, comment

        userMap1.put("date", "2023-01-12");
        userMap1.put("time", "12:20");
        userMap1.put("systolic_pressure", "120");
        userMap1.put("diastolic_pressure", "88");
        userMap1.put("heart_rate", "70");
        userMap1.put("comment", "good");




        //DATA record = new DATA("2023-01-12","12:20","120","88","70","good");
        //DATA record1 = new DATA("2023-02-13","12:40","121", "80","71","good");
        Add_Info userTestRecord = new Add_Info();
        String Key="key1";


        int ck=userTestRecord.Count(Key);
        userTestRecord.ADDING(userMap1,Key);
        assertEquals((ck+1),userTestRecord.Count(Key));

        //userTestRecord.add(record1);
        //assertEquals(2,userTestRecord.getRecords().size());
    }

}
