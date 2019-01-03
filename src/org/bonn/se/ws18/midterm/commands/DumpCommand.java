package org.bonn.se.ws18.midterm.commands;

import java.util.List;
import org.bonn.se.ws18.midterm.dtos.UserStoryDTO;
import org.bonn.se.ws18.midterm.model.Container;
import org.bonn.se.ws18.midterm.model.UserStory;
import org.bonn.se.ws18.midterm.views.AusgabeSortiert;
public class DumpCommand implements Command{
    //TODO: umzusetzen nach Filter-Map-Reduce
    public void execute(String[] args) {
        Container container = Container.getInstance();

        // Ermittlung der aktuellen Geschaeftsobjekte (=Model)
        List<UserStoryDTO> liste = container.getCurrentListOfUserStoriesAsDTO();

        // Übergabe dieser an die Ausgabe-Objekte (= View)
        // Vorteil: strikte Trennung der View-Objekte von dem Model (hier: Klasse Container)
        AusgabeSortiert ausgabe = new AusgabeSortiert( );
        ausgabe.display( liste );

        // An dieser Stelle koennte auch eine Ausgabe an alle aktuell verfügbaren
        // AusgabeConsole-Objekten erfolgen. Vorgabe dafür: Objekte müssen das
        // Interface AusgabeConsole implementiert haben. High-Level Code:

        // for ( AusgabeConsole ausgabe : List der registriereten Ausgabe-Objekte ) {
        //	ausgabe.start();
        // }

    }
    
}
