package services;


import constants.Status;
import entities.Playlist;
import entities.Song;
import repositories.IPlaylistRepository;
import repositories.ISongRepository;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class PlayService {;
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    private Deque<Song> currentSongList;
    private Playlist currentPlaylist;

    public PlayService(IPlaylistRepository playlistRepository, ISongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public Playlist loadPlaylist(String playlistName){
        Playlist playlist = null;
        playlist = playlistRepository.findByName(playlistName).orElseThrow();
        List<Long> songIds = playlistRepository.getSongIds(playlistName);

        Deque<Song> songs = new LinkedList<>();

        for(Long songId: songIds){
            Song song = songRepository.findById(songId).orElseThrow();
            songs.addLast(song);
        }
        // this.currentPlaylist = playlistName;
        this.currentSongList = songs;
        return playlist;
    }

    public Song playSong(){
        Song currSong = currentSongList.getFirst();
        if(currSong.getStatus().equals(Status.PLAY)){
            currSong.setStatus(Status.PAUSE);
        }
        else currSong.setStatus(Status.PLAY);
        return currSong;
    }

    public Song nextSong(){
        Song lastSong = currentSongList.getFirst();
        currentSongList.removeFirst();
        lastSong.setStatus(Status.PAUSE);
        currentSongList.addLast(lastSong);

        Song currSong = currentSongList.getFirst();
        currSong.setStatus(Status.PLAY);
        return currSong;
    }

    public Song previousSong(){
        Song currSong = currentSongList.getFirst();
        currSong.setStatus(Status.PAUSE);

        Song prevSong = currentSongList.getLast();
        currentSongList.removeLast();
        prevSong.setStatus(Status.PLAY);

        currentSongList.addFirst(prevSong);

        return prevSong;
    }

    public Song stopPlaying(){
        Song currSong = currentSongList.getFirst();
        currSong.setStatus(Status.STOPPED);
        return currSong;
    }
}
