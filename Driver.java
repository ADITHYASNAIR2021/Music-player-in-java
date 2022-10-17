import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        PlaylistHandler pr = new PlaylistHandler();
        File[] readNow = new File("Songs/").listFiles();
        assert readNow != null;
        SongHandler.initiateSongs(readNow);
        StartInterface.First();
    }
}

