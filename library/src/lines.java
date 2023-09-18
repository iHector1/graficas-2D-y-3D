import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class lines extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    public lines(){
        this.g = new Figures();
        setTitle("Lineas");
        setSize(740,517);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.lineas();
    }
    public void lineas(){
        /*locations = g.bresenham(new Location(100,380),new Location(140,70));
        paintPoints(Color.BLACK,locations);*/
        locations = g.middlePoint(new Location(70,70),new Location(150,380));
        paintPoints(Color.blue,locations);
        /*locations = g.Line(new Location(70,70),new Location(350,70));
        paintPoints(Color.red,locations);
        locations = g.lineDDA(new Location(34,500),new Location(500,100));
        paintPoints(Color.ORANGE,locations);*/
    }
    private void paintPoints(Color color, ArrayList<Location> locations){
        for (Location location:locations) {
            putPixel(location.pointX,location.pointY,color);
        }
    }
    private void putPixel(int x,int y,Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);
    }

    public static void main(String[] args) {
        new lines();
    }
}
