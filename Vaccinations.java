import core.data.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Vaccinations {
    public static void main(String[] args) {
        DataSource ds = DataSource.connect("https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/vaccinations/us_state_vaccinations.csv");
        ds.setCacheTimeout(15 * 60);
        ds.load();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String today = date.toString();
        // uncommment below line to hard code the date if data for today is not available yet
        // today = "2021-04-25" ;
        System.out.println("Today's date is " + today);

        //create ArrayList
        ArrayList<Vaccinations_Data> allStates = ds.fetchList("Vaccinations_Data", "date",
                "location", "people_vaccinated", "people_vaccinated_per_hundred");


        // Prompt and ask user number of people who vaccinated in their state
        Scanner sc = new Scanner(System.in);
        System.out.println("What state do you live in?");
        String st1 = sc.next();
        int exit = 0;

        // iterates through the whole data set list 
        for (int i = 0; i < allStates.size(); i++ ) {
            String curr_st = allStates.get(i).getState();  //current state variable = the state at that index in the list 
            if (curr_st.equals(st1)) {  // if state = the state user inputted
                for (int j = i; j < allStates.size(); j++) {  //another for loop to get the certain date of curr_st
                    String curr_date = allStates.get(j).getDate(); 
                    if (curr_date.equals(today)) {  // if that date = today's date
                        //double temp = allStates.get(j).getVaccinated();
                        int curr_vaccinated = (int)allStates.get(j).getVaccinated();   //find the number of people vaccinated at that index
                        System.out.println("Your State: " + st1 + " has " + curr_vaccinated + " people vaccinated as of "
                               + today + ".");
                        exit = 1;
                        break;
                    }
                }
            }
            if (exit == 1) break;
        }

        if (exit == 0) {
            System.out.println("The state '" + st1 + "' you entered cannnot be found. Please check the spelling. Make sure you don't use abbreviations and case matters.");
        }

        exit = 0;

        System.out.println();

        // Excludes non-Continental states. Not interested in these
        String[] exceptions = {"American Samoa", "Bureau of Prisons", "Dept of Defense", "Federated States of Micronesia",
        "Guam", "Indian Health Svc", "Long Term Care", "Marshall Islands", "Northern Mariana Islands",
        "Puerto Rico", "Republic of Palau", "United States", "Veterans Health", "Virgin Islands"} ;

        // Determine the state that has the most number of people vaccinated
        int most_vaccinated_count = 0;
        String most_vaccinated_state = "None" ;
        for (Vaccinations_Data vac : allStates) {
            // as long as the state corresponding with the index isn't one of the exceptions and the date matches today's date
            if (!Arrays.asList(exceptions).contains(vac.getState()) && vac.getDate().equals(today)) {  
                if (vac.getVaccinated() > most_vaccinated_count) {
                    most_vaccinated_count = (int)vac.getVaccinated();
                    most_vaccinated_state = vac.getState();
                }
            }
        }
        System.out.print("Did you know " + most_vaccinated_state + " has the most number of people vaccinated?");
        System.out.println(" With a total number of " + most_vaccinated_count + ".");
        System.out.println("Good job, " + most_vaccinated_state + "!");

        // Determine the state that has the least number of people vaccinated
        int least_vaccinated_count = Integer.MAX_VALUE;
        String least_vaccinated_state = "None" ;
        for (Vaccinations_Data vac : allStates) {
            if (!Arrays.asList(exceptions).contains(vac.getState()) && vac.getDate().equals(today)) {
                if (vac.getVaccinated() < least_vaccinated_count) {
                    least_vaccinated_count = (int)vac.getVaccinated();
                    least_vaccinated_state = vac.getState();
                }
            }
        }
        System.out.print("And " + least_vaccinated_state + " has the least number of people vaccinated.");
        System.out.println(" With a total number of " + least_vaccinated_count + ".");

        System.out.println();

        // Determine the state that has the most number of people vaccinated per 100
        int most_vaccinated_count_per_100 = 0;
        String most_vaccinated_state_per_100 = "None" ;
        for (Vaccinations_Data vac : allStates) {
            if (!Arrays.asList(exceptions).contains(vac.getState()) && vac.getDate().equals(today)) {
                if (vac.getVaccinated_per_100() > most_vaccinated_count_per_100) {
                    most_vaccinated_count_per_100 = (int)vac.getVaccinated_per_100();
                    most_vaccinated_state_per_100 = vac.getState();
                }
            }
        }
        System.out.print("Did you also know " + most_vaccinated_state_per_100 + " has the most number of people vaccinated per 100?");
        System.out.println(" With " + most_vaccinated_count_per_100 + " per 100 people.");
        System.out.println("Well done, " + most_vaccinated_state_per_100 + "!");

        // Determine the state that has the least number of people vaccinated per 100
        int least_vaccinated_count_per_100 = Integer.MAX_VALUE;
        String least_vaccinated_state_per_100 = "None" ;
        for (Vaccinations_Data vac : allStates) {
            if (!Arrays.asList(exceptions).contains(vac.getState()) && vac.getDate().equals(today)) {
                if (vac.getVaccinated_per_100() < least_vaccinated_count_per_100) {
                    least_vaccinated_count_per_100 = (int)vac.getVaccinated_per_100();
                    least_vaccinated_state_per_100 = vac.getState();
                }
            }
        }
        System.out.print("And " + least_vaccinated_state_per_100 + " has the least number of people vaccinated per 100.");
        System.out.println(" With " + least_vaccinated_count_per_100 + " per 100 people.");
    }
}


