package pl.gov.nsa.model;

public class Song extends Playable {
    private String title;
    private String artist;
    private int minutes;
    private int seconds;

    public Song(){}

    public Song(String title, String artist, int minutes, int seconds) {
        this.title = title;
        this.artist = artist;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
        return "Piosenka " + title + " - " + artist + " (" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + ")";
    }
}
