package com.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.List;

public class DataList {

    private List<DATA> data = new ArrayList<>();

    // Add
    public void add(DATA e) {
        if (data.contains(e)) {
            throw new IllegalArgumentException("Entry already exists");
        }
        data.add(e);
    }

    // Delete
    public void delete(DATA e) {
        if (!data.contains(e)) {
            throw new IllegalArgumentException("Entry does not exist");
        }
        data.remove(e);
    }

    // Count
    public int count() {
        return data.size();
    }

    // Update
    public void update(DATA e) {
        for (int i = 0; i < data.size(); i++) {
            DATA cdata = data.get(i);
            if (cdata.getKey().equals(e.getKey())) {
                data.set(i, e);
                return;
            }
        }
        throw new IllegalArgumentException("Entry not found");
    }

    public List<DATA> getEntries() {
        List<DATA> entryList = new ArrayList<>(data);
        return entryList;
    }
}
