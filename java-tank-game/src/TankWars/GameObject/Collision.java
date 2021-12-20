package TankWars.GameObject;

import TankWars.GameObject.Movable.Bullet;
import TankWars.GameObject.Movable.Tank;
import TankWars.GameObject.Stationary.Walls.BreakWall;
import TankWars.GameObject.Stationary.PowerUps.DamagePU;
import TankWars.GameObject.Stationary.PowerUps.ArmorPU;
import TankWars.GameObject.Stationary.PowerUps.LivesPU;
import TankWars.GameObject.Stationary.Walls.Wall;

import java.awt.*;
import java.util.*;

public class Collision {
    GameObject tank1, tank2;

//    private final SoundPlayer collisionS;
//    private final SoundPlayer pickupS;
//    private final SoundPlayer explosionS;

    Collision(Tank tank1, Tank tank2) {
        this.tank1 = tank1;
        this.tank2 = tank2;

//        this.explosionS = new SoundPlayer("Sounds/explosion.wav");
//        this.collisionS = new SoundPlayer("Sounds/collision.wav");
//        this.pickupS = new SoundPlayer("Sounds/pickup.wav");
    }

    public void tankCollision(Tank tank1, Tank tank2) {
        Rectangle hitBox1 = new Rectangle(tank1.getBounds());
        Rectangle hitBox2 = new Rectangle(tank2.getBounds());

        if (hitBox1.intersects(hitBox2)) {
            if (tank2.isDownPressed()) {
                tank2.setX(tank2.getX() + tank2.getVx());
                tank2.setY(tank2.getY() + tank2.getVy());
            }
            if (tank2.isUpPressed()) {
                tank2.setX(tank2.getX() - tank2.getVx());
                tank2.setY(tank2.getY() - tank2.getVy());
            }
        }
    }

    public void wallCollision(Tank t, ArrayList<Wall> walls) {
        Rectangle hitBox = new Rectangle(t.getBounds());
        for (Wall wall : walls) {
            if (hitBox.intersects(wall.getBounds())) {
                if (t.isUpPressed()) {
                    t.setX(t.getX() - t.getVx());
                    t.setY(t.getY() - t.getVy());
                } else if (t.isDownPressed()) {
                    t.setX(t.getX() + t.getVx());
                    t.setY(t.getY() + t.getVy());
                }
            }
        }
    }

    public void bulletWallCollision(ArrayList<Wall> wall) {
        Bullet bullet;
        Wall walls;
        ArrayList<Bullet> bullets = Tank.getBullet();

        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            Rectangle hitBox1 = new Rectangle(bullet.getBounds());

            for (int j = 0; j < wall.size(); j++) {
                walls = wall.get(j);

                Rectangle hitBox2 = new Rectangle(walls.getBounds());
                if (hitBox1.intersects(hitBox2)) {
                    bullets.remove(bullet);

                    if (walls instanceof BreakWall) {
                        wall.remove(walls);
                    }
                }
            }
        }
    }

    public void bulletTankCollision(Tank tank1, Tank tank2) {
        Bullet b;
        ArrayList<Bullet> bullet = Tank.getBullet();

        ArrayList<Integer> SpawnPoints = new ArrayList<>();
        SpawnPoints.add(1400);
        SpawnPoints.add(600);

        Random rand = new Random();

        int xr = SpawnPoints.get(rand.nextInt(SpawnPoints.size()));

        for (int i = 0; i < bullet.size(); i++) {
            b = bullet.get(i);
            if (b.getTank() == tank1) {
                if (b.getBounds().intersects(tank2.getBounds())) {
//                    collisionS.play();
//                    collisionS.reset();
                    bullet.remove(b);
                    if (tank2.getArmor() > 0) {
                        tank2.setArmor(tank2.getArmor() - tank1.getDamage());
                    } else {
                        tank2.setHealth(tank2.getHealth() - tank1.getDamage());
                    }
                }
                if (tank2.getHealth() == 0) {
                    tank2.setLives(tank2.getLives() - 1);
//                    explosionS.play();
//                    explosionS.reset();
                    tank2.setX(1000);
                    tank2.setY(xr);
                    tank2.setHealth(100);
                    tank2.setArmor(0);
                    tank2.setDamage(25);
                }
            }
        }
    }

    public void ArmorPU(Tank tank, ArrayList<ArmorPU> ar) {
        ArmorPU armor;
        Rectangle hitBox = new Rectangle(tank.getBounds());
        for (int i = 0; i < ar.size(); i++) {

            armor = ar.get(i);

            if (hitBox.intersects(armor.getBounds())) {
                ar.remove(armor);
//                pickupS.play();
//                pickupS.reset();
                tank.setArmor(tank.getArmor() + 50);
            }
        }
    }

    public void LivesPU(Tank tank, ArrayList<LivesPU> life) {
        LivesPU extraLife;
        Rectangle hitBox = new Rectangle(tank.getBounds());
        for (int i = 0; i < life.size(); i++) {
            extraLife = life.get(i);

            if (hitBox.intersects(extraLife.getBounds())) {
//                pickupS.play();
//                pickupS.reset();
                life.remove(extraLife);
                tank.setLives(tank.getLives() + 1);
            }
        }
    }

    public void DamagePU(Tank tank, ArrayList<DamagePU> dmg) {
        DamagePU extraDamage;
        Rectangle hitBox = new Rectangle(tank.getBounds());

        for (int i = 0; i < dmg.size(); i++) {
            extraDamage = dmg.get(i);
            if (hitBox.intersects(extraDamage.getBounds())) {
//                pickupS.play();
//                pickupS.reset();
                dmg.remove(extraDamage);
                if (tank.getDamage() < 50) {
                    tank.setDamage(tank.getDamage() + 25);
                }
            }
        }
    }

}


