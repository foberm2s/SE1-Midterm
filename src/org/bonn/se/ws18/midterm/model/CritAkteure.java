package org.bonn.se.ws18.midterm.model;

import java.util.ArrayList;

public class CritAkteure implements Criterias {
    @Override
    public int determineQual(UserStory us) {
        int x = 0;
        Container c = Container.getInstance();
        ArrayList<String> a = c.getActors();
        for(String s: us.getActors()){
            if (a.contains(s)){

            } else{
                x += 20;
                String d  = "\n Akteur ("+ s + ") nicht im System bekannt. (-20%)";
                us.setDetails(us.getDetails() + d);
                String h =  "\n Entfernen Sie den Akteur (" + s + ").";
                us.setHints(us.getHints() + h);

            }
        }
        return x;
    }
}
