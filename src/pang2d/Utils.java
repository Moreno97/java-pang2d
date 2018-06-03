package pang2d;

import mapanel.Mapcanvas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by Antonio Moreno Valls
 **/
public class Utils {
    public static synchronized void playSound(String pathname) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        new File(pathname));
                clip.open(inputStream);
                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    //Musica Inicio
    /*public static synchronized void playMusicLevel(String pathname) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        new File(pathname));
                clip.open(inputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    public static void closeSound(Clip clip){
        try {
            clip.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/


}
