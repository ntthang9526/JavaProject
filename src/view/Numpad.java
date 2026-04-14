package Project.src.view;
import javax.swing.*;
import java.awt.*;
public class Numpad extends JPanel {
    private JTextField textField;

    public Numpad(){
        setLayout(new GridLayout(4, 3, 5, 5));
        String[] buttons = {
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "C", "0", "A"
        };

        for (String s : buttons){
            JButton btn = new JButton(s);
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);

            if (s.equals("C") || s.equals("A")) btn.setForeground(Color.RED);
            btn.addActionListener(e -> {
                handleClick(s);
            });
            add(btn);
        }
    }
    public Numpad(JTextField target){
        this.textField = target;
        setLayout(new GridLayout(4, 3, 5, 5));
        String[] buttons = {
            "7", "8", "9",
            "4", "5", "6",
            "1", "2", "3",
            "C", "0", "A"
        };

        for (String s : buttons){
            JButton btn = new JButton(s);
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setFocusPainted(false);
            btn.setBackground(Color.WHITE);

            if (s.equals("C") || s.equals("A")) btn.setForeground(Color.RED);
            btn.addActionListener(e -> {
                handleClick(s);
            });
            add(btn);
        }
    }
    public void setTarget(JTextField newTarget) {
        this.textField = newTarget;
    }
    private void handleClick(String text){
        if (textField == null) return;

        String currentText = textField.getText();
        if (text.equals("C")){
            textField.setText("");
        }
        else if (text.equals("A")){
            if (!currentText.isEmpty()){
                currentText = currentText.substring(0, currentText.length() - 1);
                textField.setText(currentText);
            }
        }
        else{
            textField.setText(currentText + text);
        }
        textField.requestFocusInWindow();
    }
}
