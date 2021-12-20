package TankWars.Menus;

import TankWars.GameObject.SoundPlayer;
import TankWars.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HelpMenuPanel extends JPanel {

    private BufferedImage menuBackground;
    private final Launcher lf;

    private final SoundPlayer prepareS;
    private final SoundPlayer playS;

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public HelpMenuPanel(Launcher lf) {
        this.lf = lf;
        this.prepareS = new SoundPlayer("Sounds/prepare.wav");
        this.playS = new SoundPlayer("Sounds/playMusic.wav");

        try {
            menuBackground = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("InGame/help.png")));
        } catch (IOException e) {
            System.out.println("Error cant read menu background");
            e.printStackTrace();
            System.exit(-3);
        }
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        JButton start = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/startButton.png")));
            start.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start.setBounds(200, 575, 300, 50);
        start.addActionListener((actionEvent -> {
            prepareS.play();
            wait(1700);
            this.lf.setFrame("game");
            playS.loop();
        }));

        JButton exit = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/exitButton.png")));
            exit.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit.setBounds(200, 650, 300, 50);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));

        this.add(start);
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.menuBackground, 0, 0, null);
    }

}