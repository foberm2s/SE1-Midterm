package org.bonn.se.ws18.midterm.commands;

import org.bonn.se.ws18.midterm.model.Container;

import java.util.List;

public class ActorCommand implements Command{
    @Override
    public void execute(String[] args) {
        try {
            Container a = Container.getInstance();
            List<String> list = a.getActors();
            for (String s : list) {
                System.out.println(s);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
