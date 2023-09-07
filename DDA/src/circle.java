import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class circle extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;
    public circle(){
        setTitle("Linea DDA");
        setSize(900,900);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    public static void main(String[] args) {
           new circle();
    }
    public void paint(Graphics g){
        basicCircle(Color.BLACK,new Location(100,100),new Location(50,50));
        middlePointCircle(Color.BLACK,new Location(300,40),new Location(50,50));
        middlePointCircle(Color.cyan,new Location(200,40),new Location(50,50));
    }

    public void basicCircle(Color g,Location pointA , Location pointB){
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;

        for (int x = 0; x <= radius; x++) {
            double y = Math.sqrt(radius * radius - x * x);
            int x1 = centerX + x;
            int x2 = centerX - x;
            int y1 = centerY + (int) Math.round(y);
            int y2 = centerY - (int) Math.round(y);

            // Dibuja los 4 cuadrantes del círculo
            putPixel(x1, y1, g);
            putPixel(x2, y1, g);
            putPixel(x1, y2, g);
            putPixel(x2, y2, g);
        }

    }

    public void polarCircle(Color g,Location pointA , Location pointB){
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;
        for (double theta = 0; theta <= 2 * Math.PI; theta += 0.01) {
            int x = centerX + (int) (radius * Math.cos(theta));
            int y = centerY + (int) (radius * Math.sin(theta));
            putPixel(x, y, g);
        }


    }
    public void middlePointCircle(Color g , Location pointA, Location pointB){
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;
        int x = 0;
        int y = radius;
        int p = 5 / 4 - radius; // Inicializamos p0

        drawCirclePoints(centerX, centerY, x, y, g); // Pintar el primer punto

        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * x - 2 * y + 1;
            }
            drawCirclePoints(centerX, centerY, x, y, g);
        }
    }
    private void drawCirclePoints(int centerX, int centerY, int x, int y, Color g) {
        // Pintar puntos en los 8 octantes usando simetría
        putPixel(centerX + x, centerY + y, g);
        putPixel(centerX - x, centerY + y, g);
        putPixel(centerX + x, centerY - y, g);
        putPixel(centerX - x, centerY - y, g);

        putPixel(centerX + y, centerY + x, g);
        putPixel(centerX - y, centerY + x, g);
        putPixel(centerX + y, centerY - x, g);
        putPixel(centerX - y, centerY - x, g);
    }
    public void putPixel(int x, int y,Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);
    }
}
