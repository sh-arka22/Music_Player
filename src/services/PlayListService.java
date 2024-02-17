package services;

import entities.Playlist;
import entities.Song;
import repositories.IPlaylistRepository;
import repositories.ISongRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class PlayListService {
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    public PlayListService(IPlaylistRepository playlistRepository, ISongRepository songRepository){
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    public Playlist createPlaylist(String playListName, List<Long> songIds){

        Playlist newPlaylist = new Playlist(playListName, new LinkedHashSet<>(songIds));
        Playlist savedPlayList = playlistRepository.save(newPlaylist);
        return savedPlayList;
    }

    public List<Long> getAllSongs(String playListName){
        Playlist playlist = playlistRepository.findByName(playListName).orElseThrow(()->new RuntimeException(playListName + "not found"));
        List<Long> songIds = playlist.getSongList();
        return songIds;
    }

    public List<Song> addSongPlaylist(String playListName, Long songId){
        Playlist playlist = playlistRepository.findByName(playListName).orElseThrow(()->new RuntimeException(playListName + "not found"));
        playlist.addSong(songId);
        List<Song> songList = new ArrayList<>();
        for(Long id: playlist.getSongList()){
            Song song = songRepository.findById(id).orElseThrow(()->new RuntimeException("song with id: " + id + " not found"));
            songList.add(song);
        }
        return songList;
    }

    public List<Song> deleteSongPlaylist(String playListName, Long songId){
        Playlist playlist = playlistRepository.findByName(playListName).orElseThrow(()->new RuntimeException(playListName + "not found"));
        boolean deleted = playlist.deleteSong(songId);
        if(deleted == false)
            throw new RuntimeException("Song with id: " + songId + " is not prasent in the plalist");
        List<Song> songList = new ArrayList<>();
        for(Long id: playlist.getSongList()){
            Song song = songRepository.findById(id).orElseThrow(()->new RuntimeException("song with id: " + id + " not found"));
            songList.add(song);
        }
        return songList;
    }

    public Playlist deletePlaylist(String playlistName){
        return playlistRepository.delete(playlistName);
    }
}

