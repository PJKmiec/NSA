package pl.gov.nsa;

import pl.gov.nsa.dto.PlaylistsDTO;
import pl.gov.nsa.model.Playable;
import pl.gov.nsa.model.Playlist;
import pl.gov.nsa.model.Song;
import pl.gov.nsa.model.Video;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Witaj w programie PLAYLISTY napisanym dla NSA");
        System.out.println("**************");

        PlaylistsDTO dto = new PlaylistsDTO();
        List<Playlist> playlists = dto.getPlaylists();

        Scanner scanner = new Scanner(System.in);
        int option1 = 0;

        while (option1 < 1 || option1 > 7) {
            System.out.println("Wybierz jedną z opcji:");
            System.out.println("1: Wyświetlenie listy playlist");
            System.out.println("2: Dodanie nowej playlisty");
            System.out.println("3: Dodanie piosenki do playlisty");
            System.out.println("4: Dodanie filmu do playlisty");
            System.out.println("5: Dodanie playlisty do playlisty");
            System.out.println("6: Odtwarzanie playlisty");
            System.out.println("7: Wyjście");
            option1 = scanner.nextInt();

            // wyjście
            if (option1 == 7) {
                return;
            } else {

                // dla większości opcji w przypadku braku istniejących playlist wracamy do menu głównego
                if (option1 != 2 && playlists.isEmpty()) {
                    System.out.println("Nie znaleziono ani jednej playlisty.");
                    option1 = 0;
                }

                // wyświetlenie playlist
                if (option1 == 1) {
                    System.out.println("Znalezione playlisty:");
                    for (Playlist playlist : playlists) {
                        System.out.println(playlist);

                        List<Playable> contents = playlist.getTracklist();

                        if (contents.size() < 1) {
                            System.out.println("- brak utworów");
                        }

                        for (Playable item : contents) {
                            System.out.println("- " + item);

                            if (item instanceof Playlist) {
                                for (Playable subitem : ((Playlist) item).getTracklist()) {
                                    System.out.println("-- " + subitem);
                                }
                            }
                        }
                    }
                } else if (option1 == 2) { // dodanie nowej playlisty
                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt

                    String name = "";
                    while (name.isEmpty()) {
                        System.out.println("Dodawanie nowej playlisty - podaj nazwę:");
                        name = scanner.nextLine();
                    }

                    System.out.println("Odtwarzanie losowe: (t)tak / (n)nie:");
                    boolean shuffle = scanner.nextLine().equals("t");
                    System.out.println("Zapętlenie: (t)tak / (n)nie:");
                    boolean loop = scanner.nextLine().equals("t");
                    List<Playable> tracklist = new ArrayList<>();
                    playlists.add(new Playlist(name, tracklist, shuffle, loop));

                    System.out.println("Dodano nową playlistę: " + name);
                } else if (option1 == 3) { // dodanie piosenki do playlisty
                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt

                    System.out.println("Wybierz playlistę do której chcesz dodać piosenkę:");

                    // tworzymy listę playlist ze skanowaniem o 1 poziom w dół i listujemy ją
                    List<Playlist> allPlaylists = listPlaylists(playlists);

                    int option2 = scanner.nextInt();

                    while (option2 < 1 || option2 > allPlaylists.size()) {
                        System.out.println("Nie ma takiej playlisty. Wpisz właściwy numer.");
                        option2 = scanner.nextInt();
                    }

                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt
                    Playlist playlist = allPlaylists.get(option2 - 1);
                    System.out.println("Wybrałeś playlistę " + playlist.getName());

                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println("Podaj tytuł piosenki:");
                        title = scanner.nextLine();
                    }

                    String artist = "";
                    while (artist.isEmpty()) {
                        System.out.println("Podaj wykonawcę piosenki:");
                        artist = scanner.nextLine();
                    }

                    String ms = "";

                    while (!ms.matches("\\d{1,2}\\:\\d{1,2}")) {
                        System.out.println("Podaj długość piosenki w formacie MM:SS:");
                        ms = scanner.nextLine();
                    }

                    int minutes = Integer.parseInt(ms.split(":")[0]);
                    int seconds = Integer.parseInt(ms.split(":")[1]);

                    while (minutes < 1 && seconds < 1) {
                        System.out.println("Długość piosenki musi wynosić conajmniej 1 sekundę!");
                        ms = scanner.nextLine();
                        minutes = Integer.parseInt(ms.split(":")[0]);
                        seconds = Integer.parseInt(ms.split(":")[1]);
                    }

                    playlist.getTracklist().add(new Song(title, artist, minutes, seconds));
                    System.out.println("Dodano piosenkę " + title + " do playlisty " + playlist.getName() + ".");
                } else if (option1 == 4) { // dodanie filmu do playlisty
                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt

                    System.out.println("Wybierz playlistę do której chcesz dodać film:");

                    // tworzymy listę playlist ze skanowaniem o 1 poziom w dół i listujemy ją
                    List<Playlist> allPlaylists = listPlaylists(playlists);

                    int option2 = scanner.nextInt();

                    while (option2 < 1 || option2 > allPlaylists.size()) {
                        System.out.println("Nie ma takiej playlisty. Wpisz właściwy numer.");
                        option2 = scanner.nextInt();
                    }

                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt
                    Playlist playlist = allPlaylists.get(option2 - 1);
                    System.out.println("Wybrałeś playlistę " + playlist.getName());

                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println("Podaj tytuł filmu:");
                        title = scanner.nextLine();
                    }

                    String hms = "";

                    while (!hms.matches("\\d{1,2}\\:\\d{1,2}\\:\\d{1,2}")) {
                        System.out.println("Podaj długość filmu w formacie HH:MM:SS:");
                        hms = scanner.nextLine();
                    }

                    int hours = Integer.parseInt(hms.split(":")[0]);
                    int minutes = Integer.parseInt(hms.split(":")[1]);
                    int seconds = Integer.parseInt(hms.split(":")[2]);

                    while (hours < 1 && minutes < 1 && seconds < 1) {
                        System.out.println("Długość filmu musi wynosić conajmniej 1 sekundę!");
                        hms = scanner.nextLine();
                        hours = Integer.parseInt(hms.split(":")[0]);
                        minutes = Integer.parseInt(hms.split(":")[1]);
                        seconds = Integer.parseInt(hms.split(":")[2]);
                    }

                    playlist.getTracklist().add(new Video(title, hours, minutes, seconds));
                    System.out.println("Dodano film " + title + " do playlisty " + playlist.getName() + ".");
                } else if (option1 == 5) { // dodanie playlisty do playlisty
                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt

                    System.out.println("Wybierz playlistę do której chcesz dodać playlistę:");

                    // tworzymy listę playlist ze skanowaniem o 1 poziom w dół i listujemy ją
                    List<Playlist> allPlaylists = playlists;

                    for (int i = 0; i < allPlaylists.size(); i++) {
                        System.out.println((i + 1) + ": " + allPlaylists.get(i).getName());
                    }

                    int option2 = scanner.nextInt();

                    while (option2 < 1 || option2 > allPlaylists.size()) {
                        System.out.println("Nie ma takiej playlisty. Wpisz właściwy numer.");
                        option2 = scanner.nextInt();
                    }

                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt
                    Playlist playlist = allPlaylists.get(option2 - 1);
                    System.out.println("Wybrałeś playlistę " + playlist.getName());

                    String name = "";
                    while (name.isEmpty()) {
                        System.out.println("Dodawanie playlisty do playlisty - podaj nazwę:");
                        name = scanner.nextLine();
                    }

                    System.out.println("Odtwarzanie losowe: (t)tak / (n)nie:");
                    boolean shuffle = scanner.nextLine().equals("t");
                    System.out.println("Zapętlenie: (t)tak / (n)nie:");
                    boolean loop = scanner.nextLine().equals("t");
                    List<Playable> tracklist = new ArrayList<>();
                    playlist.getTracklist().add(new Playlist(name, tracklist, shuffle, loop));

                    System.out.println("Dodano nową playlistę: " + name + " do playlisty " + playlist.getName());
                } else if (option1 == 6) { // odtwarzanie playlisty
                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt

                    System.out.println("Wybierz playlistę do odtworzenia:");

                    // tworzymy listę playlist ze skanowaniem o 1 poziom w dół i listujemy ją
                    List<Playlist> allPlaylists = listPlaylists(playlists);

                    int option2 = scanner.nextInt();

                    while (option2 < 1 || option2 > allPlaylists.size()) {
                        System.out.println("Nie ma takiej playlisty. Wpisz właściwy numer.");
                        option2 = scanner.nextInt();
                    }

                    scanner.nextLine(); // konsumujemy znaki nowej linii po nextInt
                    Playlist playlist = allPlaylists.get(option2 - 1);
                    System.out.println("Wybrałeś playlistę " + playlist.getName());

                    if (playlist.getTracklist().size() < 1) {
                        System.out.println("Wybrana playlista jest pusta, wychodzę z programu.");
                        return;
                    }

                    int index = 0, remainder = 0; // iterator nadrzędnej playlisty i licznik pozostałych utworów
                    boolean reverse = false, shuffled = false; // flaga kierunku iteracji i flaga zabezpieczająca subplaylisty przed wielokrotnym tasowaniem
                    String next = "";
                    List<Playable> tracklist = playlist.getTracklist();

                    if (playlist.isShuffle()) {
                        Collections.shuffle(tracklist);
                    }

                    do {
                        if (tracklist.get(index) instanceof Playlist) {
                            List<Playable> subtracklist = ((Playlist) tracklist.get(index)).getTracklist();

                            if (((Playlist) tracklist.get(index)).isShuffle() && !shuffled) {
                                Collections.shuffle(subtracklist);
                                shuffled = true;
                            }

                            int subindex = 0;
                            if (reverse) {
                                subindex = subtracklist.size() - 1;
                            }

                            do {
                                remainder = playlist.play(subtracklist, subindex);

                                // jeśli cofniemy na koniec sublisty, zabezpieczamy się przed wyjściem z pętli
                                if (remainder == 0 && reverse) {
                                    remainder++;
                                }

                                System.out.println("Kontroluj odtwarzanie klawiszami [w]wstecz / [d]dalej:");
                                next = scanner.nextLine();

                                if (next.equals("w")) {
                                    reverse = true;
                                    subindex--;

                                    if (subindex < 0) {
                                        remainder = 0;
                                        index -= 2;
                                    }
                                } else if (next.equals("d")) {
                                    reverse = false;
                                    subindex++;
                                }

                            } while (remainder > 0);
                            index++;
                        }
                        remainder = playlist.play(tracklist, index);

                        System.out.println("Kontroluj odtwarzanie klawiszami [w]wstecz / [d]dalej:");
                        next = scanner.nextLine();

                        if (next.equals("w")) {
                            reverse = true;
                            index--;
                            remainder++;
                            if (index < 0) {
                                if (playlist.isLoop()) {
                                    System.out.println("Osiągnięto początek playlisty, przechodzę do końca ponieważ jest zapętlona.");
                                    index = tracklist.size() - 1;
                                } else {
                                    System.out.println("Osiągnięto początek playlisty, wychodzę do głównego menu ponieważ nie jest zapętlona.");
                                    remainder = -1; // dla zabezpieczenia przed dodaniem komunikatu o osiągnięciu końca listy
                                }
                            }
                        } else if (next.equals("d")) {
                            reverse = false;
                            index++;
                        }

                        if (remainder == 0 && playlist.isLoop()) {
                            System.out.println("Koniec playlisty, przechodzę do początku ponieważ jest zapętlona.");
                            index = 0;
                            remainder = tracklist.size() - 1;
                        }
                    } while (remainder > 0);

                    if (remainder >= 0) {
                        System.out.println("Koniec playlisty, wychodzę do głównego menu ponieważ nie jest zapętlona.");
                    }
                }

                System.out.println("---------");
                option1 = 0; // wyjście do początkowego wyboru opcji
            }
        }
    }

    // wyświetla listę wszystkich playlist (w tym skanuje playlisty z tracklist) i ją zwraca
    public static List<Playlist> listPlaylists(List<Playlist> playlists) {
        List<Playlist> allPlaylists = new ArrayList<>();

        for (int i = 0; i < playlists.size(); i++) {
            allPlaylists.add(playlists.get(i));

            for (int j = 0; j < playlists.get(i).getTracklist().size(); j++) {
                if (playlists.get(i).getTracklist().get(j) instanceof Playlist) {
                    Playlist p = (Playlist) playlists.get(i).getTracklist().get(j);
                    allPlaylists.add(p);
                }
            }
        }

        for (int k = 0; k < allPlaylists.size(); k++) {
            System.out.println((k + 1) + ": " + allPlaylists.get(k).getName());
        }

        return allPlaylists;
    }
}
