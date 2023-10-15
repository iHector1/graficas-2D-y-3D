import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Rotation extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;

    private BufferedImage bufferImage;
    private Graphics graPixel;

    Location point1;
    Location point2;
    Location point3;
    Location point4;
    boolean firstime=true;



    int angulo=1;
    int anguloMax=90;


    public Rotation(){
        setTitle("Rotacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(null);
        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        this.setVisible(true);
        point1 = new Location(400,0);
        point2 = new Location(450,0);
        point3 = new Location(450,50);
        point4 = new Location(400,50);
    }


    public void putPixel(int x, int y, Color c) {
        bufferImage.setRGB(0, 0, c.getRGB());
        this.graPixel.drawImage(bufferImage, x, y, this);
    }

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
        int [][] resultado=rotacion(angulo,new int[][]{{point1.pointX,point1.pointY}, {point2.pointX,point2.pointY},
                {point3.pointX,point3.pointY},{point4.pointX,point4.pointY}});
// Fill the figure with pixels at random points
        fillFigure(resultado);

        g.drawImage(buffer,0,0,this);

    }



    public int [][]  rotacion(int angulo, int [][] puntos) {



        int [][] resultado=multiply(new double[][]{
                {Math.cos(Math.toRadians(angulo)),  -Math.sin(Math.toRadians(angulo)),0},
                { Math.sin(Math.toRadians(angulo)),Math.cos(Math.toRadians(angulo)),0},
                {0,0,1}
        },new int[][]{
                {puntos[0][0],puntos[0][1],1}
                ,{puntos[1][0],puntos[1][1],1}
                ,{puntos[2][0],puntos[2][1],1}
                ,{puntos[3][0],puntos[3][1],1}
        });


        return resultado;

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



    public static int[][] multiply(double[][] a, int[][] b) {
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

        //  while (incX<maxX || incY<mayY){
        while (angulo<anguloMax-5){

            try {
                angulo+=2;
                repaint();
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Rotation rotation = new Rotation();
        rotation.run();
    }
    private void fillFigure(int[][] points) {
        int n = points.length;
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;

        // Find the bounding box of the rotated figure
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            startX = Math.min(startX, x);
            startY = Math.min(startY, y);
            endX = Math.max(endX, x);
            endY = Math.max(endY, y);
        }

        int direction = 0; // 0: right, 1: down, 2: left, 3: up
        int x = startX;
        int y = startY;

        while (startX <= endX && startY <= endY) {
            // Paint pixels in the current direction
            while (x >= startX && x <= endX && y >= startY && y <= endY) {
                if (isInsidePolygon(x, y, points)) {
                    putPixel(x, y, Color.red);
                }
                // Move to the next pixel in the current direction
                switch (direction) {
                    case 0:
                        x++;
                        break;
                    case 1:
                        y++;
                        break;
                    case 2:
                        x--;
                        break;
                    case 3:
                        y--;
                        break;
                }
            }

            // Update the bounding box based on the current direction
            switch (direction) {
                case 0:
                    startY++;
                    x--;
                    y++;
                    break;
                case 1:
                    endX--;
                    x--;
                    y--;
                    break;
                case 2:
                    endY--;
                    x++;
                    y--;
                    break;
                case 3:
                    startX++;
                    x++;
                    y++;
                    break;
            }

            // Change direction (right -> down -> left -> up)
            direction = (direction + 1) % 4;
        }
    }

    private boolean isInsidePolygon(int x, int y, int[][] points) {
        int n = points.length;
        boolean isInside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            int xj = points[j][0];
            int yj = points[j][1];

            if (((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                isInside = !isInside;
            }
        }

        return isInside;
    }
}
