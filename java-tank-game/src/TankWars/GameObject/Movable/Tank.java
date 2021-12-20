package TankWars.GameObject.Movable;

import TankWars.GameConstants;
import TankWars.GameObject.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author anthony-pc
 */
public class Tank extends GameObject {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private float angle;
    private int health;
    private int armor;
    private int lives;
    private int damage;
    private final int R = 2;
    private final float ROTATIONSPEED = 3.0f;
    private final Rectangle hitBox;
    private static ArrayList<Bullet> ammo;
    private final BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
//    private final SoundPlayer bulletS;
//    private final SoundPlayer rocketS;

    public Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.hitBox = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
        ammo = new ArrayList<>();

//        this.bulletS = new SoundPlayer("Sounds/bullet.wav");
//        this.rocketS = new SoundPlayer("Sounds/rocket.wav");
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    public int getLives() {
        return lives;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVy() {
        return vy;
    }

    public int getVx() {
        return vx;
    }

    public boolean isDownPressed() {
        return DownPressed;
    }

    public boolean isUpPressed() {
        return UpPressed;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }

        if (getDamage() > 25) {
            if (this.ShootPressed && GameWorld.tick % 120 == 0) {
                Bullet b = new Bullet(x, y, (int) angle, Resource.getResourceImage("Rocket"), this);
                ammo.add(b);
//                rocketS.play();
//                rocketS.reset();
            }
        } else {
            if (this.ShootPressed && GameWorld.tick % 50 == 0) {
                Bullet b = new Bullet(x, y, (int) angle, Resource.getResourceImage("Bullet"), this);
                ammo.add(b);
//                bulletS.play();
//                bulletS.reset();
            }
        }
        ammo.forEach(Bullet::update);
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, img.getWidth(), img.getHeight());
    }

    public static ArrayList<Bullet> getBullet() {
        return ammo;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        this.hitBox.setLocation(x, y);
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.WORLD_WIDTH - 88) {
            x = GameConstants.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.WORLD_HEIGHT - 80) {
            y = GameConstants.WORLD_HEIGHT - 80;
        }
    }

    public static int checkXCoordinate(Tank t) {
        int x = t.getX();
        if (x < GameConstants.GAME_SCREEN_WIDTH / 4) {
            x = GameConstants.GAME_SCREEN_WIDTH / 4;
        }
        if (x > GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH / 4) {
            x = GameConstants.WORLD_WIDTH - GameConstants.GAME_SCREEN_WIDTH / 4;
        }
        return x;
    }

    public static int checkYCoordinate(Tank t) {
        int y = t.getY();
        if (y < GameConstants.GAME_SCREEN_HEIGHT / 2) {
            y = GameConstants.GAME_SCREEN_HEIGHT / 2;
        }
        if (y > GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT / 2) {
            y = GameConstants.WORLD_HEIGHT - GameConstants.GAME_SCREEN_HEIGHT / 2;
        }
        return y;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void drawImage(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);
        ammo.forEach(bullet -> bullet.drawImage(g));

        g2d.setColor(Color.blue);
        for (int i = 0; i < this.armor; i++) {
            g2d.fillRect(getX() - this.img.getWidth() / 2 + 10, getY() - 60, this.armor + i, 10);
        }

        g2d.setColor(Color.green);
        g2d.drawRect(getX() - this.img.getWidth() / 2 + 10, getY() - 45, 100, 10);
        if (this.health <= 100) {
            g2d.setColor(Color.green);
            g2d.fillRect(getX() - this.img.getWidth() / 2 + 10, getY() - 45, this.health, 10);
        }
        if (this.health <= 75) {
            g2d.setColor(Color.orange);
            g2d.fillRect(getX() - this.img.getWidth() / 2 + 10, getY() - 45, this.health, 10);
        }
        if (this.health <= 50) {
            g2d.setColor(Color.yellow);
            g2d.fillRect(getX() - this.img.getWidth() / 2 + 10, getY() - 45, this.health, 10);
        }
        if (this.health <= 25) {
            g2d.setColor(Color.red);
            g2d.fillRect(getX() - this.img.getWidth() / 2 + 10, getY() - 45, this.health, 10);
        }

        g2d.setColor(Color.red);
        int lifeBoxes = this.img.getWidth() / 2 - 4;
        for (int i = 0; i < this.lives; i++) {
            g2d.fillRect(getX() + i * (lifeBoxes), getY() - 23, 10, 10);
        }
    }

}