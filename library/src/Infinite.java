import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Infinite extends JFrame{

    private Color color;
    private BufferedImage buffer;
    private Graphics graphics;
    private ArrayList<Location> locations;
    private Figures g;
    public Infinite(){
        this.g = new Figures();
        color = Color.BLUE;
        setTitle("Lineas");
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.infitive();
    }


    public void infitive(){
        ArrayList<Location> locations =new ArrayList<>();
        int x,y;
        for (int t = 0; t<=360;t++){
            double radians = Math.PI/180 * t;
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            x = (int) ((100 * sin) / (1 + (cos * cos)));
            y = (int) ((100 * sin * cos) / (1 + (cos * cos)));
            //System.out.println((x+450)+" , "+(y+450));
            this.putPixel(x+200,y+100);
            locations.add(new Location(x+200,y+100));
        }
        this.lines(locations);
    }
    private void putPixel(int x,int y){
        buffer.setRGB(0, 0, color.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public static void main(String[] args) {
        new Infinite();
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

}
