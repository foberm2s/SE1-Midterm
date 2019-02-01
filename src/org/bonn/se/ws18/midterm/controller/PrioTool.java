package org.bonn.se.ws18.midterm.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.bonn.se.ws18.midterm.commands.*;
import org.bonn.se.ws18.midterm.model.CritAkteure;
import org.bonn.se.ws18.midterm.model.CritKomma;
import org.bonn.se.ws18.midterm.model.CritManager;
import org.bonn.se.ws18.midterm.model.CritMehrwert;
import org.bonn.se.ws18.midterm.views.MyConsole;


/*
 * Tool zur Priorisierung von User Stories
 * (Musterloesung Uebung 4, WS 2015-16, Vorlesung SE-I)
 *
 * Diese Klasse repraesentiert laut MVC-Pattern den Controller,
 * zustaendig fuer die Eingabe und fuer das Zusammenspiel zwischen
 *
 * c/o Sascha Alda, H-BRS, 2015
 *
 */

public class PrioTool {

    // interne HashMap zur Speicherung der Kommandos (commands..)
    private HashMap<String,Command> commands = null;

    public PrioTool( ){
        setupCommands();
    }



    /*
     * Initialisierung aller Kommandos des Tool.
     * (Entwicklung nach Command Design Pattern (Gamma, 1995)
     *
     */
    private void setupCommands() {
        commands = new HashMap<String,Command>();					//alle Commands werden in einer HashMap abgespeichert.
        CritManager p = CritManager.getInstance();					//alle Kriterien, nach denen eine User Story analysiert wird, werden im CritManager gespeichert.

        commands.put("exit", new ExitCommand() );					//setze die Commands in die Hashmap
        commands.put("enter", new EnterUserStoryCommand() );
        commands.put("dump", new DumpCommand() );
        commands.put("store", new StoreCommand() );
        commands.put("load", new LoadCommand() );
        commands.put("status", new SetStatusCommand() );
        commands.put("analyze", new AnalyzeCommand());
        commands.put("addElement", new addElementCommand());
        commands.put("actors", new ActorCommand());
        CritKomma c1 = new CritKomma();								// erzeuge die konkreten Kriterien-Klassen und setzte diese in den Critmanager.
        CritAkteure c2 = new CritAkteure();
        CritMehrwert c3 = new CritMehrwert();
        p.addCrit(c1);
        p.addCrit(c2);
        p.addCrit(c3);
    }

    public void start(){

        
        System.out.println("Prio-Tool V2.0");						// Ausgabe eines Texts zur Begruessung
        System.out.println("c/o Sascha Alda in 2015\n");

        String strInput = null;

        
        MyConsole console = new MyConsole();						// Initialisierung des Eingabe-View

        
        while ( true ) {											// So lange Eingaben getaetigt werden wird die Schleife durchlaufen.
            try {
                strInput = console.readLine("> ");

            } catch (Exception e) {
            }

            														
            String[] strings = strInput.split(" ");					// Extrahiert ein Array aus der Eingabe
            														// (hiermit koennten auch Argumente extrahiert und den Commands uebergeben werden)

            if ( strings[0].equals("help") ) {						// Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
                System.out.println("Folgende Befehle stehen zur Verfuegung:");
                Set<String> kommandos = commands.keySet();
                Iterator<String> it = kommandos.iterator();

                while ( it.hasNext() ) {
                    System.out.println( it.next() ) ;
                }

            } else {

                
                Command command = commands.get(strings[0]);			// Ermittelt das Kommando aus der HashMap, das ueber die Console eingegeben wurde
                if (strings[0].equals("analyze")) {
                    command.execute(strings);
                } else if ( (command == null) ) {
                    System.out.println("Kommando " + strings[0] + " nicht unterstuetzt!");
                } else {
                    command.execute(strings);
                }
            }
        } 															// Ende der Schleife
    }

    public static void main (String[] args)  {
        PrioTool prio = new PrioTool( );
        prio.start();
    }

}
