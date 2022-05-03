package piano_player;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import piano_player.pq_heap.MaxHeapPriorityQueue;
import piano_player.pq_heap.MinHeapPriorityQueue;

// https://stackoverflow.com/questions/64577826/is-there-a-good-way-to-make-piano-graphics-in-java

public class PianoPlayer extends JFrame implements MouseListener {

    /*
     * 
     */
    private static final long serialVersionUID = 1L;

    final int OCTAVES = 5; // change numbers of key

    // List of piano keyboards
    private ArrayList<WhiteKey> whites = new ArrayList<>();
    private ArrayList<BlackKey> blacks = new ArrayList<>();

    // Buttons for play music
    static JButton btnReplay = new JButton("REPLAY");
    static JButton btnRewind = new JButton("REWIND");
    static JButton btnMin = new JButton("TINK");
    static JButton btnMax = new JButton("TONK");
    static JButton btnClear = new JButton("CLEAR");

    // Buttons for change instrument
    static JButton record = new JButton("⏺");
    static JButton play = new JButton("◀️");
    static JButton cakeBt = new JButton("🎂");
    static JButton themeBt = new JButton("🎨");
    static JButton instrBt = new JButton("❓");
    static JButton guitarBt = new JButton("🎸");
    static JButton pianoBt = new JButton("🎹");
    static JButton violinBt = new JButton("🎻");
    static JButton bellBt = new JButton("🔔");
    static JButton trumpetBt = new JButton("🎺");
    
    // Panels
    static JPanel contentPane = new JPanel();
    static JPanel piano = new JPanel();
    static JPanel buttons = new JPanel();
    static JPanel instruments = new JPanel();
    static JPanel recording = new JPanel();

    // Color Theme
    static Color mainBackground = new Color(255, 220, 230);
    static Color buttonsColor = new Color(255, 175, 190);
    static Color clearColor = new Color(250, 120, 145);

    // Decoration
    JLabel jtpDecor = new JLabel();
    JLabel jtpDecor1 = new JLabel();
    JLabel label = new JLabel("Choose the instrument >>");
    JLabel label1 = new JLabel("Saved Record >>");

    static MidiChannel channel;
    Queue<Notes> replay = new ArrayDeque<>();
    Stack<Notes> rewind = new Stack<>();
    MinHeapPriorityQueue<Integer, Notes> tink = new MinHeapPriorityQueue<>();
    MaxHeapPriorityQueue<Integer, Notes> tonk = new MaxHeapPriorityQueue<>();
    static int velocity = 127;
    Notes newest = new Notes();
    Queue<Notes> recordFile = new ArrayDeque<>();

