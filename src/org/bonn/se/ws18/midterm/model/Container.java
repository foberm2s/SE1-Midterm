package org.bonn.se.ws18.midterm.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;
import org.bonn.se.ws18.midterm.exceptions.ContainerException;


/*
 * Klasse zum Abspeichern von User Stories + Actors in einer Liste
 * Diese Klasse repraesentiert laut MVC-Pattern das Model
 *
 * c/o Sascha Alda, H-BRS, 2015
 *
 */

public class Container {

    
    private List<UserStory> liste = null;							// Interne ArrayList zur Abspeicherung der Objekte
    
    private static ArrayList<String> actors;						// Interne ArrayList zur Abspeicherung der registrierten Actors
    
    private static Container instance = new Container();			// Statische Klassen-Variable, um die Referenz
    																// auf das einzige Container-Objekt abzuspeichern
    private final static String LOCATION = ".\\userstories.txt";    //Pfad der Datei, in welcher die Liste bei Bedarf gespeichert wird

    private final static int MAX_ANZAHL = 20;						// Maximale Anzahl von UserStory-Objekten in einem Container
    
   

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
     * kann man von aussen die Klasse nicht mehr instanziieren,
     * sondern nur noch kontrolliert ueber die statische Methode
     * der Klasse Container!
     *
     */
    private Container(){
        liste = new ArrayList<UserStory>();
        actors = new ArrayList<String>();									//Registrierung von Default-Actors
        actors.add("Professor");
        actors.add("Student");

    }

    public void addUserStory ( UserStory r ) throws ContainerException {	//Methode zum Hinzufuegen einer UserStory.
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
    
    public List<UserStory> getUserStoryList(){                       		//getter-Methode fuer die zugrundeliegende Liste der UserStories. (Wird vom analyze-Command benutzt)
        return liste;
    }
    
    public ArrayList<String> getActors(){									//Getter-Methode der Actor-Liste (benutzt vom Actor und AddElement command)
        return actors;
    }
    
    public boolean contains(UserStory r) {									//Methode zur Ueberpruefung, ob ein Person-Objekt in der Liste enthalten ist
        int ID = r.getId();
        for ( UserStory rec : liste) {
            if ( rec.getId() == ID ) {
                return true;
            }
        }
        return false;
    }


    public int getAnzahl(){													//Methode zur Bestimmung der Anzahl der von Person-Objekten
        return liste.size();
    }

    /*
     * Methode zur Auslieferung der UserStory-Objekte.
     * Es werden keine Referenzen auf die Entity selber Uebergeben,
     * sondern nur DTO
     *
     */
    public List<UserStoryDTO> getCurrentListOfUserStoriesAsDTO() {
        List<UserStoryDTO> listeDTO = new ArrayList<UserStoryDTO>();

        
        for ( UserStory userStory : this.liste ) {							// UserStoryDTO werden nun nacheinander aus den originalen UserStory-Objekten erzeugt
            UserStoryDTO dto = new UserStoryDTO();
            dto.setPrio( userStory.getPrio() );
            dto.setTitel( userStory.getTitel() );
            dto.setStatus(userStory.getStatus());
            listeDTO.add(dto);
        }

        return listeDTO;
    }

    public UserStory getUserStory(int id) {									//Interne Methode zur Ermittlung einer User Story anhander einer ID
        for ( UserStory rec : liste) {
            if (id == rec.getId() ){
                return rec;
            }
        }
        return null;
    }

    public void store() {													//Methode zum Speichern der Liste der Userstories. 
        ObjectOutputStream oos = null;										//Es wird die komplette Liste inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
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
     * Die geladene Liste Ueberschreibt aktuell die vorhandenen Objekte
     * in der Liste --> Optimierung notwendig 
     *
     */
    @SuppressWarnings("unchecked")
	public void load() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( Container.LOCATION );
            ois = new ObjectInputStream(fis);

            
            Object obj = ois.readObject();										// Auslesen der Liste
            if (obj instanceof List<?>) {
                this.liste = (List<UserStory>) obj;

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
    public void setActor(String s){											//Methode zum Hinzufügen von registrieten Actors (vom addElement-Command benutzt)
        if (actors.contains(s)){
            System.out.println("Akteur bereits bekannt");
            return;
        }
        actors.add(s);
        System.out.println("Akteur erfolgreich hinzugefuegt");
    }
   





}
