package TankWars.GameObject;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Resource {

    private static Map<String, BufferedImage> resources;

    static {
        Resource.resources = new HashMap<>();
        try {
            Resource.resources.put("Tank1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/tank1.png"))));
            Resource.resources.put("Tank2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/tank2.png"))));
            Resource.resources.put("BreakableWall", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/break.png"))));
            Resource.resources.put("UnBreakableWall", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/unbreak.png"))));
            Resource.resources.put("Bullet", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/bullet.png"))));
            Resource.resources.put("Rocket", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/rocket.png"))));
            Resource.resources.put("Damage", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/damage.png"))));
            Resource.resources.put("Armor", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/armor.png"))));
            Resource.resources.put("Lives", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/lives.png"))));
            Resource.resources.put("Grass", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("InGame/grass.png"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getResourceImage(String key) {
        return Resource.resources.get(key);
    }

}