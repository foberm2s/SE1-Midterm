package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.Container;

public class addElementCommand implements Command{
    @Override
    public void execute(String[] args){
        //ziemlich hard coded noch, eventuell ein Element interface machen fuer generik
        try {
            if (args[1].equals("-") && args[2].equals("actor")) {
                Container c = Container.getInstance();
                    c.setActor(args[3]);

            }
        } catch (Exception e){
            System.out.println("Ungueltige Eingabe");
            e.printStackTrace();
        }

    }
}
