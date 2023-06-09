package test;

public class Song {
    private String artist;
    private String name;
    private int playTimeInMilliSeconds;

    public Song(String artist, String name, int playTimeInMilliSeconds) {
        this.artist = artist;
        this.name = name;
        this.playTimeInMilliSeconds = playTimeInMilliSeconds;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayTimeInMilliSeconds() {
        return this.playTimeInMilliSeconds;
    }

    public void play() {
        System.out.println(String.format("Playing %s", this.name));
    }
}
