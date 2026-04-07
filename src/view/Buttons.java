package Project.src.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Buttons extends JButton{
    public Buttons(){
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public Buttons(String t){
        setText(t);
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusable(false);
    }
    public Buttons(String t, int w, int h, java.awt.Color c){
        setText(t);
        setPreferredSize(new Dimension(w, h));
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(c);
        setFont(new Font("Arial", Font.PLAIN, 14));
    }
    public Buttons(String t, int id ){
        setText(t);
        setPreferredSize(new Dimension(50,50));
        setBorderPainted(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.CYAN);
        setFont(new Font("Arial", Font.PLAIN, 11));
    }
}
