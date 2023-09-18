import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class circulos extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    public circulos(){
        this.g = new Figures();
        setTitle("Ciruclos");
        setSize(740,517);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.cirucle();
    }
    public void cirucle(){
        locations = g.basicCircle(new Location(100,380),new Location(150,380));
        paintPoints(Color.BLACK,locations);
        locations = g.polarCircle(new Location(70,70),new Location(140,70));
        paintPoints(Color.blue,locations);
        locations = g.middlePointCircle(new Location(340,400),new Location(400,400));
        paintPoints(Color.red,locations);
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
        new circulos();
    }
}
