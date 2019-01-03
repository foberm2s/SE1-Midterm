package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.*;

public class SetStatusCommand implements Command {

	public void execute(String[] args) {
		if(args.length != 3) {
			System.out.println("Falsche Parameteranzahl! Usage: \"status [id] [status]\" ");
			return;
		}
		
		int id = 0;
		try {
			id =  Integer.parseInt(args[1]);
		}
		catch (java.lang.NumberFormatException e) {
			System.out.print(args[1]+ " ist keine Zahl! Usage: \"status [id] [status]\" ");
			return;
		}
		
		Container container = Container.getInstance();
		UserStory u = container.getUserStory(id);
		
		if(u == null) {
			System.out.println("User Story mit der id "+ id + " ist nicht vorhanden. Bitte w�hlen sie eine g�ltige ID.");
			return;
		}
		
		else {
			UserStory.Status alterStatus = u.getStatus();
			switch(args[2]) {
	    	case "done":
	    	case "Done":
	    		u.setStatus( UserStory.Status.DONE);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" + alterStatus.toString().toLowerCase() + "\" in \"done\" ge�ndert!");
	    		return;
	    	case "progress":
	    	case "Progress":
	    		u.setStatus( UserStory.Status.PROGRESS);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" + alterStatus.toString().toLowerCase() + "\" in \"progress\" ge�ndert!");
	    		return;
	    	case "todo":
	    	case "Todo":
	    	case "TODO":
	    		u.setStatus( UserStory.Status.TODO);
	    		System.out.println("Der Status User Story mit der ID " + id + " wurde von \"" + alterStatus.toString().toLowerCase() + "\" in \"todo\" ge�ndert!");
	    		return;
	    	default:
	    		System.out.println(args[2] + " ist kein g�ltiger Stautus. Bitte w�hlen Sie zwischen \"done\", \"progress\" und \"todo\". ");
	    	}
		}
	}

}