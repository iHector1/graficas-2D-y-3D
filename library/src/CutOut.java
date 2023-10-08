import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class CutOut extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;
    private BufferedImage bufferImage;
    private Graphics graPixel;
    Location point1;
    Location point4;
    int incX=0;
    int incY=0;
    int maxX=100;
    public CutOut (){
        setTitle("Recorte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        setVisible(true);
        point1 = new Location(50,50);
        point4 = new Location(240,300);
    }
    @Override
    public void run() {

                repaint();
    }
    private void fillCircle(int x1, int y1, int x2, int y2) {
        int radius = 25;  // Radio para obtener un diámetro de 50
        int centerX = 150;
        int centerY = 300;

        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        while (x <= y) {
            for (int i = centerX - x; i <= centerX + x; i++) {
                if (i >= point1.pointX && i <= point4.pointX && centerY + y >= point1.pointY && centerY + y <= point4.pointY)
                    putPixel(i, centerY + y, Color.blue);
                if (i >= point1.pointX && i <= point4.pointX && centerY - y >= point1.pointY && centerY - y <= point4.pointY)
                    putPixel(i, centerY - y, Color.blue);
            }

            // Rellena la línea vertical
            for (int i = centerX - y; i <= centerX + y; i++) {
                if (i >= point1.pointX && i <= point4.pointX && centerY + x >= point1.pointY && centerY + x <= point4.pointY)
                    putPixel(i, centerY + x, Color.blue);
                if (i >= point1.pointX && i <= point4.pointX && centerY - x >= point1.pointY && centerY - x <= point4.pointY)
                    putPixel(i, centerY - x, Color.blue);
            }

            if (p < 0) {
                p += 4 * x + 6;
            } else {
                p += 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }
    @Override
    public void paint (Graphics g) {
        if(fondo==null){
            fondo=createImage(getWidth(),getHeight());
            Graphics gfondo=fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
        }
        update(g);
    }
    @Override
    public void update(Graphics g) {
        g.setClip(0,0,getWidth(),getHeight());
        buffer=createImage(getWidth(),getHeight());
        Graphics gbufer=buffer.getGraphics();
        gbufer.setClip(0,0,getWidth(),getHeight());
        gbufer.drawImage(fondo,0,0,this);
        this.graPixel = gbufer;
        fill();
        fillCircle(point1.pointX, point1.pointY, point4.pointX, point4.pointY);
        g.drawImage(buffer,0,0,this);
    }
    private void fill(){
        for (int y = point1.pointY; y < point4.pointY +1; y++)
            for (int x = point1.pointX; x <= point4.pointX+1; x++)
                putPixel(x, y,Color.red);
    }

    private void putPixel(int x,int y,Color c){
        bufferImage.setRGB(0,0,c.getRGB());
        this.graPixel.drawImage(bufferImage,x,y,this);
    }
    public static void main(String[] args) {
        CutOut cutOut = new CutOut();
        cutOut.run();

    }
}
