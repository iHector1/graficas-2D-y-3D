import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class RelojAnalogico extends JPanel {
    private BufferedImage backgroundImage;
    private Timer timer;

    public RelojAnalogico() {
        try {
            backgroundImage = ImageIO.read(new File("img/jedi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1000); // Actualiza cada segundo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Resto del código para dibujar el reloj...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Reloj Analógico");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new RelojAnalogico());
            frame.setVisible(true);
        });
    }
}
