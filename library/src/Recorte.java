import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Recorte extends JPanel {
    private int squareX = 200;  // Ajustamos la posición inicial del cuadrado
    private int squareY = 200;  // Ajustamos la posición inicial del cuadrado
    private int squareSize = 200;  // Ajustamos el tamaño del cuadrado

    private int circleX = -1;
    private int circleY = -1;
    private int circleDiameter = 100;

    public Recorte() {
        setPreferredSize(new Dimension(600, 600));  // Establecemos el tamaño preferido del panel
        setBackground(Color.WHITE);  // Establecemos el fondo del panel a blanco

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (isInsideSquare(mouseX, mouseY)) {
                    circleX = mouseX;
                    circleY = mouseY;
                    repaint();
                }
            }
        });
    }

    private boolean isInsideSquare(int x, int y) {
        return x >= squareX && x <= squareX + squareSize
                && y >= squareY && y <= squareY + squareSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw square with white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw square
        g.setColor(Color.BLACK);
        g.fillRect(squareX, squareY, squareSize, squareSize);

        // Draw circle if within square
        if (circleX != -1 && circleY != -1 && isInsideSquare(circleX, circleY)) {
            int circleRadius = circleDiameter / 2;
            g.setColor(Color.RED);
            g.fillOval(circleX - circleRadius, circleY - circleRadius, circleDiameter, circleDiameter);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Recorte recortes = new Recorte();
        frame.add(recortes);

        frame.setVisible(true);
    }
}