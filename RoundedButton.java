package piano_player;

import java.awt.Graphics;

import javax.swing.JButton;

// https://stackoverflow.com/questions/45773950/creating-a-button-with-a-round-background-color-java

public class RoundedButton extends JButton {
    private int radius = 10;

    public RoundedButton(String label) {
        super(label);

        setContentAreaFilled(false);
    }
    
    public RoundedButton(String label, int radius) {
        super(label);

        setContentAreaFilled(false);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius); 
    }


}