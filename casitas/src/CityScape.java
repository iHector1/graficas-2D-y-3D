import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CityScape extends JPanel implements ActionListener {
    private int sunY = 100; // Posición vertical del sol/luna
    private boolean isDay = true; // Estado de día o noche
    private int cloudX = 0; // Posición horizontal de las nubes
    private Timer timer;

    public CityScape() {
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja el fondo del cielo
        if (isDay) {
            g.setColor(new Color(135, 206, 235)); // Cielo azul claro durante el día
        } else {
            g.setColor(new Color(0, 0, 30)); // Cielo oscuro durante la noche
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        // Dibuja el sol o la luna
        g.setColor(Color.YELLOW);
        g.fillOval(getWidth() - 150, sunY, 100, 100);

        // Dibuja nubes en movimiento
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, 50, 80, 40);
        g.fillOval(cloudX + 50, 50, 80, 40);

        // Dibuja edificios con ventanas (simplificado)
        g.setColor(Color.GRAY);
        drawBuilding(g, 50, 200, 100, 300);
        drawBuilding(g, 200, 150, 150, 350);
        drawBuilding(g, 400, 250, 120, 250);
        // Dibuja más edificios...

        // Dibuja la carretera (simplificado)
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 500, getWidth(), 100);
    }

    private void drawBuilding(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
        g.setColor(Color.YELLOW);
        for (int row = 0; row < height / 30; row++) {
            for (int col = 0; col < width / 30; col++) {
                g.fillRect(x + col * 30 + 5, y + row * 30 + 5, 20, 20);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actualiza la posición del sol/luna y las nubes, y cambia entre día y noche
        sunY += (isDay ? 1 : -1);
        cloudX = (cloudX + 2) % getWidth();

        if (sunY > getHeight() || sunY < 0) {
            isDay = !isDay;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CityScape");
        CityScape cityScape = new CityScape();
        frame.add(cityScape);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
