import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class allFigures extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;

    public allFigures(){
        this.g = new Figures();
        setTitle("All Figures");
        setSize(740,517);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        this.lines();
        this.circle();
        this.rectangle();
        this.eclipse();
    }
    private void eclipse(){
        locations = g.elipse(new Location(580,380),25,5);
        paintPoints(Color.BLACK,locations);
       locations = g.elipse(new Location(580,380),40,10);
       paintPoints(Color.BLACK,locations);
        locations = g.elipse(new Location(580,380),55,15);
        paintPoints(Color.BLACK,locations);
        locations = g.elipse(new Location(580,380),65,20);
        paintPoints(Color.BLACK,locations);
    }

    private void rectangle(){
        locations = g.rectangle(new Location(230,315),new Location(400,450));
        paintPoints(Color.BLACK,locations);
        locations = g.rectangle(new Location(260,370),new Location(370,400));
        paintPoints(Color.BLACK,locations);
    }
    private void circle(){
        locations = g.basicCircle(new Location(50,380),new Location(160,380));
        paintPoints(Color.BLACK,locations);
        locations = g.basicCircle(new Location(60,380),new Location(150,380));
        paintPoints(Color.BLACK,locations);
        locations = g.polarCircle(new Location(70,380),new Location(140,380));
        paintPoints(Color.BLACK,locations);
        locations = g.polarCircle(new Location(80,380),new Location(130,380));
        paintPoints(Color.BLACK,locations);
        locations = g.basicCircle(new Location(90,380),new Location(120,380));
        paintPoints(Color.BLACK,locations);
        locations = g.polarCircle(new Location(100,380),new Location(110,380));
        paintPoints(Color.BLACK,locations);
        locations = g.polarCircle(new Location(110,380),new Location(100,380));
        paintPoints(Color.BLACK,locations);

    }

    private void lines(){
        locations = g.lineDDA(new Location(83,52),new Location(150,152));
        paintPoints(Color.BLACK,locations);
        locations = g.Line(new Location(218,100),new Location(336,100));
        paintPoints(Color.BLACK,locations);
        locations = g.bresenham(new Location(484,52),new Location(412,144));
        paintPoints(Color.BLACK,locations);
        locations = g.middlePoint(new Location(500,100),new Location(650,100));
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

    public static void main(String[] args) {
        new allFigures();
    }
}
