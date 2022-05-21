package sample;

import java.util.ArrayList;

public class PlayList {
    private String name;
    private ArrayList<Song> songs;

    public PlayList(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName () {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public boolean removeSong(Song song) {
        return this.songs.remove(song);
    }

    public void play() {
        System.out.printf("-----Playing %s-----\n", this.name);

        for (Song song : songs) {
            song.play();
        }

        System.out.printf("-----Done %s-----\n", this.name);
    }
}
