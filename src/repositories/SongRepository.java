package repositories;

import entities.Song;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SongRepository implements ISongRepository{
    private final Map<Long, Song> songMap;
    private Long autoIncrement = 1L;
    public SongRepository(){
        songMap = new TreeMap<>();
    }
    @Override
    public Song save(Song song) {
        // TODO Auto-generated method stub
        Song savedSong = new Song(autoIncrement, song.getSongName(), song.getArtist(), song.getGenre());
        songMap.put(autoIncrement, savedSong);
        autoIncrement++;
        return savedSong;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public Optional<Song> findByName(String songName) {
        // TODO Auto-generated method stub
        return songMap.values().stream().filter(s -> s.getSongName().equals(songName)).findFirst();
    }

}

