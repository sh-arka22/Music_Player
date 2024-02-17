import constants.Status;
import entities.Genre;
import entities.Playlist;
import entities.Song;
import repositories.IPlaylistRepository;
import repositories.ISongRepository;
import repositories.PlaylistRepository;
import repositories.SongRepository;
import services.PlayListService;
import services.PlayService;
import services.SongService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        if (args.length != 1){
//            throw new RuntimeException();
//        }
//        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
         String filePath = "./src/sample_input/input_1.txt";
         List<String> commandLineArgs = new LinkedList<>();
         commandLineArgs.add("filePath=" + filePath);
        run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs){
        //initialisong repositories
        IPlaylistRepository playlistRepository = new PlaylistRepository();
        ISongRepository songRepository = new SongRepository();

        //initialising services
        PlayService playService = new PlayService(playlistRepository, songRepository);
        SongService songService = new SongService(songRepository);
        PlayListService playListService = new PlayListService(playlistRepository, songRepository);

        String inputFile = commandLineArgs.get(0).split("=")[1];

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            while (true) {
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                List<String> tokens = Arrays.asList(line.split(" "));
                switch(tokens.get(0)){
                    case "ADD_SONG" :
                    {
                        String songName = tokens.get(1);
                        String artist = tokens.get(2);
                        String album = tokens.get(3);
                        Genre genre = new Genre(tokens.get(4));
                        Song song = songService.addSong(songName, artist, album, genre);
                        System.out.println(song);
                    }
                    break;

                    case "CREATE_PLAYLIST" :
                    {
                        String playlistName = tokens.get(1);
                        List<Long>songIds = new ArrayList<>();
                        for(int i=2; i<tokens.size(); i++){
                            songIds.add(Long.parseLong(tokens.get(i)));
                        }
                        Playlist playlist = playListService.createPlaylist(playlistName, songIds);
                        System.out.println(playlist);
                    }
                    break;

                    case "ADD_SONG_TO_PLAYLIST" :
                    {
                        String playlistName = tokens.get(1);
                        Long songId = Long.parseLong(tokens.get(2));
                        List<Song> songs = playListService.addSongPlaylist(playlistName, songId);
                        System.out.println("Playlist " + playlistName + " is revised with " + songs);
                    }
                    break;

                    case "DELETE_SONG_FROM_PLAYLIST" :
                    {
                        String playlistName = tokens.get(1);
                        Long songId = Long.parseLong(tokens.get(2));
                        List<Song> songs = playListService.deleteSongPlaylist(playlistName, songId);
                        System.out.println("Playlist " + playlistName + " is revised with " + songs);
                    }
                    break;

                    case "DELETE_PLAYLIST" :
                    {
                        String playlistName = tokens.get(1);
                        Playlist deleted = playListService.deletePlaylist(playlistName);
                        System.out.println("Playlist " + deleted.getPlaylistName() + " is deleted!");
                    }
                    break;

                    case "LOAD_PLAYLIST" :
                    {
                        String playlistName = tokens.get(1);
                        Playlist playlist = playService.loadPlaylist(playlistName);
                        System.out.println("Playlist " + playlistName + " is loaded!");
                    }
                    break;

                    case "PLAY_SONG" :
                    {
                        Song song = playService.playSong();
                        if(song.getStatus().equals(Status.PLAY))
                            System.out.println(song + " is playing!");
                        else if(song.getStatus().equals(Status.PAUSE)){
                            System.out.println(song + " is paused!");
                        }
                    }
                    break;

                    case "NEXT_SONG" :
                    {
                        Song song = playService.nextSong();
                        System.out.println(song + " is playing!");
                    }
                    break;

                    case "PREVIOUS_SONG" :
                    {
                        Song song = playService.previousSong();
                        System.out.println(song + " is playing!");
                    }
                    break;

                    case "STOP_SONG" :
                    {
                        Song song = playService.stopPlaying();
                        System.out.println(song + " is stopped!");
                    }
                    break;

                    case "LIST_SONGS" :
                    {
                        List<Song> songs = songService.getAllSongs();
                        System.out.println(songs);
                    }
                    break;

                    default:
                        throw new RuntimeException("Invalid Command");
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}