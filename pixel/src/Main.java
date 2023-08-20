import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public static void main(String[] args) {
        new Main();
    }
    public Main(){
        setTitle("Ventana");
        setSize(500,500);
        setLayout(null);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void putPixel(int x, int y,Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);
    }
    public void paint(Graphics g){
        super.paint(g);
        putPixel(100,100,g.getColor());
    }
}