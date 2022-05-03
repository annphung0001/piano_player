package piano_player;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DemoInstruction extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DemoInstruction frame = new DemoInstruction();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public DemoInstruction() {
        getContentPane().setBackground(Color.WHITE);
        setTitle("Piano Instruction");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Mannual Instruction");
        lblNewLabel.setBounds(0, 10, 500, 35);
        lblNewLabel.setOpaque(false);
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblNewLabel);
        
        JTextArea txtrPressIf = new JTextArea();
        txtrPressIf.setOpaque(false);
        txtrPressIf.setForeground(Color.BLACK);
        txtrPressIf.setBounds(20, 60, 470, 300);
        txtrPressIf.setWrapStyleWord(true);
        txtrPressIf.setFont(new Font("Dialog", Font.ITALIC, 15));
        txtrPressIf.setText("Press \"🎨\" if you want to change the theme."
                + "\nChoose your favorite musical instrument."
                + "\nChange the volume by using the slider to the right of the piano."
                + "\nPress the piano keys to play your music."
                + "\n\nAfter pressing the piano keys,"
                + "\nIf you want to replay what you just played, press \"REPLAY\"."
                + "\nIf you want to play reverse of what you just played, press \"REWIND\"."
                + "\nThere are two more special modes to explore \"TINK\", \"TONK\"."
                + "\nAnd remember that before you play another songs, press \"CLEAR\"."
                + "\nIf you like what you played, record it. But it can only save 1 song."
                + "\nAnd there is a sample song named \"Happy Birthday\", try playing it."
                + "\nOkay, Let's START !!!");
        txtrPressIf.setEditable(false);
        getContentPane().add(txtrPressIf);
        
        
        
        JLabel lblNewLabel_1 = new JLabel("☆*: .｡. o(≧▽≦)o .｡.:*☆");
        lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(0, 300, 500, 36);
        getContentPane().add(lblNewLabel_1);
        
    }
}
