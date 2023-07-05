package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitTesting {


    @Test
    public void AdditionTest(){
        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();

        record.add(dd1);
        assertEquals(1,record.getDatalist().size());
        record.add(dd2);
        assertEquals(2,record.getDatalist().size());
    }


    @Test
    public void DeletetionTest(){
        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();
        record.add(dd1);
        record.add(dd2);

        assertEquals(2,record.getDatalist().size());
        record.delete(dd1);
        assertEquals(1,record.getDatalist().size());
        assertTrue(!record.getDatalist().contains(dd1));
    }

    @Test
    public void TestAdditionException(){
        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();
        record.add(dd1);
        record.add(dd2);

        assertThrows(IllegalArgumentException.class,()->{
            record.add(dd1);
        });
    }


    @Test
    public void TestDeletetionException(){
        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();
        record.add(dd1);
        record.add(dd2);

        record.delete(dd1);
        assertThrows(IllegalArgumentException.class,()->{
            record.delete(dd1);
        });
    }


    @Test
    public void Test_Update() {

        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();
        record.add(dd1);
        record.add(dd2);


        DATA originaldata= record.getDatalist().get(0);
        DATA updateddata = new DATA("1","2023-01-12","12:20","111","77","70","good");
        record.update(updateddata);

        assertTrue(record.getDatalist().contains(updateddata));
        assertFalse(record.getDatalist().contains(originaldata));
    }



    @Test
    public void Test_Update_Exception() {

        DATA dd1 = new DATA("1","2023-01-12","12:20","120","88","70","good");
        DATA dd2 = new DATA("2","2023-02-13","12:40","121", "80","71","good");

        DataList record = new DataList();
        record.add(dd1);
        record.add(dd2);


        DATA data = new DATA("3","2023-01-12","12:20","111","77","70","good");
        record.add(data);

        assertThrows(IllegalArgumentException.class, () -> {
            DATA dd3 = new DATA("4","2023-02-13","12:45","131", "89","71","good");
            record.update(dd3);
        });
    }


    /*
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

    */

}
