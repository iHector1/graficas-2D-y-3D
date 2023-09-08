import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class example extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    public example(){
        setTitle("Ejemplo");
        setSize(500,500);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        g = new Figures();
        locations = new ArrayList<>();
        locations = g.Line(new Location(100,100),new Location(100,200));
        paintPoints(Color.cyan,locations);
        locations = g.middlePointCircle(new Location(100,100),new Location(50,50));
        paintPoints(Color.BLACK,locations);

        locations = g.elipse(new Location(200,300),40,15);
        paintPoints(Color.BLACK,locations);
        locations = g.elipse(new Location(200,300),42,20);
        paintPoints(Color.BLACK,locations);
        locations = g.elipse(new Location(200,300),60,50);
        paintPoints(Color.BLACK,locations);
        locations = g.rectangle(new Location(300,100),new Location(400,200));
        paintPoints(Color.BLACK,locations);
        locations = g.rectangle(new Location(250,50),new Location(450,250));
        paintPoints(Color.BLACK,locations);
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
