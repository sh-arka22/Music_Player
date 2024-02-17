package services;

import entities.Genre;
import entities.Song;
import repositories.ISongRepository;

import java.util.List;

public class SongService {
    private final ISongRepository songRepository;

    public SongService(ISongRepository songRepository){
        this.songRepository = songRepository;
    }

    public Song addSong(String songName, String artist, String album, Genre genre){
        //creating the new song
        Song newSong = new Song(songName, artist, genre);
        //savong the song on the repository
        Song savedSong = songRepository.save(newSong);

        return savedSong;
    }
    public List<Song> getAllSongs(){
        return songRepository.findAll();
    }
}

