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

    public ArrayList<Location> lineDDA(Location pointA,Location pointB){
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
        return destinationPoints;
    }

    public ArrayList<Location> bresenham(Location pointA,Location pointB){
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
        return destinationPoints;
    }

    public  ArrayList<Location> middlePoint(Location pointA,Location pointB){
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
        return destinationPoints;
    }

    public ArrayList<Location> basicCircle(Location pointA , Location pointB){
        ArrayList<Location> destinationPoints = new ArrayList<>();
        int centerX = (pointA.pointX + pointB.pointX) / 2;
        int centerY = (pointA.pointY +pointB.pointY) / 2;
        int radius = (int) Math.sqrt(Math.pow(pointB.pointX - pointA.pointX , 2) +
                Math.pow(pointB.pointY - pointA.pointY, 2)) / 2;

        for (int x = 0; x <= radius; x++) {
            double y = Math.sqrt(radius * radius - x * x);
            // Dibuja los 8 cuadrantes del círculo
            destinationPoints.add(new Location(centerX + x, centerY + (int) Math.round(y)));
            destinationPoints.add(new Location(centerX - x, centerY + (int) Math.round(y)));
            destinationPoints.add(new Location(centerX + x, centerY - (int) Math.round(y)));
            destinationPoints.add(new Location(centerX - x, centerY - (int) Math.round(y)));
            destinationPoints.add(new Location(centerX + (int) Math.round(y), centerY + x));
            destinationPoints.add(new Location(centerX - (int) Math.round(y), centerY + x));
            destinationPoints.add(new Location(centerX + (int) Math.round(y), centerY - x));
            destinationPoints.add(new Location(centerX - (int) Math.round(y), centerY - x));
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
        ArrayList<Location> destinationPoints = new ArrayList<>();

        for (double i = 0; i < 360; i +=.1) {
            double radian = Math.PI / 180 * i;
            double x = points.pointX + (radiusX * Math.cos(radian));
            double y = points.pointY + (radiusY * Math.sin(radian));
            destinationPoints.add(new Location((int) x, (int) y));
        }

        return destinationPoints;
    }
    public ArrayList<Location> basicCircle(Location centerPoint, int radiusX, int radiusY) {
        ArrayList<Location> destinationPoints = new ArrayList<>();

        int centerX = centerPoint.pointX;
        int centerY = centerPoint.pointY;

        for (int x = 0; x <= radiusX; x++) {
            double y = Math.sqrt(radiusY * radiusY - (radiusY * radiusY * x * x) / (radiusX * radiusX));
            int x1 = centerX + x;
            int x2 = centerX - x;
            int y1 = centerY + (int) Math.round(y);
            int y2 = centerY - (int) Math.round(y);

            // Dibuja los 8 cuadrantes del círculo
            destinationPoints.add(new Location(x1, y1));
            destinationPoints.add(new Location(x2, y1));
            destinationPoints.add(new Location(x1, y2));
            destinationPoints.add(new Location(x2, y2));
            destinationPoints.add(new Location(centerX + (int) Math.round(y), centerY + x));
            destinationPoints.add(new Location(centerX - (int) Math.round(y), centerY + x));
            destinationPoints.add(new Location(centerX + (int) Math.round(y), centerY - x));
            destinationPoints.add(new Location(centerX - (int) Math.round(y), centerY - x));
        }

        return destinationPoints;
    }
    public ArrayList<Location> rectangle(Location pointA, Location pointB) {
        ArrayList<Location> squarePoints = new ArrayList<>();

        // Verificar si pointA y pointB son iguales o están en la misma posición
        if (pointA.equals(pointB)) {
            return squarePoints; // Devolver una lista vacía si son iguales
        }

        // Encontrar el lado más corto para determinar la longitud del lado del cuadrado
        int sideLength = Math.min(Math.abs(pointB.pointX - pointA.pointX), Math.abs(pointB.pointY - pointA.pointY));

        // Asegurarse de que pointA sea la esquina superior izquierda y pointB sea la esquina inferior derecha
        Location upperLeft = new Location(Math.min(pointA.pointX, pointB.pointX), Math.min(pointA.pointY, pointB.pointY));
        Location lowerRight = new Location(Math.max(pointA.pointX, pointB.pointX), Math.max(pointA.pointY, pointB.pointY));

        // Agregar los puntos del cuadrado
        for (int x = upperLeft.pointX; x <= lowerRight.pointX; x++) {
            for (int y = upperLeft.pointY; y <= lowerRight.pointY; y++) {
                // Verificar si estamos en un borde del cuadrado y agregar ese punto
                if (x == upperLeft.pointX || x == lowerRight.pointX || y == upperLeft.pointY || y == lowerRight.pointY) {
                    squarePoints.add(new Location(x, y));
                }
            }
        }

        return squarePoints;
    }
    public ArrayList<Location> triangle(Location pointA, Location pointB, Location pointC) {
        ArrayList<Location> trianglePoints = new ArrayList<>();

        ArrayList<Location> midAB = middlePoint(pointA, pointB);
        ArrayList<Location> midBC = middlePoint(pointB, pointC);
        ArrayList<Location> midCA = middlePoint(pointC, pointA);


        trianglePoints.addAll(midAB);
        trianglePoints.addAll(midBC);
        trianglePoints.addAll(midCA);
        return trianglePoints;
    }
    public ArrayList<Location> rhombus(Location pointA, Location pointB) {
        ArrayList<Location> rhombusPoints = new ArrayList<>();

        int midX = (pointA.pointX + pointB.pointX) / 2;
        int midY = (pointA.pointY + pointB.pointY) / 2;

        Location upperMid = new Location(midX, pointA.pointY);
        Location lowerMid = new Location(midX, pointB.pointY);
        Location leftMid = new Location(pointA.pointX, midY);
        Location rightMid = new Location(pointB.pointX, midY);

        rhombusPoints.addAll(Line(upperMid, rightMid));
        rhombusPoints.addAll(Line(rightMid, lowerMid));
        rhombusPoints.addAll(Line(lowerMid, leftMid));
        rhombusPoints.addAll(Line(leftMid, upperMid));

        return rhombusPoints;
    }

    public ArrayList<Location> Rectangle(Location pointA, Location pointB) {
        ArrayList<Location> points = new ArrayList<>();

        // Use los métodos de la clase Figures para calcular los puntos del rectángulo
        ArrayList<Location> line1 = Line(pointA, new Location(pointB.pointX, pointA.pointY));
        ArrayList<Location> line2 = Line(new Location(pointA.pointX, pointB.pointY), pointB);

        ArrayList<Location> line3 = Line(pointA, new Location(pointA.pointX, pointB.pointY));
        ArrayList<Location> line4 = Line(new Location(pointB.pointX, pointA.pointY), pointB);

        // Agregue todos los puntos de las líneas al resultado
        points.addAll(line1);
        points.addAll(line2);
        points.addAll(line3);
        points.addAll(line4);

        return points;

    }
}
