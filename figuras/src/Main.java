import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        setTitle("Casita");
        setSize(500,500);
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setEnabled(true);
        setVisible(true);
    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.blue);

        //g.drawLine(400,405,275,25);
        //rectangulo
        g.drawRect(20,20,200,100);
        g.fillRect(20,20,200,100);

        //cuadrado
        g.setColor(Color.black);
        g.drawRect(250,200,100,100);
        g.fillRect(250,200,100,100);
        //ciruclo
        g.setColor(Color.red);
        g.drawOval(120,150,120,120);
        g.fillOval(120,150,120,120);

        g.drawString("Figuras",200,400);

    }
}