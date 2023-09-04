import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Line extends JPanel {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Line(Color g,Location pointA,Location pointB){
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        int dy,dx,steps;
        float x,y,Xincrement,Yincrement;
        dx = pointB.pointX - pointA.pointX;
        dy = pointB.pointY - pointA.pointY;

        if(Math.abs(dy)>Math.abs(dx)){
            steps = Math.abs(dy);
        }else{
            steps = Math.abs(dx);
        }
        Xincrement =dx / steps;
        Yincrement = dy / steps;

        x =pointA.pointX;
        y = pointA.pointY;

        for(int i = 0 ; i<steps ; i++){
            x+=Xincrement;
            y+=Yincrement;
            this.putPixel((int) x,(int) y,g);
        }
    }

    public void putPixel(int x, int y,Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);
    }
}
