package com.revision.ctci.gobjectorienteddesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CJukeBox {
    public static void main(String[] args) {
        Player player = new Player();
        player.start();
    }
}

class Player extends Thread {
    Account account;
    List<Playlist> playlists;
    boolean exit = false;

    Player() {
        this.account = null;
        this.playlists = new ArrayList<>();
    }

    @Override
    public void run() {
        System.out.println("Player Started: ");
        while (!exit) {
            /* Check if the user is logged in otherwise show the login screen */
            /* Display the playlists available to the user */
            /* Once they selected load it to a queue and then start playing the songs
             * If they change the Playlist setting modify and play accordingly otherwise
             * use default settings */
            int selection = 0;
            if (playlists.isEmpty() || !play(playlists.get(selection))) {
                System.out.println("Could not play... try again");
            }
            /* If they chose to exit then close the application */
            try {
                TimeUnit.SECONDS.sleep(3);
                interrupt();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
                exit();
            }
        }
    }

    public void exit() {
        System.out.println("Exiting... ");
        this.exit = true;
    }

    public boolean login(String username) {
        this.account = fetchAccount(username);
        if (this.account == null) return false;
        return true;
    }

    private Account fetchAccount(String username) {
        return Account.fetchAccount(username);
    }

    public boolean play(Playlist playlist) {
        return false;
    }

    /* Additional Functionality */
    public void pausePlayer() {

    }

    public void resumePlayer() {

    }
}

class Account {
    int id;
    String name;
    Equalizer equalizer;

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
        this.equalizer = getOrInitiate();
    }

    //Logic to get the existing equalizer mapped to the account or initiate a new creation
    private Equalizer getOrInitiate() {
        return new Equalizer();
    }

    static Account fetchAccount(String username) {
        return null;
    }
}

class Equalizer {
    Map<String, Decibel> hertz;

    enum Type {
        SIXTY_HZ(60), TWO_HUNDRED_THIRTY_HZ(230), NINE_HUNDERED_TEN_HZ(910), THREE_POINT_SIX_KILO_HZ(3600), FOURTEEN_KILO_HZ(14000);
        private int value;

        Type(int value) {
            this.value = value;
        }
    }

    public Equalizer() {
        this.hertz = initializeEqualizer();
    }

    private Map<String, Decibel> initializeEqualizer() {
        Map<String, Decibel> equalizer = new HashMap<>();
        for (Type type : Type.values()) {
            equalizer.put(type.name(), new Decibel());//Assign Default Decibel Level
        }
        return equalizer;
    }

    private boolean modifyEqualizer(Decibel[] levels) {
        return false;
    }
}

class Decibel {//Range is +10 db to -10 db
    private int level;

    Decibel() {
        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

class Playlist {
    List<Song> songs;
    Setting setting;

    public Playlist(List<Song> songs) {
        this.songs = songs;
        this.setting = new Setting();//Default setting
    }

    public boolean addSong(Song song) {
        return false;
    }

    public boolean addSongs(List<Song> songs) {
        return false;
    }

    /* Based on the user input from front end we can ensure we are changing the corresponding settings */
    public boolean modifySetting(boolean[] settings) {
        return false;
    }
}

class Song {
    /* Absolutely Necessary */
    public long id;
    public String name;
    public int duration;//In Milli Seconds
    /* Optimization in future filtering, sorting features */
    public List<Artist> artists;
    public Language language;
    public Image image;
    public Lyric lyrics;
    public Genre genre;
}

class Setting {
    private boolean shuffle;
    private boolean loop;

    Setting() {
        this.shuffle = false;
        this.loop = false;
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
}

/* The below classes ARTIST | ROLE | LANGUAGE | IMAGE | LYRIC | GENRE are all primitive stages as of now
 * and can be altered as and when required. for example we can filter songs by Artist, Language, Genre
 * We need some mechanism of indexing them like maintaining in the form of hash tables */
class Artist {
    public int id;
    public String name;
    public Role role;
}

class Role {
    public int id;
    public String name;
}

class Language {
    public int id;
    public String name;
}

class Image {
    public long id;
    public String name;
    /* The place for holding the cover picture of the song */
}

class Lyric {
    public String details;
    public HashMap<Integer, String> lines;
}

class Genre {
    public int id;
    public String name;
}