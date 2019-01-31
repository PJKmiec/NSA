package pl.gov.nsa.model;

public class Video extends Playable {
    private String title;
    private int hours;
    private int minutes;
    private int seconds;

    public Video(){}

    public Video(String title, int hours, int minutes, int seconds) {
        this.title = title;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Film " + title + " (" + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + ")";
    }
}
