package piano_player;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.SwingConstants;

public class Theme extends JFrame {

    private JPanel contentPane;
    RoundedButton btnNewButton = new RoundedButton("DREAMY", 30);
    RoundedButton btnNewButton_1 = new RoundedButton("SIMPLE", 30);
    RoundedButton btnNewButton_2 = new RoundedButton("FRESH", 30);
    private final JLabel lblNewLabel_1 = new JLabel("（づ￣3￣）づ╭❤️～");
    private final JLabel lblNewLabel_2 = new JLabel("ლ(╹◡╹ლ)");
    private final JLabel lblNewLabel_3 = new JLabel(">>>");

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Theme frame = new Theme();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Theme() {
        setSize(450, 300);
        setTitle("Piano Theme");
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Choose a theme");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setBounds(10, 20, 416, 30);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
        
        contentPane.add(lblNewLabel);
        
        lblNewLabel_3.setFont(new Font("SymbolPi", Font.BOLD, 30));
        lblNewLabel_3.setForeground(Color.red);
        contentPane.add(lblNewLabel_3);

        btnNewButton.setForeground(new Color(250, 120, 145));
        btnNewButton.setBackground(new Color(255, 220, 230));
        btnNewButton.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        btnNewButton.setBounds(170, 70, 110, 30);
        
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Color background = new Color(255, 220, 230);
                Color buttonsColor = new Color(255, 175, 190);
                Color clear = new Color(250, 120, 145);
                PianoPlayer.buttons.setBackground(background);
                PianoPlayer.instruments.setBackground(background);
                PianoPlayer.recording.setBackground(background);
                
                PianoPlayer.btnClear.setBackground(clear);
                PianoPlayer.btnMin.setBackground(buttonsColor);
                PianoPlayer.btnMax.setBackground(buttonsColor);
                PianoPlayer.btnReplay.setBackground(buttonsColor);
                PianoPlayer.btnRewind.setBackground(buttonsColor);
                
                PianoPlayer.bellBt.setBackground(buttonsColor);
                PianoPlayer.pianoBt.setBackground(buttonsColor);
                PianoPlayer.guitarBt.setBackground(buttonsColor);
                PianoPlayer.violinBt.setBackground(buttonsColor);
                PianoPlayer.trumpetBt.setBackground(buttonsColor);
                
                PianoPlayer.themeBt.setBackground(buttonsColor);
                PianoPlayer.instrBt.setBackground(buttonsColor);
                PianoPlayer.cakeBt.setBackground(buttonsColor);
                PianoPlayer.play.setBackground(buttonsColor);
                PianoPlayer.record.setBackground(buttonsColor);
                
                lblNewLabel_3.setBounds(100, 70, 70, 30);
            }
        });
        contentPane.add(btnNewButton);

        btnNewButton_1.setBackground(new Color(222, 222, 222));
        btnNewButton_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        btnNewButton_1.setBounds(170, 120, 110, 30);
        
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Color background = new Color(222, 222, 222);
                Color buttonsColor = Color.gray;
                Color clear = Color.black;
                PianoPlayer.buttons.setBackground(background);
                PianoPlayer.instruments.setBackground(background);
                PianoPlayer.recording.setBackground(background);
                
                PianoPlayer.btnClear.setBackground(clear);
                PianoPlayer.btnReplay.setBackground(buttonsColor);
                PianoPlayer.btnRewind.setBackground(buttonsColor);
                PianoPlayer.btnMin.setBackground(buttonsColor);
                PianoPlayer.btnMax.setBackground(buttonsColor);
                
                PianoPlayer.bellBt.setBackground(buttonsColor);
                PianoPlayer.pianoBt.setBackground(buttonsColor);
                PianoPlayer.guitarBt.setBackground(buttonsColor);
                PianoPlayer.violinBt.setBackground(buttonsColor);
                PianoPlayer.trumpetBt.setBackground(buttonsColor);
                
                PianoPlayer.themeBt.setBackground(buttonsColor);
                PianoPlayer.instrBt.setBackground(buttonsColor);
                PianoPlayer.cakeBt.setBackground(buttonsColor);
                PianoPlayer.play.setBackground(buttonsColor);
                PianoPlayer.record.setBackground(buttonsColor);
                
                lblNewLabel_3.setBounds(100, 120, 70, 30);
            }
        });
        contentPane.add(btnNewButton_1);

        btnNewButton_2.setForeground(new Color(46, 139, 87));
        btnNewButton_2.setBackground(new Color(224, 255, 255));
        btnNewButton_2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
        btnNewButton_2.setBounds(170, 170, 110, 30);
        
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Color background = new Color(224, 255, 255);
                Color buttonsColor = new Color(102, 204, 153);
                Color clear = new Color(46, 139, 87);
                PianoPlayer.buttons.setBackground(background);
                PianoPlayer.instruments.setBackground(background);
                PianoPlayer.recording.setBackground(background);
                
                PianoPlayer.btnClear.setBackground(clear);
                PianoPlayer.btnReplay.setBackground(buttonsColor);
                PianoPlayer.btnRewind.setBackground(buttonsColor);
                PianoPlayer.btnMin.setBackground(buttonsColor);
                PianoPlayer.btnMax.setBackground(buttonsColor);
                
                PianoPlayer.bellBt.setBackground(buttonsColor);
                PianoPlayer.pianoBt.setBackground(buttonsColor);
                PianoPlayer.guitarBt.setBackground(buttonsColor);
                PianoPlayer.violinBt.setBackground(buttonsColor);
                PianoPlayer.trumpetBt.setBackground(buttonsColor);
                
                PianoPlayer.themeBt.setBackground(buttonsColor);
                PianoPlayer.instrBt.setBackground(buttonsColor);
                PianoPlayer.cakeBt.setBackground(buttonsColor);
                PianoPlayer.play.setBackground(buttonsColor);
                PianoPlayer.record.setBackground(buttonsColor);
                
                lblNewLabel_3.setBounds(100, 170, 70, 30);
            }
        });
        contentPane.add(btnNewButton_2);
        lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(10, 195, 165, 57);
        
        contentPane.add(lblNewLabel_1);
        lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 17));
        lblNewLabel_2.setBounds(320, 200, 86, 42);
        
        contentPane.add(lblNewLabel_2);
        
        
    }
}
