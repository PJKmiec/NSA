package pl.gov.nsa.dto;

import pl.gov.nsa.model.Playable;
import pl.gov.nsa.model.Playlist;
import pl.gov.nsa.model.Song;
import pl.gov.nsa.model.Video;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {

    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();

        // główna playlista
        List<Playable> tracklist1 = new ArrayList<>();
        tracklist1.add(new Song("Brown Sugar", "Rolling Stones", 3, 50));
        playlists.add(new Playlist("Główna playlista", tracklist1, false, true));

        // subplaylista
        List<Playable> tracklist2 = new ArrayList<>();
        tracklist2.add(new Song("Supper's Ready", "Genesis", 23, 5));
        tracklist2.add(new Song("Echoes", "Pink Floyd", 10, 20));
        tracklist2.add(new Song("2112", "Rush", 20, 39));
        tracklist2.add(new Song("Close To The Edge", "Yes", 18, 42));
        tracklist2.add(new Song("Shine On You Crazy Diamond (Parts 6-9)", "Pink Floyd", 12, 26));
        tracklist2.add(new Song("Octavarium", "Dream Theater", 24, 0));
        tracklist2.add(new Song("Starless", "King Crimson", 12, 30));
        tracklist2.add(new Song("Roundabout", "Yes", 8, 29));
        tracklist2.add(new Song("Thick as a Brick (Part 1)", "Jethro Tull", 22, 40));
        tracklist2.add(new Song("Tarkus", "Emerson, Lake & Palmer", 20, 39));
        playlists.get(0).getTracklist().add(new Playlist("Progressive rock TOP 10", tracklist2, true, false));

        // film
        playlists.get(0).getTracklist().add(new Video("Casablanca", 1, 42, 0));

        return playlists;
    }

}
