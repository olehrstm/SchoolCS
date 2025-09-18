package de.olehrstm.school.nim;

import de.olehrstm.school.nim.service.InputService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class Application extends JFrame {

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
        JButton startButton = new JButton();
        startButton.setBounds(60, 30, 150, 100);
        startButton.setFont(new Font("Dialog", Font.BOLD, 11));
        startButton.setText("Start");
        startButton.setMargin(new Insets(2, 2, 2, 2));
        startButton.addActionListener(event -> {
            NimGame nimGame = new NimGame(new InputService());
            nimGame.init();
        });
        startButton.setBackground(Color.GREEN);
        cp.add(startButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Application();
    }
}
