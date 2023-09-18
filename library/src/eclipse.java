import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class eclipse extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    public eclipse(){
        this.g = new Figures();
        setTitle("Elipse");
        setSize(740,517);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.elipse();
    }
    public void elipse(){
        /*locations = g.elipse(new Location(340,250),150,200);
        paintPoints(Color.BLACK,locations);*/
        locations = g.elipse(new Location(70,70),34,10);
        paintPoints(Color.blue,locations);
    }

    public static void main(String[] args) {
        new eclipse();
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
}
