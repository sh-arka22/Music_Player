package entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Playlist {
    private final Long id;
    private final String playlistName;
    private Set<Long> songList = new LinkedHashSet<>();


    public Playlist(Long id, String playlistName, Set<Long> songList) {
        this.id = id;
        this.playlistName = playlistName;
        this.songList = songList;
    }
    public Playlist(String playlistName, Set<Long> songList) {
        this.id = null;
        this.playlistName = playlistName;
        this.songList = songList;
    }
    public Playlist(Long id, String playlistName) {
        this.id = id;
        this.playlistName = playlistName;
    }

    public Long getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Long> getSongList() {
        return new ArrayList<>(songList);
    }

    public void setSongList(Set<Long> songList) {
        this.songList = songList;
    }
    public void addSong(Long songId){
        this.songList.add(songId);
    }
    public boolean deleteSong(Long songId){
        if(songList.contains(songId)){
            songList.remove(songId);
            return true;
        }
        else return false;
    }
    @Override
    public String toString() {
        return "Playlist [id=" + id + "]";
    }
}

