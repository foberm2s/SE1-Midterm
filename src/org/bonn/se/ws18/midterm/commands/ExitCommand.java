package org.bonn.se.ws18.midterm.commands;

public class ExitCommand implements Command {
    public void execute(String[] args) {
        System.out.println("Aufwiedersehen und bis zum naechsten Mal!");
        System.exit(0);

    }
}
