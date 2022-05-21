package sample;

import java.util.ArrayList;

public class PocuTunes {
    private ArrayList<Song> songs;
    private ArrayList<PlayList> playLists;

    public PocuTunes() {
        this(new ArrayList<Song>(), new ArrayList<PlayList>());
    }

    public PocuTunes(ArrayList<Song> songs, ArrayList<PlayList> playLists) {
        this.songs = songs;
        this.playLists = playLists;
    }

    public int getSongsCount() {
        return this.songs.size();
    }

    public void addSongs(Song song) {
        this.songs.add(song);
    }

    public boolean removeSong(Song songName) {
        for (PlayList playlist : this.playLists) {
            playlist.removeSong(songName);
        }

        return this.songs.remove(songName);
    }

    public void addPlayList(PlayList playList) {
        this.playLists.add(playList);
    }

    public boolean removePlayList(PlayList playList) {
        return this.playLists.remove(playList);
    }
}
