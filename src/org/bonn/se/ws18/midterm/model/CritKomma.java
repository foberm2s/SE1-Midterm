package org.bonn.se.ws18.midterm.model;

public class CritKomma implements Criterias {
    @Override
    public int determineQual(UserStory us) {
        int x = 0;
        int count = 0;
        for (int i=0; i < us.getTitel().length(); i++)
        {
            if (us.getTitel().charAt(i) == ',')
            {
                count++;
            }
        }
        if (count == 0){
            x += 20;
            String d = "\n Keine Kommata zu erkennen (-20%)";
             us.setDetails(us.getDetails() + d);
            String h = "\n Ist ihre User Story aussagekraeftig genug?";
            us.setHints(us.getHints() + h);
        } else if(count > 4){
            x += 25;
            String d = "\n Zu viele Kommata (-25%)";
            us.setDetails(us.getDetails() + d);
            String h = "\n Ist ihre User Story zu komplex formuliert?";
            us.setHints(us.getHints() + h);
        }
        return x;
    }
}
