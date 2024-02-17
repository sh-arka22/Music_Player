package repositories;

import entities.Playlist;

import java.util.*;

public class PlaylistRepository implements IPlaylistRepository{
    private final Map<String, Playlist> playlistMap;
    private Long autoIncreament = 1L;
    public PlaylistRepository(){
        playlistMap = new TreeMap<>();
    }
    @Override
    public Playlist save(Playlist playlist) {
        Playlist savedPlaylist = new Playlist(autoIncreament, playlist.getPlaylistName(), new LinkedHashSet<>(playlist.getSongList()));
        playlistMap.put(playlist.getPlaylistName(), savedPlaylist);
        autoIncreament++;
        return savedPlaylist;
    }

    @Override
    public Playlist delete(String playlist) {
        // TODO Auto-generated method stub
        Playlist delPlaylist = playlistMap.get(playlist);
        if(delPlaylist == null) throw new RuntimeException("playlist doesnot exists");
        playlistMap.remove(playlist);
        return delPlaylist;
    }

    @Override
    public Optional<Playlist> findByName(String playlistName) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(playlistMap.get(playlistName));
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        // TODO Auto-generated method stub
        List<Playlist> playlists = new ArrayList<>(playlistMap.values());
        for(Playlist playlist: playlists){
            if(playlist.getId() == id){
                return Optional.ofNullable(playlist);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Long> getSongIds(String playlistName){
        return playlistMap.get(playlistName).getSongList();
    }
}

