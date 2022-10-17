import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Stack;

public class Playlists {
    // Playlist playPath = new Playlist();
    static Queue<String> songQueue = new LinkedList<>();
    static Stack<String> TempStorage = new Stack<>();
    static Scanner in = new Scanner(System.in);

    void displayList() {
        PlaylistHandler.playlistNames.printList();
    }

    public static String next_line(String ask) {
        String get = "";
        System.out.print(ask);
        get = in.nextLine();
        if (get.equals("")) {
            return null;
        }
        return get;
    }

    void writeToFile(String Playlist, String songName) {
        try {
            FileWriter fw = new FileWriter("Files/Playlist/" + Playlist + ".txt", true);
            System.out.println(songName);
            fw.write(songName + "\n");
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    static ArrayList<String> Play_list = new ArrayList<>();

    void makePlaylist()
            throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.print("Enter the Name of the Playlist: ");
        Scanner in = new Scanner(System.in);
        String Pname = in.nextLine();
        createPlaylist(Pname);
        System.out.println();
    }

    void createPlaylist(String name)
            throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        if (!PlaylistHandler.playlistNames.search(name)) {
            try {
                FileWriter fw = new FileWriter("Files/Playlists.txt", true);
                fw.write(name + "\n");
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
            PlaylistHandler.whenAdded(name);
            String ch = next_line("Do you want to add songs to the Playlist now?(y/n): ");
            if (ch.equals("y") || ch.equals("Y")) {
                addSongToPlaylist(name);
            }
        } else {
            System.out.println("Playlist Already exists!");
            StartInterface.First();
        }
    }

    void addSongToPlaylist(String Playlist) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        char ch;
        do {

            String songName = next_line("Enter the Name of the song you want to Add: ");
            SongHandler sh1 = new SongHandler();
            sh1.initiatePlaylist(Playlist);
            if (SongHandler.PlaylistContents.contains(songName)) {
                System.out.println("Song already exists in this Playlist!");
            } else {
                if (SongHandler.SongCollection.search(songName + ".wav")) {
                    writeToFile(Playlist, songName);
                } else {
                    System.out.println("Song doesn't Exist!");
                }
            }
            System.out.println("Do you wish to continue adding songs?(y/n): ");
            ch = sc.next().charAt(0);

        } while (ch == 'y' || ch == 'Y');
    }

    void startPlaylist(String Playlist) throws IOException {
        SongHandler sHand = new SongHandler();
        sHand.initiatePlaylist(Playlist);
        songQueue.addAll(SongHandler.PlaylistContents);
    }

    void deleteSongFromPlaylist(String Playlist) throws IOException {
        SongHandler sand = new SongHandler();
        sand.initiatePlaylist(Playlist);
        String name = next_line("Enter the name of the song you want to delete: ");
        String formatName = "Songs/" + name + ".wav";
        if (SongHandler.PlaylistContents.contains(formatName)) {
            SongHandler.PlaylistContents.remove(formatName);
            TempStorage.add(formatName + " " + Playlist);
            try {
                FileWriter fw = new FileWriter("Files/Playlist/" + Playlist + ".txt");
                for (String nameSong : SongHandler.PlaylistContents) {
                    fw.write(nameSong.substring(6, nameSong.indexOf(".")) + "\n");
                }
                fw.close();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
            System.out.println("Song Deleted!");
        } else {
            System.out.println("You can't delete a song that doesn't exist in the Playlist! ");
        }
    }

    void undoDeletePlaylist() {
        if (TempStorage.empty()) {
            System.out.println("Cannot Perform Operation: Occurrence of Deletion in session not observed");
        } else {
            String backUp = TempStorage.peek();
            String[] nest = backUp.split(" ");
            TempStorage.pop();
            SongHandler.PlaylistContents.add(nest[0]);
            try {
                FileWriter fw = new FileWriter("Files/Playlist/" + nest[1] + ".txt");
                for (String nameSong : SongHandler.PlaylistContents) {
                    fw.write(nameSong.substring(6, nameSong.indexOf(".")) + "\n");
                }
                fw.close();
                System.out.println("Restored Song --> " + backUp);
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

}
