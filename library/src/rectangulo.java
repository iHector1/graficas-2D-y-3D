import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class rectangulo extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    public rectangulo(){
        this.g = new Figures();
        setTitle("rectangulo");
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
       /* locations = g.rectangle(new Location(100,100),new Location(570,500));
        paintPoints(Color.BLACK,locations);*/
        locations = g.rectangle(new Location(120,320),new Location(300,120));
        paintPoints(Color.BLUE,locations);
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
        new rectangulo();
    }
}
