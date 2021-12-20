package TankWars.GameObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

//This class was taken from the "Airstrike" game provided by the professor.

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String soundFile) {
        try {
            AudioInputStream soundStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundPlayer.class.getClassLoader().getResource(soundFile)));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "No sound documents are fouond");
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
