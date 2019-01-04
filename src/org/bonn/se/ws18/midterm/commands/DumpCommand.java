package org.bonn.se.ws18.midterm.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;
import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.UserStory;
import org.bonn.se.ws18.midterm.views.AusgabeSortiert;

public class DumpCommand implements Command{

	private UserStory.Status gesucht = null;
	
    public void execute(String[] args) {
    	
    	Container container = Container.getInstance();
	    List<UserStoryDTO> liste = container.getCurrentListOfUserStoriesAsDTO();
	    AusgabeSortiert ausgabe = new AusgabeSortiert( ); 
	    
	    
        if(args.length == 1) {
	    	
        	ausgabe.display( liste );

        }
        else if(args.length == 3 && args[1].equals("-status")){
        	
        	
        	
        	switch(args[2]) {
	    	case "done":
	    	case "Done":
	    		gesucht = UserStory.Status.DONE;
	    		break;
	    	case "progress":
	    	case "Progress":
	    		gesucht = UserStory.Status.PROGRESS;
	    		break;
	    	case "todo":
	    	case "Todo":
	    	case "TODO":
	    		gesucht = UserStory.Status.TODO;
	    		break;
	    	default:
	    		System.out.println(args[2] + " ist kein gültiger Stautus. Bitte wählen Sie zwischen \"done\", \"progress\" und \"todo\". ");
	    		return;
	    	}
        	
        	liste = liste.stream()
	 				  .filter(u -> u.getStatus().equals(gesucht))
				      .collect(Collectors.toList());
        	
        	System.out.println("Die Folgenden User Stories haben den Status \"" + this.gesucht +" \": \n" );
        	ausgabe.display(liste);
        }
        else {
        	System.out.println("Ungültiger Parameter! Usage: \"dump [-status [status]]\"" );
        }
    }
    
}
