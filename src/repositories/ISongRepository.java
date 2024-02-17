package repositories;

import entities.Song;

import java.util.List;
import java.util.Optional;

public interface ISongRepository {
    Song save(Song song);
    List<Song> findAll();
    Optional<Song> findById(Long id);
    Optional<Song> findByName(String songName);
}

