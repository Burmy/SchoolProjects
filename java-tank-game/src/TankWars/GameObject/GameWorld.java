/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWars.GameObject;

import TankWars.GameConstants;
import TankWars.GameObject.Movable.Tank;
import TankWars.GameObject.Movable.TankControl;
import TankWars.GameObject.Stationary.Walls.Background;
import TankWars.GameObject.Stationary.Walls.BreakWall;
import TankWars.GameObject.Stationary.PowerUps.DamagePU;
import TankWars.GameObject.Stationary.PowerUps.ArmorPU;
import TankWars.GameObject.Stationary.PowerUps.LivesPU;
import TankWars.GameObject.Stationary.Walls.UnBreakWall;
import TankWars.GameObject.Stationary.Walls.Wall;
import TankWars.Launcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author anthony-pc
 */

public class GameWorld extends JPanel implements Runnable {

    private static boolean gameOver = false;
    private BufferedImage world;
    private Tank t1;
    private Tank t2;
    private final Launcher lf;
    public static long tick = 0;

    Collision collision;
    ArrayList<Wall> walls;
    ArrayList<DamagePU> extraDamagePU;
    ArrayList<ArmorPU> armorPU;
    ArrayList<LivesPU> extraLifePU;
    ArrayList<Background> background;
    SoundPlayer winS;

    public GameWorld(Launcher lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        try {
            this.resetGame();
            while (!gameOver) {
                tick++;
                this.t1.update(); // update tank
                this.t2.update();
                this.repaint();   // redraw game

                collision.tankCollision(this.t1, this.t2);
                collision.wallCollision(this.t1, this.getWalls());
                collision.wallCollision(this.t2, this.getWalls());
                collision.bulletWallCollision(this.getWalls());
                collision.bulletWallCollision(this.getWalls());
                collision.bulletTankCollision(this.t1, this.t2);
                collision.bulletTankCollision(this.t2, this.t1);
                collision.DamagePU(this.t1, this.getDmgPowerUp());
                collision.DamagePU(this.t2, this.getDmgPowerUp());
                collision.ArmorPU(this.t1, this.getArmor());
                collision.ArmorPU(this.t2, this.getArmor());
                collision.LivesPU(this.t1, this.getLivesPowerUp());
                collision.LivesPU(this.t2, this.getLivesPowerUp());

                Thread.sleep(1000 / 144);

                if (t2.getLives() <= 0) {
                    this.lf.setFrame("end1");
                    this.winS = new SoundPlayer("Sounds/victory.wav");
                    winS.play();
                    return;
                }
                if (t1.getLives() <= 0) {
                    this.lf.setFrame("end2");
                    this.winS = new SoundPlayer("Sounds/victory.wav");
                    winS.play();
                    return;
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }

    //    /**
//     * Reset game to its initial state.
//     */
    public void resetGame() {
        tick = 0;
        this.t1.setX(300);
        this.t1.setY(200);
        this.t2.setX(1700);
        this.t2.setY(1800);
        this.t1.setHealth(100);
        this.t2.setHealth(100);
        this.t1.setArmor(0);
        this.t2.setArmor(0);
        this.t1.setLives(3);
        this.t2.setLives(3);
        this.t1.setDamage(25);
        this.t2.setDamage(25);
    }

    /**
     * Load all resources for Tank Wars Game. Set all Game Objects to their
     * initial state as well.
     */
    public void gameInitialize() {
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH,
                GameConstants.WORLD_HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        collision = new Collision(t1, t2);
        walls = new ArrayList<>();
        extraDamagePU = new ArrayList<>();
        armorPU = new ArrayList<>();
        extraLifePU = new ArrayList<>();
        background = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(GameWorld.class.getClassLoader().getResourceAsStream("Maps/map1")));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if (row == null) {
                throw new IOException("No data in file.");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for (int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for (int curCol = 0; curCol < numCols; curCol++) {
                    Background tile = new Background((curCol * 35), (curRow * 35), Resource.getResourceImage("Grass"));
                    background.add(tile);
                    switch (mapInfo[curCol]) {
                        case "2" -> walls.add(new BreakWall(curCol * 35, curRow * 35, Resource.getResourceImage("BreakableWall")));
                        case "3", "9" -> walls.add(new UnBreakWall(curCol * 35, curRow * 35, Resource.getResourceImage("UnBreakableWall")));
                        case "4" -> armorPU.add(new ArmorPU(curCol * 35, curRow * 35, Resource.getResourceImage("Armor")));
                        case "5" -> extraDamagePU.add(new DamagePU(curCol * 35, curRow * 35, Resource.getResourceImage("Damage")));
                        case "6" -> extraLifePU.add(new LivesPU(curCol * 35, curRow * 35, Resource.getResourceImage("Lives")));
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        t1 = new Tank(300, 200, 0, 0, 0, Resource.getResourceImage("Tank1"));
        t2 = new Tank(1700, 1800, 0, 0, 180, Resource.getResourceImage("Tank2"));
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.setBackground(Color.BLACK);
        this.lf.getJf().addKeyListener(tc1);
        this.lf.getJf().addKeyListener(tc2);
    }

    private ArrayList<Wall> getWalls() {
        return walls;
    }

    private ArrayList<DamagePU> getDmgPowerUp() {
        return extraDamagePU;
    }

    private ArrayList<ArmorPU> getArmor() {
        return armorPU;
    }

    private ArrayList<LivesPU> getLivesPowerUp() {
        return extraLifePU;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        background.forEach(background -> background.drawImage(buffer));
        walls.forEach(wall -> wall.drawImage(buffer));
        extraDamagePU.forEach(extraDamagePU -> extraDamagePU.drawImage(buffer));
        armorPU.forEach(ArmorUp -> ArmorUp.drawImage(buffer));
        extraLifePU.forEach(AddLife -> AddLife.drawImage(buffer));
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);

        int t1XCoordinate = Tank.checkXCoordinate(t1) - GameConstants.GAME_SCREEN_WIDTH / 4;
        int t2XCoordinate = Tank.checkXCoordinate(t2) - GameConstants.GAME_SCREEN_WIDTH / 4;
        int t1YCoordinate = Tank.checkYCoordinate(t1) - GameConstants.GAME_SCREEN_HEIGHT / 2;
        int t2YCoordinate = Tank.checkYCoordinate(t2) - GameConstants.GAME_SCREEN_HEIGHT / 2;

        BufferedImage leftHalf = world.getSubimage(t1XCoordinate, t1YCoordinate, GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);
        BufferedImage rightHalf = world.getSubimage(t2XCoordinate, t2YCoordinate, GameConstants.GAME_SCREEN_WIDTH / 2, GameConstants.GAME_SCREEN_HEIGHT);
        BufferedImage mm = world.getSubimage(0, 0, GameConstants.WORLD_WIDTH - 27, GameConstants.WORLD_HEIGHT - 40);

        g2.drawImage(leftHalf, 0, 0, null);
        g2.drawImage(rightHalf, GameConstants.GAME_SCREEN_WIDTH / 2 + 4, 0, null);

        g2.scale(.15, .15);
        g2.drawImage(mm, 4650, 0, null);
    }

}