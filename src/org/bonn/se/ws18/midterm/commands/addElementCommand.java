package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.Container;

public class addElementCommand implements Command, UndoableCommand{
	
	private String lastActor;
	
    @Override
    public void execute(String[] args){
        //ziemlich hard coded noch, eventuell ein Element interface machen fuer generik
        try {
            if (args[1].equals("-") && args[2].equals("actor")) {
                Container c = Container.getInstance();
                	lastActor = args[3];
                    c.setActor(args[3]);

            }
            else {
            	System.out.println("Ungueltige Eingabe");
            }
        } catch (Exception e){
            System.out.println("Ungueltige Eingabe");
            e.printStackTrace();
        }

    }
    
    public void undo() {
    	if(lastActor == null) {
    		System.out.println("Nothing to undo!");
    	}
    	else{
    		Container c = Container.getInstance();
    	
    		if(c.removeActor(lastActor)) {
    			
    			System.out.println("Der Akteur \"" + lastActor +  "\" wurde entfernt!");
    			lastActor = null;		
    		}
    		else {
    			System.out.println("Der Akteur wurde bereits entfernt/ist nicht vorhanden!");
    		}
    	}
    }
}
