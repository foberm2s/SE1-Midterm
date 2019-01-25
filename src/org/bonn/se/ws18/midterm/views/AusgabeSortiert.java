package org.bonn.se.ws18.midterm.views;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;

public class AusgabeSortiert implements IDialog {

    //private List<UserStoryDTO> liste = null;        ----> unnötig, aber in der ML!


    /*
     * Methode zur Ausgabe aller IDs der Person-Objekte
     *
     */
    @Override
    public void display( List<UserStoryDTO> liste ){
        // Falls Liste leer kurze Ausgabe und raus.
        if ( liste.size() == 0 ) {
            System.out.println("Keine User Stories vorhanden");
            return;
        }

        // Sortierung mit der Methode sort aus Collection:
        Collections.sort( liste );

        // Ausgabe ueber einen Iterartor (Ausgabe kann auch optimiert werden ;-)):
        Iterator<UserStoryDTO> i = liste.iterator();
        while (  i.hasNext() ) {
            UserStoryDTO p = i.next();
            System.out.println("Titel: " + p.getTitel());
            System.out.println("Prio: " + p.getPrio());


            System.out.println("\n");
        }
        System.out.println("Aktuelle Anzahl User Stories: " + liste.size() );
    }




}