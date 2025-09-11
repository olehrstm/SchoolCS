package de.olehrstm.school.nim;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class Application extends JFrame {

    private final JButton startButton = new JButton();
    private static final int MAX_TAKE = 3;

    public Application() {
        // Frame init
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("NIM");
        setResizable(true);
        Container cp = getContentPane();
        cp.setLayout(null);

        // components
        startButton.setBounds(64, 32, 136, 104);
        startButton.setFont(new Font("Dialog", Font.BOLD, 11));
        startButton.setText("Start");
        startButton.setMargin(new Insets(2, 2, 2, 2));
        startButton.addActionListener(event -> {
            int count = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Hölzchen gibt es am Anfang? (Standard: 21)"));
            JOptionPane.showMessageDialog(null, "Es gibt " + count + " Hölzchen.");

            while (count > 0) {
                int take = Integer.parseInt(JOptionPane.showInputDialog("Wie viele Hölzchen ziehst du, Spieler 1?"));
                if (take > MAX_TAKE) {
                    JOptionPane.showMessageDialog(null, "Du darfst nur maximal " + MAX_TAKE + " Hölzchen ziehen!");
                    continue;
                }
                count -= take;
                JOptionPane.showMessageDialog(null, "Es sind noch " + count + " Hölzchen übrig.");

                if (count > 0) {
                    take = 4 - take;
                    if (take > MAX_TAKE) {
                        JOptionPane.showMessageDialog(null, "Du darfst nur maximal " + MAX_TAKE + " Hölzchen ziehen!");
                        continue;
                    }
                    count -= take;
                    JOptionPane.showMessageDialog(null, "Der Computer zieht " + take + " Hölzchen.\nEs sind noch " + count + " Hölzchen übrig.");

                    if (count <= 0) {
                        JOptionPane.showMessageDialog(null, "Spieler 2 hat verloren!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Spieler 1 hat verloren!");
                }
            }
        });
        startButton.setBackground(Color.GREEN);
        cp.add(startButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Application();
    }
}
