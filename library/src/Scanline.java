import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class Scanline extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;
    private Figures figures;
    private BufferedImage bufferImage;
    private Graphics graPixel;
    public ArrayList<String> locations;
    Location point1;
    Location point2;
    Location point3;
    Location point4;


    public Scanline(){
        setTitle("Scannline");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        locations= new ArrayList<>();
        figures = new Figures();
        setVisible(true);
        point1 = new Location(50,50);
        point2 = new Location(100,50);
        point3 = new Location(100,100);
        point4 = new Location(50,100);
    }

    public void fill(){
        fillCircle();  // Llena el círculo
        for(int i= point1.pointY; i<=point3.pointY;i++){
            Bresenham(point1.pointX,point1.pointY+i,point2.pointX,point2.pointY+i);
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
        g.drawImage(buffer,0,0,this);
    }

    private void putPixel(int x,int y,Color c){
        bufferImage.setRGB(0,0,c.getRGB());
        this.graPixel.drawImage(bufferImage,x,y,this);
    }
    @Override
    public void run() {
        repaint();
    }
    public static void main(String[] args) {
       Scanline scanline= new Scanline();
       scanline.show();
       scanline.run();
    }
    public void Bresenham(int x0, int y0, int x1, int y1){
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar */
        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        }
        else
            stepy = 1;
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        }
        else
            stepx = 1;
        x = x0;
        y = y0;
        putPixel(x,y,Color.red);
        locations.add(String.valueOf(x)+","+String.valueOf(y));
        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            p = 2*dy - dx;
            incE = 2*dy;
            incNE = 2*(dy-dx);
            while (x != x1){
                x = x + stepx;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    y = y + stepy;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red);
                locations.add(String.valueOf(x)+","+String.valueOf(y));
            }
        }
        else{
            p = 2*dx - dy;
            incE = 2*dx;
            incNE = 2*(dx-dy);
            while (y != y1){
                y = y + stepy;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    x = x + stepx;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red);
                locations.add(String.valueOf(x)+","+String.valueOf(y));
            }
        }
    }

    public void fillCircle() {
        int radius = 25;  // Radio para obtener un diámetro de 50
        int centerX = 150;
        int centerY = 300;

        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        while (x <= y) {
            // Rellena la línea horizontal
            for (int i = centerX - x; i <= centerX + x; i++) {
                putPixel(i, centerY + y, Color.blue);
                putPixel(i, centerY - y, Color.blue);
            }

            // Rellena la línea vertical
            for (int i = centerX - y; i <= centerX + y; i++) {
                putPixel(i, centerY + x, Color.blue);
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
}
