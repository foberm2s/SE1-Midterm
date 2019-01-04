package org.bonn.se.ws18.midterm.exceptions;

public class ContainerException extends Exception {

    private String modus;
    private Integer id;

    public ContainerException( String s ) {
        super ( s );
    }

    @Override
    public void printStackTrace() {
        System.out.println("Das UserStory-Objekt mit der ID " + this.id + " ist bereits vorhanden!");
    }

    public void addID(Integer id) {
        this.id = id;
    }


}