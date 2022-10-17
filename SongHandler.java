import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class SongHandler {
    static Collections SongCollection = new Collections();
    static ArrayList<String> PlaylistContents = new ArrayList<>();
    static String songPath = "Songs/";
    public static void initiateSongs(File[] files)
        {
            for (File filename : files) {
                SongCollection.add(filename.getName());
            }
        }
     void initiatePlaylist(String Playlist) throws IOException {
        PlaylistContents.clear();
        File readf = new File("Files/Playlist/"+Playlist+".txt");
        if(!readf.exists()){
            readf.createNewFile();
        }
        Scanner sc=new Scanner(readf);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            line=line.trim();
            PlaylistContents.add("Songs/"+line+".wav");
        }
    }
    static void DisplaySongs(){SongCollection.printList();}
    void Play() throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        while(!Playlists.songQueue.isEmpty()){
            Player.PlaySongs();
            TimeUnit.SECONDS.sleep(1);
        }
        StartInterface.First();
    }
}
