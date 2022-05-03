package piano_player;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.UIManager;

public interface Key {
    // change WD to suit your screen
    int WD = 22;
    int HT = (WD * 9) / 2;
    // change baseNote for starting octave
    int baseNote = 36;// baseNote mod 12 = 0

    int getNote();
}

class BlackKey extends JButton implements Key {

    final int note;

    public BlackKey(int pos) {
        note = baseNote + 1 + 2 * pos + (pos + 3) / 5 + pos / 5;
        int left = 15 + WD + ((WD * 3) / 2) * (pos + (pos / 5) + ((pos + 3) / 5));
        setBackground(Color.black);
        setBounds(left, 15, WD, HT);
    }

    public int getNote() {
        return note;
    }
}

class WhiteKey extends JButton implements Key {

    static int WWD = (WD * 3) / 2;
    static int WHT = (HT * 3) / 2;
    final int note;

    public WhiteKey(int pos) {
        note = baseNote + 2 * pos - (pos + 4) / 7 - pos / 7;
        int left = 15 + WWD * pos;
        setBounds(left, 15, WWD, WHT);
        setBackground(UIManager.getColor("ColorChooser.swatchesDefaultRecentColor"));
    }

    public int getNote() {
        return note;
    }
    // https://stackoverflow.com/questions/64577826/is-there-a-good-way-to-make-piano-graphics-in-java
}