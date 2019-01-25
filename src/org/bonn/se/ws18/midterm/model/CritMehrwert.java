package org.bonn.se.ws18.midterm.model;

public class CritMehrwert implements Criterias {
    @Override
    public int determineQual(UserStory us) {
        int mehr = us.getMehrwert();
        if (mehr < 1 || mehr > 5) {
            String d = "\n Kein korrekter Mehrwert zu erkennen (-30%)";
            us.setDetails(us.getDetails() + d);
            String h = "\n Fuegen Sie einen schriftlichen Mehrwert zwischen 1 und 5 hinzu!";
            us.setHints(us.getHints() + h);
            return 30;
        }
        return 0;
    }
}
