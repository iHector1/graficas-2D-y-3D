import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class AnimatedGears extends JPanel implements Runnable {

    private float radius = 100;
    private int teeth = 10;
    private float angle = 0;

    public AnimatedGears() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        // Calcula los puntos del polígono del engranaje
        int[] x = new int[teeth];
        int[] y = new int[teeth];
        for (int i = 0; i < teeth; i++) {
            float angle = (float) ((2 * Math.PI * i) / teeth);
            x[i] = (int) Math.round(radius * Math.cos(angle));
            y[i] = (int) Math.round(radius * Math.sin(angle));
        }

        // Escala los puntos del polígono
        for (int i = 0; i < teeth; i++) {
            x[i] = (int) (x[i] * getWidth() / (2 * radius));
            y[i] = (int) (y[i] * getHeight() / (2 * radius));
        }

        // Dibuja el engranaje
        g.drawPolygon(x, y, teeth);
    }

    @Override
    public void run() {
        while (true) {
            // Actualiza el ángulo del engranaje
            angle += 0.01;

            // Si el ángulo es mayor que 2 * Math.PI, lo reinicia
            if (angle > 2 * Math.PI) {
                angle -= 2 * Math.PI;
            }

            // Repaint el panel
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Crea un engranaje
        AnimatedGears gears = new AnimatedGears();

        // Muestra la ventana
        JFrame frame = new JFrame("Animated Gears");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gears);
        frame.setVisible(true);

        // Inicia el hilo de actualización
        new Thread(gears).start();
    }
}
