import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CutOut extends JFrame {
    private Image fondo;
    private Image buffer;
    private BufferedImage bufferImage;
    private Graphics graPixel;
    Location point1;
    Location point4;
    private List<Circle> circles = new ArrayList<>();

    public CutOut() {
        setTitle("Recorte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        bufferImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        setVisible(true);
        point1 = new Location(50, 50);
        point4 = new Location(300, 400);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (isInsideSquare(mouseX, mouseY)) {
                    // Agregar el círculo a la lista
                    circles.add(new Circle(mouseX - 10, mouseY - 10));
                    repaint();
                }
            }
        });
    }

    private boolean isInsideSquare(int x, int y) {
        return x >= point1.pointX && x <= point4.pointX
                && y >= point1.pointY && y <= point4.pointY;
    }

    private void fillCircle(int x1, int y1, int x2, int y2, int circleX, int circleY) {
        int radius = 70; // Radio para obtener un diámetro de 34

        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        while (x <= y) {
            for (int i = circleX - x; i <= circleX + x; i++) {
                if (i >= x1 && i <= x2 && circleY + y >= y1 && circleY + y <= y2)
                    putPixel(i, circleY + y, Color.blue);
                if (i >= x1 && i <= x2 && circleY - y >= y1 && circleY - y <= y2)
                    putPixel(i, circleY - y, Color.blue);
            }

            // Rellena la línea vertical
            for (int i = circleX - y; i <= circleX + y; i++) {
                if (i >= x1 && i <= x2 && circleY + x >= y1 && circleY + x <= y2)
                    putPixel(i, circleY + x, Color.blue);
                if (i >= x1 && i <= x2 && circleY - x >= y1 && circleY - x <= y2)
                    putPixel(i, circleY - x, Color.blue);
            }

            if (p < 0) {
                p += 4 * x + 6;
            } else {
                p += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0, getWidth(), getHeight());
        }
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.setClip(0, 0, getWidth(), getHeight());
        buffer = createImage(getWidth(), getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0, 0, getWidth(), getHeight());
        gbuffer.drawImage(fondo, 0, 0, this);
        this.graPixel = gbuffer;
        fill();

        // Pintar todos los círculos
        for (Circle circle : circles) {
            fillCircle(point1.pointX, point1.pointY, point4.pointX, point4.pointY, circle.x, circle.y);
        }

        g.drawImage(buffer, 0, 0, this);
    }

    private void fill() {
        for (int y = point1.pointY; y < point4.pointY + 1; y++)
            for (int x = point1.pointX; x <= point4.pointX + 1; x++)
                putPixel(x, y, Color.red);
    }

    private void putPixel(int x, int y, Color c) {
        bufferImage.setRGB(0, 0, c.getRGB());
        this.graPixel.drawImage(bufferImage, x, y, this);
    }

    public static void main(String[] args) {
        CutOut cutOut = new CutOut();
        cutOut.setVisible(true);
    }
}
























class Circle {
    int x;
    int y;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }
}