    public PianoPlayer() {

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            synth.loadAllInstruments(synth.getDefaultSoundbank());
            MidiChannel channels[] = synth.getChannels();
            // set channel of midi
            channel = channels[0];
            channel.setChannelPressure(0);
            // set the instrument program
            channel.programChange(0);// 88, 98, 46, 104 (change the number to change the instrument)
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < piano.getComponentCount(); i++) {
            if (e.getComponent().equals(piano.getComponent(i))) {
                long start = System.currentTimeMillis();
                Key key = (Key) e.getSource();
                channel.noteOn(key.getNote(), velocity);

//                System.out.println(start);
                newest.setNote(key.getNote());
                newest.setTimePress(start);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (int i = 0; i < piano.getComponentCount(); i++) {
            if (e.getComponent().equals(piano.getComponent(i))) {
                long end = System.currentTimeMillis();
                Key key = (Key) e.getSource();
                channel.noteOff(key.getNote(), velocity);

                Notes add = new Notes(newest.getNote(), newest.getTimePress(), 0);
                add.setTimeRelease(end);
//                System.out.println(add);
                replay.add(add);
                recordFile.add(add);
                rewind.add(add);
                tink.insert(add.getNote(), add);
                tonk.insert(add.getNote(), add);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().equals(themeBt)) {
            Theme.main(null);
        }
        if (e.getComponent().equals(instrBt)) {
            DemoInstruction.main(null);
        }
        if (e.getComponent().equals(btnReplay)) {
            Convert.replay(replay);
        } else if (e.getComponent().equals(btnRewind)) {
            while (!rewind.isEmpty()) {
                Notes play = rewind.pop();
                channel.noteOn(play.getNote(), velocity);
                try {
                    Thread.sleep(play.getTimeRelease() - play.getTimePress());
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                channel.noteOff(play.getNote(), velocity);
                if (rewind.size() > 0) {
                    long start = rewind.peek().getTimeRelease();// time gap = next one - this one
                    try {
                        Thread.sleep(play.getTimePress() - start);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } else if (e.getComponent().equals(btnClear)) {
            replay.clear();
            rewind.clear();
            tink.setSize(0);
            tonk.setSize(0);
            recordFile.clear();
            
            // change instruments
        } else if (e.getComponent().equals(pianoBt)) {
            channel.programChange(0);

        } else if (e.getComponent().equals(violinBt)) {
            channel.programChange(40);

        } else if (e.getComponent().equals(guitarBt)) {
            channel.programChange(24);

        } else if (e.getComponent().equals(bellBt)) {
            channel.programChange(10);

        } else if (e.getComponent().equals(trumpetBt)) {
            channel.programChange(56);
            
        } else if (e.getComponent().equals(cakeBt)) {
            Convert song = new Convert();
            Convert.replay(song.hpbd);
        } else {
            return;
        }
    }
    
    
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void createAndShowGUI() {
        setTitle("Piano By An & Giang");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src\\piano_player\\icon.jpg"));
        setFont(new Font("Consolas", Font.BOLD, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(contentPane);
        setVisible(true);

        contentPane.setLayout(null);
        contentPane.setBackground(Color.white);

        // Make a piano panel
        piano = new JPanel(null) {
            @Override
            public Dimension getPreferredSize() {
                int count = getComponentCount();
                Component last = getComponent(count - 1);
                Rectangle bounds = last.getBounds();
                int width = bounds.x + bounds.width + 65;
                int height = bounds.y + bounds.height + 15;

                return new Dimension(width, height);
            }

            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }

        };

        piano.setBackground(Color.white);

        // the slide to change volume
        JSlider slider = new JSlider(SwingConstants.VERTICAL, 0, 127, 127);
        slider.setSnapToTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setForeground(Color.black);
        slider.setBorder(null);
        slider.setBackground(Color.WHITE);
        slider.setMinorTickSpacing(10);
        
        piano.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                velocity = (int) ((JSlider) e.getSource()).getValue();
            }
        });

        // create piano keys
        for (int posB = 0; posB < OCTAVES * 5; posB++) {
            BlackKey black = new BlackKey(posB);
            blacks.add(black);
            piano.add(black);
            black.addMouseListener(this);
        }

        for (int posW = 0; posW < OCTAVES * 7 + 1; posW++) {
            WhiteKey white = new WhiteKey(posW);
            whites.add(white);
            piano.add(white);
            white.addMouseListener(this);
        }

        piano.setBounds(0, 0, piano.getPreferredSize().width, piano.getPreferredSize().height);
        contentPane.add(piano);
        slider.setBounds(piano.getPreferredSize().width - 55, 10, 50, 160);

        // Setting buttons panel
        buttons.setBackground(new Color(255, 220, 230));
        buttons.setBounds(0, piano.getPreferredSize().height, piano.getPreferredSize().width, 60);

        jtpDecor.setText("🎼🎶🎵🎶🎵");
        jtpDecor.setOpaque(false);
        jtpDecor.setFont(new Font("Dialog", Font.PLAIN, 24));
        buttons.add(jtpDecor);

        btnMin.setForeground(Color.white);
        btnMin.setBackground(buttonsColor);
        btnMin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!tink.isEmpty()) {
                    Notes play = tink.removeMin().getValue();
                    channel.noteOn(play.getNote(), velocity);
                    try {
                        Thread.sleep(play.getTimeRelease() - play.getTimePress());
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    channel.noteOff(play.getNote(), velocity);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnMin.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
        buttons.add(btnMin);

        btnMax.setForeground(Color.white);
        btnMax.setBackground(buttonsColor);
        btnMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!tonk.isEmpty()) {
                    Notes play = tonk.removeMax().getValue();
                    channel.noteOn(play.getNote(), velocity);
                    try {
                        Thread.sleep(play.getTimeRelease() - play.getTimePress());
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    channel.noteOff(play.getNote(), velocity);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        btnMax.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
        buttons.add(btnMax);

        btnRewind.setForeground(Color.white);
        btnRewind.setBackground(buttonsColor);
        btnRewind.addMouseListener(this);
        btnRewind.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
        buttons.add(btnRewind);

        btnReplay.setForeground(Color.white);
        btnReplay.setBackground(buttonsColor);
        btnReplay.addMouseListener(this);
        btnReplay.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
        buttons.add(btnReplay);

        btnClear.setForeground(Color.white);
        btnClear.setBackground(clearColor);
        btnClear.addMouseListener(this);
        btnClear.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 28));
        buttons.add(btnClear);

        jtpDecor1.setText("🎼🎶🎵🎶🎵");
        jtpDecor1.setFont(new Font("Dialog", Font.PLAIN, 24));
        jtpDecor1.setOpaque(false);
        buttons.add(jtpDecor1);

        contentPane.add(buttons);

        // Setting Instruments Panel
        
        label.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 27));
        label.setOpaque(false);
        instruments.add(label);

        pianoBt.setForeground(Color.black);
        pianoBt.setBackground(buttonsColor);
        pianoBt.addMouseListener(this);
        pianoBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        instruments.add(pianoBt);

        guitarBt.setForeground(new Color(160, 82, 45));
        guitarBt.setBackground(buttonsColor);
        guitarBt.addMouseListener(this);
        guitarBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        instruments.add(guitarBt);

        violinBt.setForeground(new Color(160, 82, 45));
        violinBt.setBackground(buttonsColor);
        violinBt.addMouseListener(this);
        violinBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        instruments.add(violinBt);

        bellBt.setForeground(Color.yellow);
        bellBt.setBackground(buttonsColor);
        bellBt.addMouseListener(this);
        bellBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        instruments.add(bellBt);

        trumpetBt.setForeground(Color.yellow);
        trumpetBt.setBackground(buttonsColor);
        trumpetBt.addMouseListener(this);
        trumpetBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        instruments.add(trumpetBt);

        instruments.setBackground(mainBackground);
        instruments.setBounds(0, piano.getPreferredSize().height + 60, piano.getPreferredSize().width, 60);
        contentPane.add(instruments);
        
        //recording panel
        themeBt.setForeground(Color.white);
        themeBt.setBackground(buttonsColor);
        themeBt.addMouseListener(this);
        themeBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        recording.add(themeBt);

        instrBt.setForeground(Color.red);
        instrBt.setBackground(buttonsColor);
        instrBt.addMouseListener(this);
        instrBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        recording.add(instrBt);
        
        label1.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 27));
        label1.setOpaque(false);
        recording.add(label1);
        
        cakeBt.setForeground(Color.white);
        cakeBt.setBackground(buttonsColor);
        cakeBt.setFont(new Font("Dialog", Font.PLAIN, 28));
        recording.add(cakeBt);
        cakeBt.addMouseListener(this);
        
        play.setForeground(Color.white);
        play.setBackground(buttonsColor);
        play.setFont(new Font("Dialog", Font.PLAIN, 28));
        recording.add(play);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Queue<Notes> temp = new ArrayDeque<>();
                try {
                    temp = Convert.scanIn(temp, "src/piano_player/Saved.txt");
                    Convert.replay(temp);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
            
        record.setForeground(Color.red);
        record.setBackground(buttonsColor);
        record.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomAccessFile save = null;
                try {
                    save = new RandomAccessFile("src/piano_player/Saved.txt", "rw");
                    save.setLength(0);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                
                FileOutputStream outFile = null;
                try {
                    outFile = new FileOutputStream("src/piano_player/Saved.txt");
                    PrintWriter out = new PrintWriter(outFile);
                    while (recordFile.size()!= 0) {
                        String s = recordFile.poll().toString();
                        out.println(s);
                    }
                    out.close();
                    System.out.println("done "+ recordFile.size());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        record.setFont(new Font("Dialog", Font.PLAIN, 28));
        recording.add(record);
        
        recording.setBackground(mainBackground);
        recording.setBounds(0, piano.getPreferredSize().height + 120, piano.getPreferredSize().width, 65);
        contentPane.add(recording);
        
        setSize(piano.getPreferredSize().width + 15, piano.getPreferredSize().height + 225);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PianoPlayer().createAndShowGUI();
            }
        });
    }

}