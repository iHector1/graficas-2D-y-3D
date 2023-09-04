import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;
    public Screen(){
        setTitle("Linea DDA");
        setSize(900,900);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }


    public void paint(Graphics g) {
        line(Color.cyan,new Location(100,100),new Location(100,600));
        lineDDA(Color.RED,new Location(800,200),new Location(100,600));
        bresenham(Color.BLACK,new Location(200,600),new Location(800,200));

//        g.drawLine(100,600,800,100);

    }
    public void line(Color g, Location pointA, Location pointB) {
        int dx = pointB.pointX - pointA.pointX;
        int dy = pointB.pointY - pointA.pointY;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float Xincrement = (float) dx / steps;
        float Yincrement = (float) dy / steps;

        float x = pointA.pointX;
        float y = pointA.pointY;

        for (int i = 0; i < steps; i++) {
            x += Xincrement;
            y += Yincrement;
            this.putPixel((int) x, (int) y, g);
        }
    }
    public void lineDDA(Color g,Location pointA,Location pointB){
        int dx = pointB.pointX - pointA.pointX;
        int dy = pointB.pointY - pointA.pointY;
  
        if (Math.abs(dx) > Math.abs(dy)) { //pendiente < 1
            float m = (float) dy / (float) dx;
            float b = pointA.pointY - m*pointA.pointX;
            if(dx<0)
                dx = -1;
            else
                dx = 1;
            while (pointA.pointX != pointB.pointX) {
                pointA.pointX += dx;
                pointA.pointY = Math.round(m*pointA.pointX + b);
                this.putPixel(pointA.pointX, pointA.pointY, g);
            }
        } else
        if (dy != 0) { // pendiente>= 1
            float m = (float) dx / (float) dy; //compute slope
            float b = pointA.pointX - m*pointA.pointY;
            if(dy<0)
                dy = -1;
            else
                dy = 1;
            while (pointA.pointY != pointB.pointY) {
                pointA.pointY += dy;
                pointA.pointX = Math.round(m*pointA.pointY + b);
                this.putPixel(pointA.pointX, pointA.pointY, g);
            }
        }
    }
    public void bresenham(Color g, Location pointA,Location pointB){
        int x, y, dx, dy, p, incE, incNE, stepx, stepy,x0,y0,x1,y1;

        x0=pointA.pointX;
        y0=pointA.pointY;
        x1= pointB.pointX;
        y1 = pointB.pointY;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar
         */
        if (dy < 0) {
            dy = -dy; stepy = -1;
        }
        else
            stepy = 1;
        if (dx < 0) {
            dx = -dx; stepx = -1;
        }
        else
            stepx = 1;
        x = x0;
        y = y0;
        putPixel(x0,y0,g);
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
                putPixel(x,y,g);
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
                putPixel(x,y,g);

            }
        }
    }
    public void putPixel(int x, int y,Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);
    }
}
