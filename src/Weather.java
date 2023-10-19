public class Weather {
    public int date;
    public String rain;
    public String clouds;

    public Weather(int date, String rain, String clouds) {
        this.date = date;
        this.rain = rain;
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return "Date: " + this.date + ". Rain: " + this.rain + ". Sky: " + this.clouds + ".";
    }
}
