package entities;

import constants.Status;

public class Song {
    private final Long id;
    private final String songName;
    private final String artist;
    private final Genre genre;
    private Status status;

    public Song(Long id, String songName, String artist, Genre genre) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.status = status.PAUSE;
    }

    public Song(String songName, String artist, Genre genre) {
        this.songName = songName;
        this.artist = artist;
        this.genre = genre;
        this.status = status.PAUSE;
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    public Genre getGenre() {
        return genre;
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    @Override
    public String toString() {
        return "Song [id=" + id + "]";
    }
}
