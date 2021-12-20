package TankWars.GameObject.Movable;

import TankWars.GameConstants;
import TankWars.GameObject.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {
    int x, y, vx, vy, angle;
    int R = 8;
    BufferedImage bulletImage;
    Rectangle hitBox;
    private final Tank tank;

    public Bullet(int x, int y, int angle, BufferedImage bulletImage, Tank tank) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.bulletImage = bulletImage;
        this.hitBox = new Rectangle(x, y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight());
    }

    public void update() {
        moveForwards();
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth() / 2.0, this.bulletImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.bulletImage, rotation, null);
    }
}