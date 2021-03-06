package org.bonn.se.ws18.midterm.commands;


import org.bonn.se.ws18.midterm.exceptions.ContainerException;
import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.UserStory;
import org.bonn.se.ws18.midterm.util.*;
import org.bonn.se.ws18.midterm.views.MyConsole;

public class EnterUserStoryCommand implements Command, UndoableCommand{

    private MyConsole console = null;
    
    private Integer LastId = null;

    public void execute(String[] args) {
        // erzeuge Console (moegliche Optimierung: Uebergabe als Parameter, Singleton...
        console = new MyConsole();

        // Ausgabe einer Eingabeaufforderung
        System.out.println("Geben sie die Grunddaten der User Story ein:");

        // Eingabe der ID:
        int id = console.readLineInt("ID der User Story: ");

        // Abfrage der einzelnen Werte der User Story:
        String titel = console.readLine("Titel der User Story: ");

        // Eingabe des Aufwands:
        int aufwand = this.eingabeAufwand();

        // Eingabe des Risikos:
        int risk = console.readLineInt("Risiko der User Story: ");

        // Eingabe des Mehrwerts:
        int mehrwert = console.readLineInt("Mehrwert der User Story: ");

        // Eingabe der Strafe:
        int strafe = console.readLineInt("Strafe der User Story: ");

        // Berechnung der Priorisierung
        double prio = Util.berechnePrio( mehrwert, strafe, aufwand, risk );


        String actor = console.readLine("Registrieren Sie die Akteure (komma-separiert):  ");
        String[] actors = actor.split(", ");
        System.out.println("User Story mit ID: " + id + " hat die Prio: " + prio);
        // Neues Objekt vom Typ UserStory
        UserStory us = new UserStory( id, titel, mehrwert, strafe, aufwand, risk , prio, actors);

        // Abspeichern des PrioWertes:
        Container container = Container.getInstance();

        try {
            container.addUserStory( us );
            LastId = id;
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }


    /*
     * Methode zur Eingabe des Aufwands. Es wird geprueft, ob der Aufwand einer
     * Fibonacci-Zahl entspricht.
     *
     */
    private int eingabeAufwand() {
        int aufwand = console.readLineInt("Aufwand der User Story: ");

        boolean status = Util.checkFibonacci( aufwand );

        if (status == false) {
            System.out.println("Die Zahl " + aufwand + " ist keine Fibonacci-Zahl!");
            return this.eingabeAufwand();
        }
        return aufwand;
    }


	@Override
	public void undo() {
		if(LastId == null) {
			System.out.println("Nothing to Undo!");
		}
		else {
			Container c = Container.getInstance();
			if(c.removeUserStory(LastId)) {
				System.out.println("User Story mit der ID " + LastId + " wurde entfernt");
			}
			else {
				System.out.println("Die User Story wurde bereits entfernt/existiert nicht");
			}
		}
		
	}

}
