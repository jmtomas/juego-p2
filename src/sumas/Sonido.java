/* Juan Manuel Tomás - 232063 */
package sumas;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author juan
 */
public class Sonido {

    public static void reproducir(String nombre) {
        URL url = Sonido.class.getResource("sonidos/" + nombre + ".wav");
        try (AudioInputStream inputStream = AudioSystem.getAudioInputStream(url)) {
            DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }
}
