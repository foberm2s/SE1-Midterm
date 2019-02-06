package org.bonn.se.ws18.midterm.commands;

import java.util.List;
import java.util.stream.Collectors;

import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;
import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.Status;
import org.bonn.se.ws18.midterm.views.AusgabeSortiert;

public class DumpCommand implements Command{

	private Status gesucht = null;										// wenn in methode deklariert dann fehlermeldung, daher als instanzvariable.Aber warum?!
																					//Fehlermeldung: Local variable gesucht in an enclosing scope must be final or effectivly final
    public void execute(String[] args) {
    	Container container = Container.getInstance();
	    List<UserStoryDTO> liste = container.getCurrentListOfUserStoriesAsDTO();
	    AusgabeSortiert ausgabe = new AusgabeSortiert( ); 
	    
	    
        if(args.length == 1) {															// einfacher dump OHNE filtern der UserStorys nach einem status (nur 'dump')
	    	
        	ausgabe.display( liste );

        }
        else if(args.length == 4 && args[1].equals("-") && args[2].equals("status")){  //überprüfen syntax
        	
        	
        	
        	switch(args[3]) {														  // konvertierung String --> status objekt (nach welchem gefliter wird)
	    	case "done":
	    	case "Done":
	    		gesucht = Status.DONE;
	    		break;
	    	case "progress":
	    	case "Progress":
	    		gesucht = Status.PROGRESS;
	    		break;
	    	case "todo":
	    	case "Todo":
	    	case "TODO":
	    		gesucht = Status.TODO;
	    		break;																
	    	default:																	// fehlerbehandlung bei Angabe eines ungültigen status
	    		System.out.println(args[2] + 
	    		" ist kein gültiger Stautus. Bitte wählen Sie zwischen \"done\", \"progress\" und \"todo\". ");
	    		return;
	    	}
        	
        	liste = liste.stream()														// Filtenr der Userstories mit dem gesuchten status (über stream.filter)
	 				  .filter(u -> u.getStatus().equals(this.gesucht))
				      .collect(Collectors.toList());
        	
        	System.out.println("Die Folgenden User Stories haben den Status \"" + this.gesucht +" \": \n" );
        	ausgabe.display(liste);
        }
        else {																			// Fehlerbehandlung bei falscher Syntax
        	System.out.println("Ungültiger Parameter! Usage: \"dump [-status <status>]\"" ); 
        }
    }
    
}
