package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.*;

public class SetStatusCommand implements Command {

	public void execute(String[] args) {
		if(args.length != 3) {																	// fehlerbehandlung falsche parameterzahl
			System.out.println("Falsche Parameteranzahl! Usage: \"status [id] [status]\" ");
			return;
		}
		
		int id = 0;
		try {
			id =  Integer.parseInt(args[1]);
		}
		catch (java.lang.NumberFormatException e) {												// fehlerbehandlung wenn als id keine zahl angegeben wird
			System.out.print(args[1]+ " ist keine Zahl! Usage: \"status [id] [status]\" ");		
			return;
		}
		
		Container container = Container.getInstance();
		UserStory u = container.getUserStory(id);
		
		if(u == null) {
			System.out.println("User Story mit der id "+ id + 									// fehlerbehandlung wenn die userstory mit der id nicht vorhanden ist
					" ist nicht vorhanden. Bitte wählen sie eine gültige ID.");
			return;
		}
		
		else {																					// konvertierung String --> status Objekt
			Status alterStatus = u.getStatus();
			switch(args[2]) {
	    	case "done":
	    	case "Done":
	    		u.setStatus( Status.DONE);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" +
	    		alterStatus.toString().toLowerCase() + "\" in \"done\" geändert!");
	    		return;
	    	case "progress":
	    	case "Progress":
	    		u.setStatus(Status.PROGRESS);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" +
	    		alterStatus.toString().toLowerCase() + "\" in \"progress\" geändert!");
	    		return;
	    	case "todo":
	    	case "Todo":
	    	case "TODO":
	    		u.setStatus( Status.TODO);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" +
	    		alterStatus.toString().toLowerCase() + "\" in \"todo\" geändert!");
	    		return;
	    	default:																			// fehlerbehandlung wenn kein gültiger status angegeben wurde
	    		System.out.println(args[2] +
	    		" ist kein gültiger Stautus. Bitte wählen Sie zwischen \"done\", \"progress\" und \"todo\". ");
	    	}
		}
	}

}
