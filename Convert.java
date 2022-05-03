package piano_player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Convert {
    Queue<Notes> hpbd = new ArrayDeque<>();

    public static Queue<Notes> scanIn( Queue<Notes>temp, String link) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(link);
        Scanner sc = new Scanner(fileInputStream);
        // scan and create the queue
        while (sc.hasNextInt()) {
            int note = sc.nextInt();
            long timeP = sc.nextLong();
            long timeR = sc.nextLong();
            Notes play = new Notes(note, timeP, timeR);
            temp.add(play);
        }
        return temp;

    }
    
    public static void replay(Queue<Notes> in) {
        while (!in.isEmpty()) {
            Notes play = in.remove();
            // Play note in right tone
            PianoPlayer.channel.noteOn(play.getNote(), PianoPlayer.velocity);
            try {
                Thread.sleep(play.getTimeRelease() - play.getTimePress());
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            PianoPlayer.channel.noteOff(play.getNote(), PianoPlayer.velocity);
            // Play notes in right time gap
            if (in.peek() != null) {
                long start = in.peek().getTimePress();
                // time gap = next one - this one
                try {
                    Thread.sleep(start - play.getTimeRelease());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public Convert() {
        try {
            Queue<Notes> hpbd = new ArrayDeque<>();
            this.hpbd = scanIn(hpbd, "src/piano_player/hpbd.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws IOException {
    }
}
