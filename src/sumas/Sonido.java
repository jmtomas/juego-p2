/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumas;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Sonido(String nombre) {
        URL url = this.getClass().getResource("sonidos/" + nombre + ".wav");
        try (AudioInputStream as = AudioSystem.getAudioInputStream(url)) {
            DataLine.Info info = new DataLine.Info(Clip.class, as.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(as);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(FrameTablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
