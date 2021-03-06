package org.bonn.se.ws18.midterm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserStory implements Comparable<UserStory>, Serializable {

	private static final long serialVersionUID = 1L;
	
	String titel;
    int aufwand = 0;
    int id = 0;
    int mehrwert = 0;
    int risk = 0;
    int strafe = 0;
    double prio = 0.0;
    Status status = null;
    //us_md_1
    int qual = 0;
    //us_md_2
    String details = "";
    String hints = "";
    List<String> actors;


    public UserStory(int id, String titel, int mehrwert, int strafe,
                     int aufwand, int risk, double prio, String[] actor) {
        this.id = id;
        this.titel = titel;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risk = risk;
        this.prio = prio;
        this.status = Status.TODO;
        actors = new ArrayList<String>();
        for(String s: actor){
            this.actors.add(s);
        }
    }

    public void setStatus(Status s) {
        this.status = s;
    }

    public Status getStatus() {
        return this.status;
    }

    public double getPrio() {
        return prio;
    }

    public void setPrio(double prio) {
        this.prio = prio;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getAufwand() {
        return aufwand;
    }

    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMehrwert() {
        return mehrwert;
    }

    public void setMehrwert(int mehrwert) {
        this.mehrwert = mehrwert;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public int getStrafe() {
        return strafe;
    }

    public void setStrafe(int strafe) {
        this.strafe = strafe;
    }

    //us_md_1
    public void setQual() {
        CritManager m = CritManager.getInstance();
        qual = m.execute(this);
    }

    public int getQual() {
        return qual;
    }


    public String getDetails(){
        return details;
    }
    public String getHints(){
        return hints;
    }
    public void setHints(String d){
        hints = d;
    }
    public void setDetails(String d){
        details = d;
    }

    /*
     * Methode zum Vergleich zweier UserStories.
     * Vergleich ist implementiert auf Basis des Vergleichs
     * von zwei Prio-Werten.
     */
    public int compareTo(UserStory input) {
        if (input.getPrio() == this.getPrio()) {
            return 0;
        }

        if (input.getPrio() > this.getPrio()) {
            return 1;
        } else return -1;
    }
    //us_md_2, akteure adden
    public void setActors(String a) {
        actors.add(a);
    }
    public List<String> getActors(){
        return actors;
    }

}

