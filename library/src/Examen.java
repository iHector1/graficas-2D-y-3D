import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Examen extends JFrame {
    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;

    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    private Location3D vector;
    private Figures g;
    public Examen(){
        color = Color.red;
        setTitle("Proyección paralela oblicua");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        this.g = new Figures();

        vector = new Location3D(100, 150, 170);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void putPixel(int x, int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void drawCono(){
        float step = (float) (2 * Math.PI) / 100;
        float step2 = 2.5f / 1000; // Asumiendo que 2.5 es el límite superior para j

        for (float j = 0; j <= 2.5; j += step2) {
            for (float u = 0; u <= 2 * Math.PI; u += step) {
                float z = u * u;
                float u2 = -z / vector.pointZ;
                float x = (float) ((Math.cos(u)) * j + (vector.pointX * u2)) * 70;
                float y = (float) ((Math.sin(u)) * j + (vector.pointY * u2)) * 70;
                System.out.println("x: " + x + " y: " + y);
                pointsXY.add(new Location((int) x + 200, (int) y + 300));
            }
        }

        for (Location point : pointsXY) {
            putPixel(point.pointX, point.pointY); // Asumiendo que tienes una función putPixel para dibujar un punto
        }
        this.lines(pointsXY);  // Líneas no parecen ser necesarias para este caso

    }
    private void lines(ArrayList<Location> points){
        for (int i = 0; i < points.size()-1; i++){
            ArrayList<Location> locations = g.bresenham(points.get(i),points.get(i+1));
            drawPoints(locations);
        }
    }
    public void drawPoints(ArrayList<Location> locations){
        for (Location point : locations){
            System.out.println((point.pointX)+" , "+(point.pointY));
            putPixel(point.pointX, point.pointY);
        }
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        drawCono();
    }
    public static void main(String[] args) {
        new Examen();
    }
}
