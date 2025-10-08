package de.olehrstm.school.nim;

import de.olehrstm.school.nim.service.NimInputService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

@Slf4j
public class NimApplication extends JFrame {

    public NimApplication() {
        // Frame init
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        JButton startButton = new JButton();
        startButton.setBounds(60, 30, 150, 100);
        startButton.setFont(new Font("Dialog", Font.BOLD, 11));
        startButton.setText("Start");
        startButton.setMargin(new Insets(2, 2, 2, 2));
        startButton.addActionListener(event -> {
            NimGame nimGame = new NimGame(new NimInputService());
            nimGame.init();
        });
        startButton.setBackground(Color.GREEN);
        cp.add(startButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new NimApplication();
    }
}
