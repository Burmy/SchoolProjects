package TankWars.Menus;


import TankWars.GameObject.SoundPlayer;
import TankWars.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;


public class StartMenuPanel extends JPanel {


    private final Image menuBackground;
    private final Launcher lf;

    private final SoundPlayer prepareS;
    private final SoundPlayer menuS;
    private final SoundPlayer playS;
    private final SoundPlayer helpS;

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public StartMenuPanel(Launcher lf) {
        this.lf = lf;
        this.prepareS = new SoundPlayer("Sounds/prepare.wav");
        this.menuS = new SoundPlayer("Sounds/menuMusic.wav");
        menuS.loop();
        this.playS = new SoundPlayer("Sounds/playMusic.wav");
        this.helpS = new SoundPlayer("Sounds/helpMusic.wav");

        setBackground(Color.BLACK);
        this.setBackground(Color.BLACK);

        menuBackground = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/title.gif"))).getImage();
        this.setLayout(null);

        JButton start = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/startButton.png")));
            start.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start.setBounds(250, 575, 300, 50);
        start.addActionListener((actionEvent -> {
            prepareS.play();
            wait(1700);
            this.lf.setFrame("game");
            menuS.stop();
            playS.loop();
        }));

        JButton help = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/helpButton.png")));
            help.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        help.setBounds(250, 650, 300, 50);
        help.addActionListener((actionEvent -> {
            menuS.stop();
            helpS.play();
            this.lf.setFrame("help");
        }));

        JButton exit = new JButton();
        try {
            Image img = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Menus/exitButton.png")));
            exit.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit.setBounds(250, 725, 300, 50);
        exit.addActionListener((actionEvent -> {
            this.lf.closeGame();
        }));

        this.add(start);
        this.add(help);
        this.add(exit);

    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(menuBackground, 0, 0, this);
    }

}