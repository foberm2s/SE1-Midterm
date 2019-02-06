package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.UserStory;

import java.util.List;

public class AnalyzeCommand implements Command {
    //Analyze Befehl (US_MD_1) fuer das Midterm Projekt in SE1

//hahahafdsfsdfw
    public void execute(String[] arr) {
         	Container c = Container.getInstance();
         	
         	if(arr.length == 2 || (arr.length > 2 && !arr[2].equals("all"))) { //Fall 1: Es geht um eine spezifische UserStory
         		
         		UserStory us = null;
         		try {
         			us = c.getUserStory(Integer.parseInt(arr[1]));
         		}
         		catch (java.lang.NumberFormatException e) {						
         			System.out.print(arr[1]+ " ist keine Ganzzahl!");		//Fehlerbehandlung: als ID wurde keine ganzzahl angegeben.
         			return;
         		}
         		
         		if(us == null) {
         			System.out.print("arr[1] ist keine gueltige id!");		//Fehlerbehandlung: ungültige ID
         			return;
         		}
         		
         		us.setQual();
         		
         		int qual = us.getQual();
         		
                System.out.println("Die User Story mit der ID " + arr[1] + 
                		" hat die Qualitaet: \n" + qual + "% " + qualHelper(qual));
                
                if (arr.length >= 4 && arr[2].equals("-") && arr[3].equals("details")){  //Fall 1.2: Es geht um eine spezifische UserStory + details
                    System.out.println("Details:");
                    System.out.println(us.getDetails());
                    System.out.print("\n");

                }
               
                if (arr.length == 6 && arr[4].equals("-") && arr[5].equals("hints")){ //Fall 1.3 Es geht um eine spezifische UserStory + details + hints
                        System.out.println("Hints:");
                        System.out.println(us.getHints());
                }
                us.setHints("");													// reset details + hints
                us.setDetails("");										
                
            } 
         	else if (arr.length == 3 && arr[1].equals("-") && arr[2].equals("all")) {		// Fall 2: Es sollen alle UserStories analysiert werden.
         		int x = 0;
                List<UserStory> ls = c.getUserStoryList();
                
                if(ls.isEmpty()) {												//Fehlerbehandlung: Keine UserStories vorhanden
                	System.out.print("Keine User Storys vorhanden!");
                	return;
                }
                
                int count = 0;
                for (UserStory u : ls) {
                     ++count;
                     x += u.getQual();
                }
                double aus = x/count;
                System.out.println("Ihre " + ls.size() + " User Stories haben durchschnittlich folgende Qualitaet: \n" + aus + "% " + qualHelper((int)Math.round(aus)));
            }
         	else {
         		System.out.println("Falsche Syntax!"); 							// Fehlerbehandlung falsche Syntax
         	}
           
    } 
    


    private String qualHelper(int a){
        if (a <= 100 && a >= 0) {
            if (a >= 90) {
                return "(sehr gut)";
            } else if (a >= 75) {
                return "(gut)";
            } else if (a >= 60) {
                return "(befriedigend)";
            } else if (a >= 50) {
                return "(ausreichend)";
            } else if (a >= 25) {
                return "(mangelhaft)";
            } else {
                return "(ungenuegend)";
            }

        } else{
            return "(irgendwas stimmt mit dieser UserStory nicht.)";
        }
    }
}