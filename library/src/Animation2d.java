import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation2d extends JFrame implements Runnable {

    private BufferedImage bufferImage;
    private Image buffer;

    private int bodyX,bodyY;

    private Image fondo;
    private Graphics graPixel;
    private ArrayList<Location> locations;
    private Figures g;
    private Transformations transformations;
    private int translateX; // Traslación en X
    private int  translateY; // Traslación en Y
    public Animation2d() {
        this.g = new Figures();
        setTitle("Animacion");
        setSize(700, 650);
        setLayout(null);
        setVisible(true);
        this.transformations = new Transformations();
        setLocationRelativeTo(null);
        bufferImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
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

    private void scene1(){
        this.backgroudScene1();
        this.sceneAnimation1();
    }
    private void backgroudScene1(){
        //
        int[][] squarePoints = {
                {0, 0},//izq arribs
                {700, 0},//der arri
                {700, 420},//der abajo
                {0, 420}//izq abajo
        };
        this.fillFigure(squarePoints,new Color(20,129,186));
        int[][] squarePoints2 = {
                {0, 420},//izq arriba
                {700, 420},
                {700, 650},
                {0, 650}
        };
        this.fillFigure(squarePoints2,new Color(150, 61, 0));
        //sun
        this.sunScene1();

        //mountains

        this.locations = g.triangle(new Location(0,420),new Location(190,100)
                ,new Location(370,420));
        this.pointsLocations(locations,new Color(81, 60, 44));
        this.locations = g.triangle(new Location(371,420),new Location(540,100)
                ,new Location(700,420));
        this.pointsLocations(locations,new Color(81, 60, 44));


    }
    @Override
    public void update(Graphics graphics) {
        graphics.setClip(0, 0, getWidth(), getHeight());
        buffer = createImage(getWidth(), getHeight());
        graPixel = buffer.getGraphics();
        graPixel.setClip(0, 0, getWidth(), getHeight());
        this.scene1();
        graphics.drawImage(buffer, 0, 0, this);
    }
    private void pointsLocations(ArrayList<Location> locations,Color color){
        int[][] trianglePoints = new int[locations.size()][2];
        for (int i = 0; i < locations.size(); i++) {
            trianglePoints[i][0] = locations.get(i).pointX;
            trianglePoints[i][1] = locations.get(i).pointY;
        }
        fillFigure(trianglePoints,color);
    }
    private void paintPoints(Color color, ArrayList<Location> locations) {
        for (Location location : locations)
            putPixel(location.pointX, location.pointY, color);
    }

    private void fillFigure(int[][] points,Color color) {
        int n = points.length;
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;

        // Find the bounding box of the rotated figure
        for (int i = 0; i < n-1; i++) {
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
                    putPixel(x, y, color);
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

    private void putPixel(int x, int y, Color color) {
        bufferImage.setRGB(0, 0, color.getRGB());
        graPixel.drawImage(bufferImage, x, y, this);
    }

    private void sunScene1(){
        int centerX = 608;
        int centerY = 90;
        int radius = 50;
        int numPoints = 360;
        int[][] circlePoints = new int[numPoints][2];

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        fillFigure(circlePoints,new Color(246, 174, 45));
    }

    private void topCone(){
        this.locations.clear();
        Location location1 = new Location(352,300);
        Location location2 = new Location(300,360);
        Location location3 = new Location(410,360);
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(0,bodyY,locations);


        this.pointsLocations(this.locations,new Color(192, 50, 33));
    }

    private void rocketExhaust(){
        this.locations.clear();
        this.locations.add(new Location(310,570));
        this.locations.add(new Location(340,570));
        this.locations.add(new Location(340,620));
        this.locations.add(new Location(310,620));
        this.locations = transformations.translation(0,bodyY,locations);
        int[][] square = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square,new Color(0,0,0));
        this.locations.clear();
        this.locations.add(new Location(370,570));
        this.locations.add(new Location(400,570));
        this.locations.add(new Location(400,620));
        this.locations.add(new Location(370,620));
        this.locations = transformations.translation(0,bodyY,locations);
        int[][] square2 = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square2,new Color(0,0,0));
        window();
    }

    private void squareBody(){
        this.locations.clear();
        this.locations.add(new Location(300,360));
        this.locations.add(new Location(300,570));
        this.locations.add(new Location(410,570));
        this.locations.add(new Location(410,360));

        this.locations = transformations.translation(0,bodyY,locations);
        int[][] square = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square,new Color(255,255,255));
    }
    private void window(){
        int centerX = 355;
        int centerY = 433;
        int radius = 35;
        int numPoints = 360;
        int[][] circlePoints = new int[numPoints][2];

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }

        // Aplica la traslación a los puntos del círculo
        for (int i = 0; i < numPoints; i++) {
            circlePoints[i][1] += translateY;
        }

        fillFigure(circlePoints, new Color(134, 187, 216));

        // Repite el proceso para los otros tres círculos
        centerX = 312;
        centerY = 450;
        radius = 7;
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        for (int i = 0; i < numPoints; i++) {
            circlePoints[i][1] += translateY;
        }
        fillFigure(circlePoints, new Color(89, 89, 89));

        // Repite el proceso para los otros dos círculos
        centerX = 353;
        centerY = 480;
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        for (int i = 0; i < numPoints; i++) {
            circlePoints[i][1] += translateY;
        }
        fillFigure(circlePoints, new Color(89, 89, 89));

        centerX = 400;
        centerY = 450;
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        for (int i = 0; i < numPoints; i++) {
            circlePoints[i][1] += translateY;
        }
        fillFigure(circlePoints, new Color(89, 89, 89));
    }

    private void rocketWings(){
        this.locations.clear();
        Location location1 = new Location(410,506);
        Location location2 = new Location(410,570);
        Location location3 = new Location(449,570);
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(0,bodyY,locations);
        this.pointsLocations(locations,new Color(192, 50, 33));

        this.locations.clear();
        location1 = new Location(300,506);
        location2 = new Location(300,570);
        location3 = new Location(263,570);
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(0,bodyY,locations);
        this.pointsLocations(this.locations,new Color(192, 50, 33));
    }
    private void sceneAnimation1(){
        bodyX+=10;
        bodyY-=10;
        translateX += 10; // Traslación en X
        translateY -= 10; // Traslación en Y
        this.topCone();
        this.squareBody();
        this.rocketExhaust();
        this.rocketWings();
    }
    public static void main(String[] args) {
        Animation2d animation2d = new Animation2d();
        Thread thread = new Thread(animation2d);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }
}