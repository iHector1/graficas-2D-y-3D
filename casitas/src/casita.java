import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class casita  extends JFrame {

   public casita(){
       setTitle("Mi casita");
       setBounds(300,100,500,500);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

    public static void main(String[] args) {
        new casita();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
        // Dibuja el sol o la luna
        g.setColor(Color.YELLOW);
        g.fillOval(getWidth() - 150, 50, 100, 100);
        //nubes
        g.setColor(Color.WHITE);
        g.fillOval(200, 100, 80, 40);
        g.fillOval(200 + 50, 100, 80, 40);
        g.fillOval(400, 100, 80, 40);
        g.fillOval(300 + 50, 100, 80, 40);

        //grass
        g.setColor(new Color(23,67,56));
        g.drawRect(0,300,600,300);
        g.fillRect(0,300,600,300);

        //roof
        int poligonX[] = {50,100};
        int poligonY[] = {100,100};
        g.drawPolygon(poligonX,poligonY,poligonX.length);

    }
}
