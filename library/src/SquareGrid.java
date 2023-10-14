
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class SquareGrid extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private BufferedImage offscreenBuffer;  // Buffer adicional
    private Graphics offscreenGraphics;     // Gráfico adicional
    private ArrayList<Location> line;
    private Figures g;
    private int points;
    public int[] verticalConstraints, horizontalConstraints;

    public SquareGrid(int[] verticalConstraints, int[] horizontalConstraints) {
        this.g = new Figures();
        color = Color.RED;
        this.horizontalConstraints = horizontalConstraints;
        this.verticalConstraints = verticalConstraints;
        setTitle("Malla de cuadros");
        setSize(700, 600);
        setLayout(null);
        setVisible(true);
        line = new ArrayList<>();
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);  // Fondo transparente
        graphics = buffer.getGraphics();
        offscreenBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);  // Fondo transparente
        offscreenGraphics = offscreenBuffer.getGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void drawBorder() {
        Location firstCorner = new Location(50, 50);
        Location secondCorner = new Location(300, 300);

        line = g.bresenham(firstCorner, new Location(secondCorner.pointX, firstCorner.pointY));
        drawPoints(Color.RED, line);

        line = g.bresenham(firstCorner, new Location(firstCorner.pointX, secondCorner.pointY));
        drawPoints(Color.RED, line);

        line = g.bresenham(secondCorner, new Location(secondCorner.pointX, firstCorner.pointY));
        drawPoints(Color.RED, line);

        line = g.bresenham(secondCorner, new Location(firstCorner.pointX, secondCorner.pointY));
        drawPoints(Color.RED, line);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        offscreenGraphics.setColor(Color.WHITE);  // Establecer fondo blanco
        offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());  // Rellenar con blanco
        drawBorder();
        drawGrid();
        graphics.drawImage(offscreenBuffer, 0, 0, this);
    }

    private void drawPoints(Color color, ArrayList<Location> points) {
        this.color= color;
        for (Location point : points)
            putPixel(point.pointX, point.pointY);
    }

    private void putPixel(int x, int y) {
        offscreenBuffer.setRGB(x, y, color.getRGB());  // Usar el buffer adicional
    }

    private void drawGrid() {
        for (int i = 0; i < verticalConstraints.length; i++) {
            line = g.bresenham(
                    new Location(verticalConstraints[i], 51),
                    new Location(verticalConstraints[i], 299));
            drawPoints(Color.BLUE, line);
        }

        for (int i = 0; i < horizontalConstraints.length; i++) {
            line = g.bresenham(
                    new Location(51, horizontalConstraints[i]),
                    new Location(299, horizontalConstraints[i]));
            drawPoints(Color.BLUE, line);
        }
    }

    public static void main(String[] args) {
        int[] verticalConstraints = {60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290};
        int[] horizontalConstraints = {60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290};

        // Crear una instancia de la clase Grid
        SquareGrid grid = new SquareGrid(verticalConstraints, horizontalConstraints);
    }
}