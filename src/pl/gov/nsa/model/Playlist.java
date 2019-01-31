package pl.gov.nsa.model;

import java.util.Collections;
import java.util.List;

public class Playlist extends Playable {
    private String name;
    private List<Playable> tracklist;
    private boolean shuffle;
    private boolean loop;

    public int play(List<Playable> tracklist, int index){
        System.out.println("Teraz odtwarzam: " + tracklist.get(index));
        return (tracklist.size() - index - 1);
    }

    public Playlist(){}

    public Playlist(String name, List<Playable> tracklist, boolean shuffle, boolean loop) {
        this.name = name;
        this.tracklist = tracklist;
        this.shuffle = shuffle;
        this.loop = loop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Playable> getTracklist() {
        return tracklist;
    }

    public void setTracklist(List<Playable> tracklist) {
        this.tracklist = tracklist;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public String toString() {
        String s = isShuffle() ? "losowa" : "normalna";
        String l = isLoop() ? "zapętlona" : "bez pętli";
        return "Playlista " + name + " [" + s + " / " + l + "]:";
    }
}
