import javax.swing.*;
import java.awt.*;

public class DDA extends JFrame {

    public static void drawLine(Location location0, Location location1, Graphics g) {
        // Calcula la pendiente de la línea
        float m = (location1.pointY - location0.pointY) / (location1.pointX - location0.pointX);

        // Si la pendiente es mayor que 1, intercambiamos x y y
        if (m > 1) {
            Location temp = location0;
            location0 = location1;
            location1 = temp;
            m = 1 / m;
        }

        // Inicializamos las variables de la iteración
        int x = location0.pointX;
        int y = location0.pointY;
        int dx = location1.pointX - location0.pointX;
        int dy = location1.pointY - location0.pointY;
        int signX = dx > 0 ? 1 : -1;
        int signY = dy > 0 ? 1 : -1;

        // Iteramos sobre la línea
        while (x != location1.pointX) {
            // Dibujamos el pixel actual
            g.drawLine(x, y, x, y);

            // Actualizamos las variables de la iteración
            x += signX;
            y += signY * m;
        }
    }
    public void paint(Graphics g){
        // Creamos una ubicación
        Location location0 = new Location(0, 0);
        Location location1 = new Location(100, 100);

        // Dibujamos una línea
        DDA.drawLine(location0, location1, g);
    }

    public static void main(String[] args) {
        // Creamos una ventana
        Frame frame = new Frame("DDA");
        frame.setSize(600, 400);

        // Mostramos la ventana
        frame.setVisible(true);
    }
}