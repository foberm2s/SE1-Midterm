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
 * Diese Klasse repräsentiert laut MVC-Pattern den Controller,
 * zuständig für die Eingabe und für das Zusammenspiel zwischen
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
        // alle Commands werden in einer HashMap abgespeichert.
        commands = new HashMap<String,Command>();
        CritManager p = CritManager.getInstance();

        // fuenf Commands werden gespeichert (ohne help)
        // Optimierung: Auslesen der Befehle aus den Command-Klasse
        // (hier ausgelassen zur besseren Illustration)
        commands.put("exit", new ExitCommand() );
        commands.put("enter", new EnterUserStoryCommand() );
        commands.put("dump", new DumpCommand() );
        commands.put("store", new StoreCommand() );
        commands.put("load", new LoadCommand() );
        commands.put("status", new SetStatusCommand() );
        commands.put("analyze", new AnalyzeCommand());
        commands.put("addElement", new addElementCommand());
        commands.put("actors", new ActorCommand());
        CritKomma c1 = new CritKomma();
        CritAkteure c2 = new CritAkteure();
        CritMehrwert c3 = new CritMehrwert();
        p.addCrit(c1);
        p.addCrit(c2);
        p.addCrit(c3);
    }

    public void start(){

        // Ausgabe eines Texts zur Begruessung
        System.out.println("Prio-Tool V2.0");
        System.out.println("c/o Sascha Alda in 2015\n");

        String strInput = null;

        // Initialisierung des Eingabe-View
        MyConsole console = new MyConsole();

        // So lange Eingaben getaetigt werden wird die Schleife durchlaufen.
        while ( true ) {
            try {
                strInput = console.readLine("> ");

            } catch (Exception e) {
            }

            // Extrahiert ein Array aus der Eingabe
            // (hiermit koennten auch Argumente extrahiert und den Commands uebergeben werden
            String[] strings = strInput.split(" ");

            // Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if ( strings[0].equals("help") ) {
                System.out.println("Folgende Befehle stehen zur Verfuegung:");
                Set<String> kommandos = commands.keySet();
                Iterator<String> it = kommandos.iterator();

                while ( it.hasNext() ) {
                    System.out.println( it.next() ) ;
                }

            } else {

                // Ermittelt das Kommando aus der HashMap, das ueber die Console eingegeben wurde
                Command command = commands.get(strings[0]);
                if (strings[0].equals("analyze")) {
                    command.execute(strings);
                } else if ( (command == null) ) {
                    System.out.println("Kommando " + strings[0] + " nicht unterstuetzt!");
                } else {
                    command.execute(strings);
                }
            }
        } // Ende der Schleife
    }

    public static void main (String[] args)  {
        // System.out.println("Anzahl: " + args[0].toString() );

        PrioTool prio = new PrioTool( );
        prio.start();
    }

}
