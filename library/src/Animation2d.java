import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Animation2d extends JFrame implements Runnable {

    private int times;
    private float scalation;
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
    private float counter;
    private int size;
    private boolean endSize;
    private int radius;
    private  int radius2;
    private boolean endClouds;
    private boolean endScene;
    private int miniroket;

    public Animation2d() {
        this.g = new Figures();
        setTitle("Animacion");
        setSize(700, 650);
        setLayout(null);
        setVisible(true);
        this.transformations = new Transformations();
        this.locations = new ArrayList<>();
        setLocationRelativeTo(null);
        counter=1;
        size = 600;
        this.radius = 4;
        this.radius2 = 1;
        this.endSize = false;
        this.endClouds = false;
        endScene = false;
        this.bodyY=-10;
        this.times=3;
        this.miniroket=1;
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
            if(!this.endScene){
                this.backgroudScene1();
            }
            this.sceneAnimation1();
    }
    private void backgroudScene1(){
        //
        ArrayList<Location> location= new ArrayList<>();
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

        location = g.triangle(new Location(0,420),new Location(190,100)
                ,new Location(370,420));
        this.pointsLocations(location,new Color(81, 60, 44));
        location = g.triangle(new Location(371,420),new Location(540,100)
                ,new Location(700,420));
        this.pointsLocations(location,new Color(81, 60, 44));


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
            circlePoints[i][1] += bodyY;
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
            circlePoints[i][1] += bodyY;
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
            circlePoints[i][1] += bodyY;
        }
        fillFigure(circlePoints, new Color(89, 89, 89));
        this.infitive(centerX,centerY);
        centerX = 400;
        centerY = 450;
        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        for (int i = 0; i < numPoints; i++) {
            circlePoints[i][1] += bodyY;
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
        System.out.println(this.endScene);
        if(this.times<=0){
            bodyY-=10;
            translateY -= 10;
        }
        scalation+=2;
        //System.out.println(bodyY);
        //this.space();
        if(bodyY>-530){
            this.topCone();
            this.squareBody();
            this.rocketExhaust();
            this.rocketWings();
            this.cactus1();
            this.cactus2();
            this.lineClouds();
        }else{
            this.endScene1();
        }
        if(this.endScene){
            this.scene2();
        }

    }

    private void space(){
        this.locations.clear();
        this.locations = g.rhombus(new Location(50,50),new Location(100,100));
        this.locations = transformations.CenterRotation(translateY,locations,new Location(75,75));
        this.pointsLocations(this.locations,new Color(255,255,255));
        this.locations.clear();
        this.locations = g.rhombus(new Location(50,50),new Location(100,100));
        this.locations = transformations.CenterRotation(translateY*-1,locations,new Location(75,75));
        this.pointsLocations(this.locations,new Color(255,255,255));
        this.locations.clear();
        this.locations = g.rhombus(new Location(400,10),new Location(420,30));
        this.locations = transformations.CenterRotation(translateY,locations,new Location(314,200));
        this.pointsLocations(this.locations,new Color(70, 73, 76));
        this.locations = g.rhombus(new Location(100,10),new Location(120,30));
        this.locations = transformations.CenterRotation(translateY,locations,new Location(314,200));
        this.pointsLocations(this.locations,new Color(70, 73, 76));
        translateY++;
        this.locations.clear();
        this.locations = g.elipse(new Location(314,200),100,100);
        this.drawPoints(this.locations,new Color(70, 73, 76));
        System.out.println(translateY);
        int min=0;
        int max1 = 400;
        int max = 700;
        for(int i = 0 ; i<50;i++){
            putPixel((int) (Math.random() * (max - min + 1)) + min,(int) (Math.random() * (max1 - min + 1)) + min,Color.white);
        }
        this.planet();
        this.miniRocket();
        this.miniroket++;
        try {
            sleep(35);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void planet(){
        int centerX = 314;
        int centerY = 200;
        int radius = 80;
        int numPoints = 360;
        int[][] circlePoints = new int[numPoints][2];

        for (int i = 0; i < numPoints; i++) {
            double angle = 2 * Math.PI * i / numPoints;
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY + radius * Math.sin(angle));
            circlePoints[i] = new int[]{x, y};
        }
        fillFigure(circlePoints,new Color(86, 40, 45));
    }
    private void sizes(){
        if(size>400){
            size-=10;
            System.out.println(size);
            setSize(getWidth(),size);
        }else{
            this.endSize=true;
        }
    }

    private void scene2(){
        this.scanLine();
        this.space();
    }
    private void endScene1(){
        if(this.endSize){
            if(counter<150){
                ArrayList<Location> newPoint = new ArrayList<Location>();
                newPoint.add(new Location(5, getHeight()));
                counter+=5;
                newPoint = transformations.Escalation(counter, 1, newPoint);

                ArrayList<Location> result = new ArrayList<Location>();
                result.add(new Location(5, 5));
                result.add(new Location(5, getHeight()));
                result.add(newPoint.get(0));
                result.add(new Location(newPoint.get(0).pointX,0));
                System.out.println(this.counter);
                drawPolygon(result, Color.BLACK);
                this.pointsLocations(result,Color.BLACK);
                //Rellenar(2, 2, Color.BLACK,Color.BLACK);

            }else{
                this.endScene =true;
            }
        }else{
            this.sizes();
        }


    }
    void drawPolygon(ArrayList<Location> points, Color fillColor){
        ArrayList<Location> newPoints = new ArrayList<Location>();
        for (int i = 0; i < points.size(); i++){
            ArrayList<Location> line = (i == points.size() - 1) ?
                    g.bresenham(points.get(i), points.get(0)):
                    g.bresenham(points.get(i), points.get(i+1));
            newPoints.addAll(line);
        }

        for (int i = 0; i < newPoints.size(); i++)
        {
            putPixel(newPoints.get(i).pointX, newPoints.get(i).pointY, fillColor);
        }
    }
    private void lineClouds(){
        if(!this.endClouds){
            translateX+=10;
            // Crear los puntos iniciales de los círculos
            ArrayList<Location> circle1Points;
            Location circle2Center = new Location(391, 634);
            this.locations.clear();
            this.locations = generateEllipsePoints(350,600,radius,radius2);
            circle1Points = new Transformations().translation(translateX*-1, 1, this.locations);
            pointsLocations(circle1Points,Color.white);
            this.locations.clear();
            this.locations = generateEllipsePoints(323,600,radius,radius2);
            circle1Points = new Transformations().translation(translateX*-1, 1, this.locations);
            pointsLocations(circle1Points,Color.white);
            this.locations.clear();
            this.locations = generateEllipsePoints(391,600,radius,radius2);
            circle1Points = new Transformations().translation(translateX, 1, this.locations);
            pointsLocations(circle1Points,Color.white);
            this.locations.clear();
            this.locations = generateEllipsePoints(364,600,radius,radius2);
            circle1Points = new Transformations().translation(translateX, 1, this.locations);
            pointsLocations(circle1Points,Color.white);

            if(radius<=24 && radius2<=21){
                radius+=2;
                radius2+=2;
            }
            if(translateX>=150){
                translateX=1;
                this.times-=1;
            }
            if(this.times<=0){
                this.endClouds=true;
            }
        }


    }
    public ArrayList<Location> generateEllipsePoints(int centerX, int centerY, int radiusX, int radiusY) {
        ArrayList<Location> ellipsePoints = new ArrayList<>();

        for (int i = 0; i < 360; i++) {
            double angle = 2 * Math.PI * i / 360;
            int x = (int) (centerX + radiusX * Math.cos(angle));
            int y = (int) (centerY + radiusY * Math.sin(angle));
            ellipsePoints.add(new Location(x, y));
        }

        return ellipsePoints;
    }

    private void cactus1(){
        this.locations.clear();
        this.locations.add(new Location(165, 403));
        this.locations.add(new Location(187, 403));
        this.locations.add(new Location(187, 465));
        this.locations.add(new Location(165, 465));
        int[][] square = {
                {this.locations.get(0).pointX, this.locations.get(0).pointY},
                {this.locations.get(1).pointX, this.locations.get(1).pointY},
                {this.locations.get(2).pointX, this.locations.get(2).pointY},
                {this.locations.get(3).pointX, this.locations.get(3).pointY}
        };
        fillFigure(square, new Color(0, 129, 72));

        this.locations.clear();
        this.locations.add(new Location(143, 424));
        this.locations.add(new Location(210, 424));
        this.locations.add(new Location(210, 442));
        this.locations.add(new Location(143, 442));
        int[][] square2 = {
                {this.locations.get(0).pointX, this.locations.get(0).pointY},
                {this.locations.get(1).pointX, this.locations.get(1).pointY},
                {this.locations.get(2).pointX, this.locations.get(2).pointY},
                {this.locations.get(3).pointX, this.locations.get(3).pointY}
        };
        fillFigure(square2, new Color(0, 129, 72));

        this.locations.clear();
        this.locations.add(new Location(143, 415));
        this.locations.add(new Location(153, 415));
        this.locations.add(new Location(153, 424));
        this.locations.add(new Location(143, 424));
        int[][] square3 = {
                {this.locations.get(0).pointX, this.locations.get(0).pointY},
                {this.locations.get(1).pointX, this.locations.get(1).pointY},
                {this.locations.get(2).pointX, this.locations.get(2).pointY},
                {this.locations.get(3).pointX, this.locations.get(3).pointY}
        };
        fillFigure(square3, new Color(0, 129, 72));

        this.locations.clear();
        this.locations.add(new Location(200, 415));
        this.locations.add(new Location(210, 415));
        this.locations.add(new Location(210, 424));
        this.locations.add(new Location(200, 424));
        int[][] square4 = {
                {this.locations.get(0).pointX, this.locations.get(0).pointY},
                {this.locations.get(1).pointX, this.locations.get(1).pointY},
                {this.locations.get(2).pointX, this.locations.get(2).pointY},
                {this.locations.get(3).pointX, this.locations.get(3).pointY}
        };
        fillFigure(square4, new Color(0, 129, 72));

    }

    private void cactus2(){
        this.locations.clear();
        this.locations.add(new Location(565,403));
        this.locations.add(new Location(587, 403));
        this.locations.add(new Location(587, 465));
        this.locations.add(new Location(565, 465));
        int[][] square = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square,new Color(0, 129, 72));
        this.locations.clear();
        this.locations.add(new Location(543,424));
        this.locations.add(new Location(610,424));
        this.locations.add(new Location(610,442));
        this.locations.add(new Location(543,442));
        int[][] square2 = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square2,new Color(0, 129, 72));
        this.locations.clear();
        this.locations.add(new Location(543,415));
        this.locations.add(new Location(553,415));
        this.locations.add(new Location(553,424));
        this.locations.add(new Location(543,424));
        int[][] square3 = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square3,new Color(0, 129, 72));
        this.locations.clear();
        this.locations.add(new Location(600,415));
        this.locations.add(new Location(610,415));
        this.locations.add(new Location(610,424));
        this.locations.add(new Location(600,424));
        int[][] square4 = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square4,new Color(0, 129, 72));
    }
    public static void main(String[] args) {
        Animation2d animation2d = new Animation2d();
        Thread thread = new Thread(animation2d);
        thread.start();
        /*Music musicPlayer = new Music();

        // Iniciar el hilo de la música
        musicPlayer.start();*/
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            if(this.miniroket>200){
                break;
            }
        }
    }

    private void miniRocket(){
        this.locations.clear();
        this.locations.add(new Location(380, 230));
        this.locations.add(new Location(432, 230));
        this.locations.add(new Location(432, 253));
        this.locations.add(new Location(380, 253));

        this.locations = transformations.translation(miniroket,1,locations);
        int[][] square = {
                {this.locations.get(0).pointX,this.locations.get(0).pointY},//izq arriba
                {this.locations.get(1).pointX,this.locations.get(1).pointY},
                {this.locations.get(2).pointX,this.locations.get(2).pointY},
                {this.locations.get(3).pointX,this.locations.get(3).pointY}
        };
        fillFigure(square,new Color(255,255,255));
        this.locations.clear();
        Location location1 = new Location(380,209);
        Location location2 = new Location(380,230);
        Location location3 = new Location(400,230);
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(miniroket,0,locations);
        this.pointsLocations(this.locations,new Color(192, 50, 33));

        this.locations.clear();
         location1 = new Location(380,255);
         location2 = new Location(380,273);
         location3 = new Location(401,255);
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(miniroket,0,locations);
        this.pointsLocations(this.locations,new Color(192, 50, 33));

        location1 = new Location(452,240);
        location2 = new Location(432,228);
        location3 = new Location(432,253);
        this.locations.clear();
        this.locations.add(location1);
        this.locations.add(location2);
        this.locations.add(location3);
        this.locations = g.triangle(new Location(locations.get(0).pointX,locations.get(0).pointY),
                new Location(locations.get(1).pointX,locations.get(1).pointY),
                new Location(locations.get(2).pointX,locations.get(2).pointY));
        this.locations= transformations.translation(miniroket,0,locations);
        this.pointsLocations(this.locations,new Color(192, 50, 33));

    }
    public void infitive(int centerX,int centerY){
        ArrayList<Location> locations =new ArrayList<>();
        int x,y;
        for (int t = 0; t<=360;t++){
            double radians = Math.PI/180 * t;
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            x = (int) ((30 * sin) / (1 + (cos * cos)));
            y = (int) ((30 * sin * cos) / (1 + (cos * cos)));
            //System.out.println((x+450)+" , "+(y+450));
            this.putPixel(x+centerX,y+centerY+this.bodyY+50,Color.blue);
            locations.add(new Location(x+centerX,y+centerY+this.bodyY+50));
        }
        this.lines(locations);
    }
    private void lines(ArrayList<Location> points){
        for (int i = 0; i < points.size()-1; i++){
            ArrayList<Location> locations = g.bresenham(points.get(i),points.get(i+1));
            drawPoints(locations,Color.BLUE);
        }
    }
    public void drawPoints(ArrayList<Location> locations,Color color) {
        for (Location point : locations) {
            putPixel(point.pointX, point.pointY,color);
        }
    }
    private void scanLine(){
        for (int y = 0; y < getHeight() - 1; y++)
            for (int x = 0;x <= getWidth() - 1; x++)
                putPixel(x, y, Color.BLACK);
    }
}