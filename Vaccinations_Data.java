
public class Vaccinations_Data {
    private String date;
    private String location;
    private double vaccinations;
    private double vaccinations_per_100;


    Vaccinations_Data(String date, String location, double vaccinations, double vaccinations_per_100) {
        this.date = date;
        this.location = location;
        this.vaccinations = vaccinations;
        this.vaccinations_per_100 = vaccinations_per_100;
    }

    /* Produce the date recorded */
    public String getDate() {
        return date;
    }

    /* Produce the name of the state */
    public String getState() {
        return location;
    }

    /* Produce the number of vaccinations */
    public double getVaccinated() {
        return vaccinations;
    }

    /* Produce the number of vaccinations */
    public double getVaccinated_per_100() {
        return vaccinations_per_100;
    }
}