package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.UserStory;

import java.util.List;

public class AnalyzeCommand implements Command {
    //Analyze Befehl (US_MD_1) fuer das Midterm Projekt in SE1


    public void execute(String[] arr) {
        try {
            Container c = Container.getInstance();
            //ich brauche hier im Container die Klasse getUserStory
            //analyze 2, analyze 2 - details
            UserStory us = c.getUserStory(Integer.parseInt(arr[1]));
            us.setQual();
            int qual = us.getQual();
            if (arr.length > 3 || !arr[2].equals("all")) {
                try {

                    System.out.println("Die User Story mit der ID " + arr[1] + " hat die Qualitaet: \n" + qual + "% " + qualHelper(qual));
                    if (arr.length < 4){
                        return;
                    }
                    //
                    if (arr[2].equals("-") && arr[3].equals("details")){
                        System.out.println("Details: \n");
                        System.out.println(us.getDetails());

                    }
                    if (arr.length < 6){
                        return;
                    }
                    if (arr[4].equals("-") && arr[5].equals("hints")){
                        System.out.println("Hints: \n");
                        System.out.println(us.getHints());
                    }
                    //
                    //
                    if (arr[2].equals("-") && arr[3].equals("hints")){
                        System.out.println("Hints: \n");
                        System.out.println(us.getHints());

                    }
                    if (arr.length < 6){
                        return;
                    }
                    if (arr[4].equals("-") && arr[5].equals("details")){
                        System.out.println("Details: \n");
                        System.out.println(us.getDetails());
                    }
                    //

                } catch (Exception e) {
                    e.printStackTrace();
                }
            //analyze - all
            } else if (arr[2].equals("all")) {
                int x = 0;
                try {
                    List<UserStory> ls = c.getUserStoryList();
                    int count = 0;
                    for (UserStory u : ls) {
                        ++count;
                        x += u.getQual();
                    }
                    x /= count;
                    System.out.println("Ihre " + ls.size() + " User Stories haben durchschnittlich folgende Qualitaet: \n" + (x / (ls.size())) + "% " + qualHelper(x));
                } catch (Exception e) {
                    System.out.println("Ungueltige Eingabe");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Ungueltige Eingabe");
            e.printStackTrace();
        }
    }


    private String qualHelper(int a) throws Exception{
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
            throw new Exception("Ungueltige Prozentzahl: " + a);
        }
    }
}
