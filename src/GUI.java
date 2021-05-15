import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import checker.SpellChecker;

public class GUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JTextField field;
    private JButton button, reset;

    public GUI() {
        frame = new JFrame();
        field = new JTextField();
        reset = new JButton("Reset");
        button = new JButton("Submit");
        label = new JLabel("Nothing Entered");

        reset.addActionListener(this);
        button.addActionListener(this);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(field);
        panel.add(reset);
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Spell Checker");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            label.setText("Nothing Entered");
            field.setText("");
        } else {
            SpellChecker checker = new SpellChecker();
            String str = checker.check(field.getText());        
            label.setText(str);
        }
    }
}