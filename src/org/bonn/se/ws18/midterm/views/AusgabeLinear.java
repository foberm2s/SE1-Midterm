package org.bonn.se.ws18.midterm.views;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;

public class AusgabeLinear implements IDialog {

    //private List<UserStoryDTO> liste = null; ----> unnötig, aber in der ML!



    public void display(List<UserStoryDTO> list) {
        // Falls Liste leer kurze Ausgabe und raus.
        if ( list.size() == 0 ) {
            System.out.println("Keine User Stories vorhanden");
            return;
        }



        // Ausgabe ueber einen Iterartor (Ausgabe kann auch optimiert werden ;-)):
        Iterator<UserStoryDTO> i = list.iterator();
        while (  i.hasNext() ) {
            UserStoryDTO p = i.next();
            System.out.println("Titel: " + p.getTitel());
            System.out.print("  Prio: " + p.getPrio());

            System.out.println("\n");
        }
        System.out.println("Aktuelle Anzahl User Stories: " + list.size() );
    }

}