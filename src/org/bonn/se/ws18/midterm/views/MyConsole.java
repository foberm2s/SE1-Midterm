package org.bonn.se.ws18.midterm.views;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyConsole {
    private BufferedReader input = null;


    public MyConsole(){
        input = new BufferedReader( new InputStreamReader(System.in ) );
    }

    public String readLine(String prompt){
        String strInput = null;

        System.out.print( prompt );
        try {

            strInput = input.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return strInput;
    }

    public String readLine() {
        return this.readLine("> ");
    }

    public int readLineInt(String prompt){
        String strInput = null;
        System.out.print(prompt);

        // Eingabe des Wertes
        try {
            strInput = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Umwandlung nach Integer --> int
        int id = 0;
        try {
        	
            //id = new Integer( strInput ).intValue(); --> nicht mehr unterstützt in  java 9
        	
        	id = Integer.parseInt(strInput);  //alternativ
        	
        } catch (java.lang.NumberFormatException e){
            System.out.println("Das ist keine Zahl!");
            return this.readLineInt(prompt);
        }
        return id;
    }
}

