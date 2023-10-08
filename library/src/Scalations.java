import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Scalations extends JFrame implements Runnable {
    private Image fondo;
    private Image buffer;
    private BufferedImage bufferImage;
    private Graphics graPixel;
    Location point1;
    Location point2;
    Location point3;
    Location point4;
    private boolean firstime;
    int incX = 0;
    int incY = 0;
    int maxX = 100;

    public Scalations() {
        setTitle("Escalacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        bufferImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        setVisible(true);
        point1 = new Location(50, 50);
        point2 = new Location(100, 50);
        point3 = new Location(100, 100);
        point4 = new Location(50, 100);
    }

    public int[][] scalation(int incX, int incY, int[][] puntos) {

        int[][] resultado = multiply(new int[][]{
                {incX, 0, 0},
                {0, incY, 0},
                {0, 0, 1}
        }, new int[][]{
                {puntos[0][0], puntos[0][1], 1}
                , {puntos[1][0], puntos[1][1], 1}
                , {puntos[2][0], puntos[2][1], 1}
                , {puntos[3][0], puntos[3][1], 1}
        });
        return resultado;
    }
    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[b.length][a[0].length];
        // se comprueba si las matrices se pueden multiplicar
        if (a[0].length == b[0].length) {
            for (int i=0;i<b.length;i++){
                for(int z=0;z<a.length;z++){
                    int aux=0;
                    for(int j=0;j<b[0].length;j++){
                        aux+=b[i][j]*a[z][j];
                    }
                    c[i][z]=aux;
                }
            }
        }
        return c;
    }
    @Override
    public void run() {
        while (incX<3){
            try {
                incX+=1;
                incY+=1;
                repaint();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("algo salio mal");
            }
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
        if(firstime){
            Bresenham(point1.pointX,point1.pointY,point2.pointX,point2.pointY);
            Bresenham(point2.pointX,point2.pointY,point3.pointX,point3.pointY);
            Bresenham(point3.pointX,point3.pointY,point4.pointX,point4.pointY);
            Bresenham(point4.pointX,point4.pointY,point1.pointX,point1.pointY);
            firstime=false;
        }
        int [][] result= scalation(incX,incY, new int[][] {{point1.pointX,point1.pointY}, {point2.pointX,point2.pointY},
                {point3.pointX,point3.pointY},{point4.pointX,point4.pointY}});
        fill(result[0],result[1],result[3]);
        //fill();
        g.drawImage(buffer,0,0,this);
    }
    public void fill(int [] punto1, int [] punto2, int [] punto3){
        for(int i=punto1[1]; i<=punto3[1];i++){
            Bresenham(punto1[0],punto1[1]+i,punto2[0],punto2[1]+i);
        }

    }
    private void putPixel(int x,int y,Color c){
        bufferImage.setRGB(0,0,c.getRGB());
        this.graPixel.drawImage(bufferImage,x,y,this);
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

            }
        }
    }

    public static void main(String[] args) {
        Scalations scalations = new Scalations();
        scalations.run();
    }

}
