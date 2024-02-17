package repositories;

import entities.Playlist;

import java.util.List;
import java.util.Optional;

public interface IPlaylistRepository {
    Playlist save(Playlist playlist);
    Playlist delete(String playlist);
    List<Long> getSongIds(String name);
    Optional<Playlist> findByName(String playlistName);
    Optional<Playlist> findById(Long id);
}
