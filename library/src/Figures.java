import java.awt.*;
import java.util.ArrayList;

public class Figures {
    private ArrayList<Location> arrayCircules;
    public ArrayList<Location> Line(Location pointA, Location pointB){
        ArrayList<Location> destinationPoints = new ArrayList<>();
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
            destinationPoints.add(new Location((int) x,(int) y));
        }
        return destinationPoints;
    }

    public void lineDDA(Location pointA,Location pointB){
        ArrayList<Location> destinationPoints = new ArrayList<>();
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
                destinationPoints.add(new Location(pointA.pointX, pointA.pointY));

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
                destinationPoints.add(new Location(pointA.pointX, pointA.pointY));
            }
        }
    }

    public void bresenham(Location pointA,Location pointB){
        int x, y, dx, dy, p, incE, incNE, stepx, stepy,x0,y0,x1,y1;
        ArrayList<Location> destinationPoints = new ArrayList<>();
        x0=pointA.pointX;
        y0=pointA.pointY;
        x1= pointB.pointX;
        y1 = pointB.pointY;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar
         */
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
        destinationPoints.add(new Location(x0,y0));
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
                destinationPoints.add(new Location(x,y));
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
                destinationPoints.add(new Location(x,y));

            }
        }
    }

    public  void middlePoint(Location pointA,Location pointB){
        int x0 = pointA.pointX;
        int y0 = pointA.pointY;
        int x1 = pointB.pointX;
        int y1 = pointB.pointY;
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        ArrayList<Location> destinationPoints = new ArrayList<>();

        int x = x0;
        int y = y0;

        while (true) {
            destinationPoints.add(new Location(x,y));
            if (x == x1 && y == y1) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x += sx;
            }
            if (e2 < dx) {
                err += dx;
                y += sy;
            }
        }
    }

    public ArrayList<Location> basicCircle(Location pointA , Location pointB){
        ArrayList<Location> destinationPoints = new ArrayList<>();
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;

        for (int x = 0; x <= radius; x++) {
            double y = Math.sqrt(radius * radius - x * x);
            int x1 = centerX + x;
            int x2 = centerX - x;
            int y1 = centerY + (int) Math.round(y);
            int y2 = centerY - (int) Math.round(y);

            // Dibuja los 4 cuadrantes del círculo
            destinationPoints.add(new Location(x1, y1));
            destinationPoints.add(new Location(x2, y1));
            destinationPoints.add(new Location(x1, y2));
            destinationPoints.add(new Location(x2, y2));
        }
        return destinationPoints;
    }

    public ArrayList<Location> polarCircle(Location pointA , Location pointB){
        ArrayList<Location> destinationPoints = new ArrayList<>();
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;
        for (double theta = 0; theta <= 2 * Math.PI; theta += 0.01) {
            int x = centerX + (int) (radius * Math.cos(theta));
            int y = centerY + (int) (radius * Math.sin(theta));
            destinationPoints.add(new Location(x, y));
        }
        return destinationPoints;
    }

    public ArrayList<Location> middlePointCircle(Location pointA, Location pointB){
        this.arrayCircules = new ArrayList<>();
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;
        int x = 0;
        int y = radius;
        int p = 5 / 4 - radius; // Inicializamos p0

        drawCirclePoints(centerX, centerY, x, y); // Pintar el primer punto

        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * x - 2 * y + 1;
            }
            drawCirclePoints(centerX, centerY, x, y);
        }
        return this.arrayCircules;
    }

    private void drawCirclePoints(int centerX, int centerY, int x, int y) {
        // Pintar puntos en los 8 octantes usando simetría
        this.arrayCircules.add(new Location(centerX + x, centerY + y));
        this.arrayCircules.add(new Location(centerX - x, centerY + y));
        this.arrayCircules.add(new Location(centerX + x, centerY - y));
        this.arrayCircules.add(new Location(centerX - x, centerY - y));

        this.arrayCircules.add(new Location(centerX + y, centerY + x));
        this.arrayCircules.add(new Location(centerX - y, centerY + x));
        this.arrayCircules.add(new Location(centerX + y, centerY - x));
        this.arrayCircules.add(new Location(centerX - y, centerY - x));
    }

    public ArrayList<Location> elipse(Location points,int radiusX,int radiusY){
        ArrayList<Location> destinationsPoints = new ArrayList<>();

        for (int i = 0; i < 360; i++){
            double radian = Math.PI / 180 * i;
            int x = points.pointX + (int) (radiusX * Math.cos(radian));
            int y = points.pointY + (int) (radiusY * Math.sin(radian));
            destinationsPoints.add(new Location(x, y));
        }

        return destinationsPoints;
    }
    public ArrayList<Location> rectangle(Location pointA, Location pointB) {
        ArrayList<Location> destinationPoints = new ArrayList<>();

        // Asegurarse de que pointA sea la esquina superior izquierda y pointB sea la esquina inferior derecha
        if (pointA.pointX > pointB.pointX) {
            Location temp = pointA;
            pointA = pointB;
            pointB = temp;
        }

        int width = pointB.pointX - pointA.pointX;
        int height = pointB.pointY - pointA.pointY;

        // Línea superior
        for (int x = pointA.pointX; x <= pointB.pointX; x++) {
            destinationPoints.add(new Location(x, pointA.pointY));
        }

        // Línea derecha
        for (int y = pointA.pointY; y <= pointB.pointY; y++) {
            destinationPoints.add(new Location(pointB.pointX, y));
        }

        // Línea inferior
        for (int x = pointB.pointX; x >= pointA.pointX; x--) {
            destinationPoints.add(new Location(x, pointB.pointY));
        }

        // Línea izquierda
        for (int y = pointB.pointY; y >= pointA.pointY; y--) {
            destinationPoints.add(new Location(pointA.pointX, y));
        }

        return destinationPoints;
    }
}