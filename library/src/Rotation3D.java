import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Rotation3D extends JFrame implements Runnable {
    private Color color, disponible;
    private float incX,incY,incZ;
    private Thread thread;
    private BufferedImage bufferImage;
    private Image buffer;
    private Graphics graphics;
    private Image fondo;
    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    private ArrayList<Location3D> pointsXYZ = new ArrayList<Location3D>();
    private Location3D vector;
    private Figures g;
    private Graphics graPixel;
    private Transformations transformations;
    private int counter;

    public Rotation3D() {
        color = Color.red;
        setTitle("Rotacion 3D");
        setSize(450, 450);
        setLayout(null);
        setVisible(true);

        this.g = new Figures();
        //first square
        pointsXYZ.add(new Location3D(50, 150, 50));//a 0
        pointsXYZ.add(new Location3D(150, 150, 50));//b 1
        pointsXYZ.add(new Location3D(50, 250, 50));//c 2
        pointsXYZ.add(new Location3D(150, 250, 50));//d 3
        //second square
        pointsXYZ.add(new Location3D(50, 150, 150));//e 4
        pointsXYZ.add(new Location3D(150, 150, 150));//f 5
        pointsXYZ.add(new Location3D(50, 250, 150));//g 6
        pointsXYZ.add(new Location3D(150, 250, 150));//h 7
        incX = incY = 1;
        transformations = new Transformations();
        vector = new Location3D(8, 7, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        bufferImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) bufferImage.createGraphics();
    }

    private void putPixel(int x, int y) {
        bufferImage.setRGB(0, 0, color.getRGB());
        graPixel.drawImage(bufferImage, x, y, this);
    }

    private void putPixel(int x, int y, Color color) {
        bufferImage.setRGB(0, 0, color.getRGB());
        graPixel.drawImage(bufferImage, x, y, this);
    }

    private void drawCube() {
        for (int i = 0; i < pointsXYZ.size(); i++) {
            float u = (float) (pointsXYZ.get(i).pointZ) / vector.pointZ;
            float x = pointsXYZ.get(i).pointX + (vector.pointX * u);
            float y = pointsXYZ.get(i).pointY + (vector.pointY * u);

            pointsXY.add(new Location((int) x, (int) y));
        }
        pointsXY = Transformations.CenterRotation(counter, pointsXY, new Location(166, 233));
        for (Location point : pointsXY) {
            putPixel(point.pointX, point.pointY);
        }


        int square[][] = {
                {pointsXY.get(0).pointX, pointsXY.get(0).pointY},
                {pointsXY.get(1).pointX, pointsXY.get(1).pointY},
                {pointsXY.get(5).pointX, pointsXY.get(5).pointY},
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY}
        };
        fillFigure(square, Color.BLUE);
        int square2[][] = {
                {pointsXY.get(0).pointX, pointsXY.get(0).pointY},
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY},
                {pointsXY.get(6).pointX, pointsXY.get(6).pointY},
                {pointsXY.get(2).pointX, pointsXY.get(2).pointY}

        };
        fillFigure(square2, Color.BLACK);
        int square3[][] = {
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY},
                {pointsXY.get(5).pointX, pointsXY.get(5).pointY},
                {pointsXY.get(7).pointX, pointsXY.get(7).pointY},
                {pointsXY.get(6).pointX, pointsXY.get(6).pointY}

        };
        fillFigure(square3, Color.GRAY);
        pointsXY.clear();
    }



    @Override
    public void paint(Graphics graphics) {
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0, getWidth(), getHeight());
        }
        update(graphics);
    }

    public static void main(String[] args) {
        Rotation3D scalation3D = new Rotation3D();
        Thread thread = new Thread(scalation3D);
        thread.start();
    }

    @Override
    public void update(Graphics graphics) {
        graphics.setClip(0, 0, getWidth(), getHeight());
        buffer = createImage(getWidth(), getHeight());
        graPixel = buffer.getGraphics();
        graPixel.setClip(0, 0, getWidth(), getHeight());
        this.drawCube();
        System.out.println("x: "+incX+" y:"+incY);
        graphics.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void run() {
        while (true){
            try{
                repaint();
                thread.sleep(10);
                if (++counter == 360) counter = 0;
            }
            catch (InterruptedException e) {}
        }
    }

    public void fillFigure(int[][] square, Color color) {
        int minY = getMinY(square);
        int maxY = getMaxY(square);


        for (int y = minY; y <= maxY; y++) {

            int[] intersections = findIntersections(square, y);
            for (int i = 0; i < intersections.length - 1; i += 2) {
                int startX = intersections[i];
                int endX = intersections[i + 1];

                for (int x = startX; x <= endX; x++) {
                    putPixel(x, y, color);
                }
            }
        }
    }
    private int getMinY(int[][] square) {
        int minY = Integer.MAX_VALUE;
        for (int[] point : square) {
            minY = Math.min(minY, point[1]);
        }
        return minY;
    }

    private int getMaxY(int[][] square) {
        int maxY = Integer.MIN_VALUE;
        for (int[] point : square) {
            maxY = Math.max(maxY, point[1]);
        }
        return maxY;
    }

    private int[] findIntersections(int[][] square, int y) {

        int[] intersections = new int[square.length * 2];
        int index = 0;
        for (int i = 0; i < square.length; i++) {
            int nextIndex = (i == square.length - 1) ? 0 : i + 1;

            int x1 = square[i][0];
            int y1 = square[i][1];
            int x2 = square[nextIndex][0];
            int y2 = square[nextIndex][1];

            if ((y1 <= y && y2 > y) || (y1 > y && y2 <= y)) {
                double intersectX = ((double)(y - y1) / (y2 - y1)) * (x2 - x1) + x1;
                intersections[index++] = (int) intersectX;
            }
        }
        Arrays.sort(intersections, 0, index);
        return Arrays.copyOf(intersections, index);
    }

}