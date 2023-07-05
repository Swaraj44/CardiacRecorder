package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.List;

public class DataList {

    private List<DATA> data = new ArrayList<>();



    public void add(DATA dd) {
        if (data.contains(dd)) {throw new IllegalArgumentException("Data exists already!");}
        data.add(dd);
    }




    public void delete(DATA dd) {
        if (!data.contains(dd)) {throw new IllegalArgumentException("Data does not exist!");}
        data.remove(dd);
    }





    public int count() {
        return data.size();
    }







    public void update(DATA dd) {
        for (int i = 0; i < data.size(); i++) {
            DATA cdata = data.get(i);
            if (cdata.getKey().equals(dd.getKey())) {
                data.set(i,dd);
                return;
            }
        }
        throw new IllegalArgumentException("Data not found!");
    }




    public List<DATA> getDatalist() {
        List<DATA> getDatalist = new ArrayList<>(data); return getDatalist;
    }
}
