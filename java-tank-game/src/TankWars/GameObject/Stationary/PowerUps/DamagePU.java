package TankWars.GameObject.Stationary.PowerUps;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DamagePU extends PowerUps {

    int x, y;
    BufferedImage powerUp;

    public DamagePU(int x, int y, BufferedImage powerUp) {
        this.x = x;
        this.y = y;
        this.powerUp = powerUp;
    }

    @Override
    public void drawImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.powerUp, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, powerUp.getWidth(), powerUp.getHeight());
    }

}
