package org.bonn.se.ws18.midterm.model;

import java.util.ArrayList;

public class CritManager {
    private ArrayList<Criterias> list;
    private static CritManager instance;

    private CritManager(){
        list = new ArrayList<Criterias>();
    }

    public void addCrit(Criterias a){
        try {
            list.add(a);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static synchronized CritManager getInstance() {
        if (instance == null) {
            instance = new CritManager();
        }
        return instance;
    }
    public int execute(UserStory us) {
        int x = 100;
        for (Criterias c : list) {
            x -= c.determineQual(us);
        }
        if (x < 0 ){
            return 0;
        } else{
            return x;
        }
    }

}
