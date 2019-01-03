package org.bonn.se.ws18.midterm.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;
import org.bonn.se.ws18.midterm.exceptions.ContainerException;


/*
 * Klasse zum Abspeichern von User Stories in einer Liste
 * Diese Klasse repräsentiert laut MVC-Pattern das Model
 *
 * c/o Sascha Alda, H-BRS, 2015
 *
 */

public class Container {

    // Interne ArrayList zur Abspeicherung der Objekte
    private List<UserStory> liste = null;

    // Statische Klassen-Variable, um die Referenz
    // auf das einzige Container-Objekt abzuspeichern
    private static Container instance = new Container();

    private final static String LOCATION = ".\\userstories.txt";

    // Maximale Anzahl von UserStory-Objekten in einem Container
    private final static int MAX_ANZAHL = 20;

    /*
     * Statische Methode um die einzige Instanz der Klasse
     * Container zu bekommen. Das Keyword synchronized bewirkt,
     * dass garantiert nur ein Objekt den alleinigen Zugriff
     * auf diese Methode hat. Anonsten koennte es passieren, dass
     * zwei parallel zugreifende Objekte zwei unterschiedliche
     * Objekte erhalten (vgl. auch Erlaeuterung in Uebung)
     *
     */
    public static synchronized Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    /*
     * Ueberschreiben des Konstruktors. Durch die Sichtbarkeit private
     * kann man von außen die Klasse nicht mehr instanziieren,
     * sondern nur noch kontrolliert ueber die statische Methode
     * der Klasse Container!
     *
     */
    private Container(){
        liste = new ArrayList<UserStory>();

    }


    /*
     * Methode zum Hinzufuegen einer UserStory.
     */
    public void addUserStory ( UserStory r ) throws ContainerException {
        if (this.getAnzahl() == Container.MAX_ANZAHL ) {
            ContainerException ex = new ContainerException("Maximale Anzahl von User Stories erreicht (20)!");
            throw ex;
        }

        if ( contains(r) == true ) {
            ContainerException ex = new ContainerException("ID bereits vorhanden!");
            throw ex;
        }
        liste.add(r);

    }

    /*
     * Methode zur Ueberpruefung, ob ein Person-Objekt in der Liste enthalten ist
     *
     */
    public boolean contains(UserStory r) {
        int ID = r.getId();
        for ( UserStory rec : liste) {
            if ( rec.getId() == ID ) {
                return true;
            }
        }
        return false;
    }


    /*
     * Methode zur Bestimmung der Anzahl der von Person-Objekten
     *
     */
    public int getAnzahl(){
        return liste.size();
    }

    /*
     * Methode zur Auslieferung der UserStory-Objekte.
     * Es werden keine Referenzen auf die Entity selber übergeben,
     * sondern nur DTO
     *
     */
    public List<UserStoryDTO> getCurrentListOfUserStoriesAsDTO() {
        List<UserStoryDTO> listeDTO = new ArrayList<UserStoryDTO>();

        // UserStoryDTO werden nun nacheinander aus den originalen UserStory-Objekten erzeugt
        for ( UserStory userStory : this.liste ) {
            UserStoryDTO dto = new UserStoryDTO();
            dto.setPrio( userStory.getPrio() );
            dto.setTitel( userStory.getTitel() );

            listeDTO.add(dto);
        }

        return listeDTO;
    }


    /*
     * Interne Methode zur Ermittlung einer Person
     *
     */
    public UserStory getUserStory(int id) {
        for ( UserStory rec : liste) {
            if (id == rec.getId() ){
                return rec;
            }
        }
        return null;
    }

    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */
    public void store() {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream( Container.LOCATION );
            oos = new ObjectOutputStream(fos);

            try {
                oos.writeObject( liste );
                System.out.println(this.getAnzahl() + " User Stories wurden erfolgreich gespeichert!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (oos != null) try { oos.close(); } catch (IOException e) {}
            if (fos != null) try { fos.close(); } catch (IOException e) {}
        }

    }

    /*
     * Methode zum Laden der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte geladen.
     * Die geladene Liste überschreibt aktuell die vorhandenen Objekte
     * in der Liste --> Optimierung notwendig ;-!
     *
     */
    public void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( Container.LOCATION );
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                this.liste = (List) obj;

            }
            System.out.println("Es wurden " + this.getAnzahl() + " User Stories erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("FEHLER: Datei konnte nicht gefunden werden!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("FEHLER: Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try { ois.close(); } catch (IOException e) {}
            if (fis != null) try { fis.close(); } catch (IOException e) {}
        }
    }

}
