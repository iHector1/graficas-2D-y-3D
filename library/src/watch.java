import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class watch extends JFrame implements Runnable {
    private int carPositionX = 0;  // Posición inicial del carro
    private int cloudPositionX = 0;  // Posición inicial de la nube
    private BufferedImage buffer;
    private Thread thread;
    private Figures g;
    private Timer timer;
    private int clockSize = 400;  // Tamaño del reloj y de la ventana

    public watch() {
        setTitle("Analog Clock");
        setSize(700, 700);
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g = new Figures();
        thread = new Thread(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        updateBuffer();
        g.drawImage(buffer, 0, 0, this);
    }

    private void updateBuffer() {
        Graphics bufferGraphics = buffer.getGraphics();
        drawClock(bufferGraphics);
    }

    private void drawClock(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Limpiar la pantalla
        drawBackground(g);  // Dibujar fondo con montañas
        drawCloud(g);
        drawCactus(g);
        // Dibujar el reloj
        drawClockCircle(centerX, centerY);

        // Dibujar las manecillas del reloj
        Calendar cal = Calendar.getInstance();
        drawHourHand(centerX, centerY, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
        drawMinuteHand(centerX, centerY, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        drawSecondHand(centerX, centerY, cal.get(Calendar.SECOND));
        drawMinuteLines(centerX, centerY);
        // Dibujar números de las horas
        drawHourNumbers(centerX, centerY, g);
    }

    private void drawClockCircle(int centerX, int centerY) {
        Color clockColor = Color.BLACK;
        int radius = clockSize / 2 - 10;

        ArrayList<Location> clockPoints = g.basicCircle(new Location(centerX, centerY), radius, radius);
        for (Location location : clockPoints) {
            putPixel(location.pointX, location.pointY, clockColor);
        }
    }

    private void drawHourHand(int centerX, int centerY, int hour, int minute) {
        double hourAngle = Math.toRadians(((hour % 12 + minute / 60.0) * 30) - 90);  // Corregir ángulo
        int handLength = clockSize / 5;

        int endX = (int) (centerX + handLength * Math.cos(hourAngle));
        int endY = (int) (centerY + handLength * Math.sin(hourAngle));

        Color hourHandColor = Color.BLUE;
        ArrayList<Location> points = g.middlePoint(new Location(centerX, centerY), new Location(endX, endY));
        for (Location point : points) {
            putPixel(point.pointX, point.pointY, hourHandColor);
        }
    }


    private void drawMinuteHand(int centerX, int centerY, int minute, int second) {
        double minuteAngle = Math.toRadians(((minute + second / 60.0) * 6) - 90);  // Corregir ángulo
        int handLength = clockSize / 3 - 10;

        int endX = (int) (centerX + handLength * Math.cos(minuteAngle));
        int endY = (int) (centerY + handLength * Math.sin(minuteAngle));

        Color minuteHandColor = Color.GREEN;
        ArrayList<Location> points = g.middlePoint(new Location(centerX, centerY), new Location(endX, endY));
        for (Location point : points) {
            putPixel(point.pointX, point.pointY, minuteHandColor);
        }
    }

    private void drawSecondHand(int centerX, int centerY, int second) {
        double secondAngle = Math.toRadians((second * 6) - 90);  // Corregir ángulo
        int handLength = clockSize / 3 + 10;

        int endX = (int) (centerX + handLength * Math.cos(secondAngle));
        int endY = (int) (centerY + handLength * Math.sin(secondAngle));

        Color secondHandColor = Color.RED;
        ArrayList<Location> points = g.middlePoint(new Location(centerX, centerY), new Location(endX, endY));
        for (Location point : points) {
            putPixel(point.pointX, point.pointY, secondHandColor);
        }
    }
    private void drawCar(Graphics g) {
        // Dibujar el carro
        int carY = getHeight() - 300;  // Altura del carro en la parte inferior de la ventana
        int carWidth = 50;
        int carHeight = 30;
        Color carColor = Color.BLUE;

        // Limpiar la posición anterior del carro
        g.clearRect(carPositionX - 1, carY - carHeight, carWidth + 2, carHeight);

        // Dibujar el carro en la nueva posición
        g.setColor(carColor);
        g.fillRect(carPositionX, carY - carHeight, carWidth, carHeight);

        // Mover el carro
        carPositionX = (carPositionX + 5) % getWidth();
        // Dibujar las llantas del carro
        int carWheelRadius = 7;
        int wheel1X = carPositionX -5;  // Posición de la primera llanta
        int wheel2X = carPositionX + carWidth - 20;  // Posición de la segunda llanta
        int wheelY = getHeight() - 300;  // Altura de la llanta en la parte inferior del carro

        // Dibujar primera llanta
        g.setColor(Color.BLACK);
        g.fillOval(wheel1X, wheelY - carWheelRadius, carWheelRadius * 2, carWheelRadius * 2);

        // Dibujar segunda llanta
        g.setColor(Color.BLACK);
        g.fillOval(wheel2X, wheelY - carWheelRadius, carWheelRadius * 2, carWheelRadius * 2);

    }

    private void drawCloud(Graphics g) {
        // Dibujar la nube
        int cloudY = 100;  // Altura de la nube en la parte superior de la ventana
        int cloudWidth = 80;
        int cloudHeight = 40;
        Color cloudColor = Color.WHITE;

        // Limpiar la posición anterior de la nube
        g.setColor(getBackground());  // Usar el color de fondo para limpiar
        g.fillOval(cloudPositionX - 1, cloudY, cloudWidth + 2, cloudHeight);
        g.fillOval(cloudPositionX - 15, cloudY-20, cloudWidth + 2, cloudHeight);
        g.fillOval(cloudPositionX - 40, cloudY, cloudWidth + 2, cloudHeight);
        // Dibujar la nube en la nueva posición
        g.setColor(cloudColor);
        g.fillOval(cloudPositionX, cloudY, cloudWidth, cloudHeight);
        g.fillOval(cloudPositionX-15, cloudY-20, cloudWidth, cloudHeight);
        g.fillOval(cloudPositionX-40, cloudY, cloudWidth, cloudHeight);

        // Mover la nube cada minuto
        Calendar cal = Calendar.getInstance();
        int currentMinute = cal.get(Calendar.MINUTE);
        if (currentMinute != (currentMinute + 1) % 320) {
            cloudPositionX = (cloudPositionX + 1) % getWidth();
        }

    }


    private void drawHourNumbers(int centerX, int centerY, Graphics g) {
        int radius = clockSize / 2 - 40;
        Font font = new Font("Arial", Font.BOLD, 20);
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(90 - i * 30);
            int x = (int) (centerX + radius * Math.cos(angle));
            int y = (int) (centerY - radius * Math.sin(angle));

            g.setFont(font);
            g.setColor(Color.BLACK);

            FontMetrics fm = g.getFontMetrics();
            int width = fm.stringWidth(Integer.toString(i));
            int height = fm.getHeight();

            g.drawString(Integer.toString(i), x - width / 2, y + height / 4);
        }
    }

    private void drawMinuteLines(int centerX, int centerY) {
        int radius = clockSize / 2 - 10;

        for (int i = 0; i < 60; i++) {
            int large =i % 5 ==0 ? 20 : 10;
            Color color =i % 5 ==0 ? Color.WHITE : Color.BLACK;
            double angle = Math.toRadians(270 - i * 6);
            int x1 = (int) (centerX + (radius - large) * Math.cos(angle));
            int y1 = (int) (centerY + (radius - large) * Math.sin(angle));
            int x2 = (int) (centerX + radius * Math.cos(angle));
            int y2 = (int) (centerY + radius * Math.sin(angle));
            ArrayList<Location> points = g.middlePoint(new Location(x1, y1), new Location(x2, y2));
            for (Location point : points) {
                putPixel(point.pointX, point.pointY, color);
            }
        }
    }

    private void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
    }

    @Override
    public void run() {
        while (true) {
            try {
                repaint();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void drawCactus(Graphics g) {
        int cactusHeight = 50;
        int cactusWidth = 20;
        Color cactusColor = new Color(34, 139, 34);  // Verde oscuro

        // Dibujar cactus en la orilla izquierda
        int cactusLeftX = 50;
        int cactusLeftY = getHeight() - cactusHeight - 50;  // Altura del cactus en la parte inferior de la ventana
        g.setColor(cactusColor);
        g.fillRect(cactusLeftX, cactusLeftY, cactusWidth, cactusHeight);

        // Dibujar cactus en la orilla derecha
        int cactusRightX = getWidth() - cactusWidth - 50;
        int cactusRightY = getHeight() - cactusHeight - 50;  // Altura del cactus en la parte inferior de la ventana
        g.setColor(cactusColor);
        g.fillRect(cactusRightX, cactusRightY, cactusWidth, cactusHeight);
    }
    private void drawBackground(Graphics graphics) {
        Graphics2D g2d = buffer.createGraphics();
        int radius = clockSize / 2 - 10;
        Calendar cal = Calendar.getInstance();
        // Dibujar un círculo centro del reloj
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int currentHour = cal.get(Calendar.HOUR_OF_DAY);
        if ((currentHour >= 7 && currentHour < 18) || (currentHour == 18 && cal.get(Calendar.MINUTE) == 0)) {
            // Rango de 7 am a 6 pm (amarillo)

            // Dibujar rectángulo de montaña color café
            int mountainWidth = getWidth();  // Ancho igual al ancho de la ventana
            int mountainHeight = getHeight() / 2;  // Altura igual a la mitad de la ventana
            int mountainX = 0;
            int mountainY = getHeight() / 2;
            Color brighterMountainColor = new Color(184, 134, 11);
            g2d.setColor(brighterMountainColor);
            g2d.fillRect(mountainX, mountainY, mountainWidth, mountainHeight);

            // Dibujar rectángulo de montaña color azul
            int mountainBlueWidth = getWidth();  // Ancho igual al ancho de la ventana
            int mountainBlueHeight = getHeight() / 2;  // Altura igual a la mitad de la ventana
            int mountainBlueX = 0;
            int mountainBlueY = 0;
            Color brighterMountainBlueColor = new Color(135, 206, 250);
            g2d.setColor(brighterMountainBlueColor);
            g2d.fillRect(mountainBlueX, mountainBlueY, mountainBlueWidth, mountainBlueHeight);
            Color amBackgroundColor = Color.YELLOW;
            g2d.setColor(amBackgroundColor);
            g2d.fillOval(centerX - radius - 20, centerY - radius - 20, 40, 40);
            ArrayList<Location> locations= g.basicCircle(new Location(centerX - radius , centerY - radius ),20, 20);
            for (Location point : locations) {
                putPixel(point.pointX, point.pointY, Color.BLACK);
            }
        } else {
            // Rango de 6:01 pm a 6:59 am (blanco)
            // Dibujar rectángulo de montaña color café
            int mountainWidth = getWidth();  // Ancho igual al ancho de la ventana
            int mountainHeight = getHeight() / 2;  // Altura igual a la mitad de la ventana
            int mountainX = 0;
            int mountainY = getHeight() / 2;
            Color mountainColor = new Color(139, 69, 19);  // Café
            g2d.setColor(mountainColor);
            g2d.fillRect(mountainX, mountainY, mountainWidth, mountainHeight);

            // Dibujar rectángulo de montaña color azul
            int mountainBlueWidth = getWidth();  // Ancho igual al ancho de la ventana
            int mountainBlueHeight = getHeight() / 2;  // Altura igual a la mitad de la ventana
            int mountainBlueX = 0;
            int mountainBlueY = 0;
            Color mountainBlueColor = new Color(70, 130, 180);  // Azul
            g2d.setColor(mountainBlueColor);
            g2d.fillRect(mountainBlueX, mountainBlueY, mountainBlueWidth, mountainBlueHeight);
            Color pmBackgroundColor = Color.WHITE;
            g2d.setColor(pmBackgroundColor);
            g2d.fillOval(centerX - radius - 20, centerY - radius - 20, 40, 40);
            ArrayList<Location> locations= g.basicCircle(new Location(centerX - radius, centerY - radius),20, 20);
            for (Location point : locations) {
                putPixel(point.pointX, point.pointY, Color.BLACK);
            }
        }
        drawCar(graphics);
        Color circleColor = Color.GRAY;
        Color externarCirleColor = Color.DARK_GRAY;
        g2d.setColor(externarCirleColor);
        g2d.fillOval(centerX - radius - 5, centerY - radius - 5, radius * 2 + 10, radius * 2 + 10);
        g2d.setColor(circleColor);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Dibujar AM o PM y la hora en formato de 12 horas

        int hour12Format = cal.get(Calendar.HOUR);
        String formattedHour = String.format("%02d", hour12Format);
        String amPm = cal.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        String timeString = formattedHour + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) + ":" + String.format("%02d", cal.get(Calendar.SECOND));
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.setColor(Color.WHITE);
        g2d.drawString(timeString, getWidth() / 2 - 40, centerY - 30);
        g2d.drawString(amPm, getWidth() / 2 - 10, centerY + 50);

    }

    public static void main(String[] args) {
        new watch();
    }
}
