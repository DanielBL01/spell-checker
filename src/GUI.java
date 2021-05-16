import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import checker.SpellChecker;
import javax.swing.event.DocumentListener;

public class GUI implements ActionListener {
    private JTextArea display;
    private JFrame frame;
    private JPanel panel;
    private JTextField field;
    private JButton reset;
    private SpellChecker checker;
    public GUI() {
        checker = new SpellChecker();
        frame = new JFrame();
        field = new JTextField();
        reset = new JButton("Reset");
        reset.addActionListener(this);
        display = new JTextArea("Nothing Entered");
        display.setWrapStyleWord(true);
        display.setLineWrap(true);

        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            public void insertUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            public void removeUpdate(DocumentEvent documentEvent) {
                printIt(documentEvent);
            }
            private void printIt(DocumentEvent documentEvent) {
                String str = field.getText().toLowerCase();
                String prefix = str.substring(str.lastIndexOf(" ") + 1);
                if (prefix.length() >= 2) {
                    String ac = checker.autoComplete(prefix);
                    if (ac == "0") {
                        display.setText("Found no recommended words");
                    } else {
                        display.setText(ac);
                    }
                }
            }
        };
        field.getDocument().addDocumentListener(documentListener);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(field);
        panel.add(reset);
        panel.add(display);

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
            display.setText("Nothing Entered");
            field.setText("");
        }
    }
}