import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class Music extends Thread {
    @Override
    public void run() {
        try {
            // Cargar el archivo de audio
            File audioFile = new File("./music.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Obtener el clip de audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Reproducir la canción
            clip.start();

            // Esperar hasta que la canción termine
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}