import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Manecillas extends JFrame implements Runnable {
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    int min;
    int hora;
    int sec;
    int manecillaSegundos = 100;
    int manecillaMinutos = 70;
    int manecillaHora = 50;

    public Manecillas(){
        setTitle("Manecillas");
        setResizable(false);
        setLayout(null);
        setVisible(true);
        hilo = new Thread(this);
        hilo.start();
    }
    @Override
    public void paint(Graphics g){
        if (fondo == null){
            fondo = createImage(getWidth(),getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
            ImageIcon fondito = new ImageIcon("img/jedi.png");
            gfondo.drawImage(fondito.getImage(),(getWidth()-300)/2,(getHeight()-300)/2, 300,300,this);

        }
        update(g);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        double xHora, yHora, anguloHora;
        g.setClip(0,0,getWidth(),getHeight());
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.MINUTE)!= min){
            //generar la imagen de fondo
            hora = cal.get(Calendar.HOUR);

            //crear la imagen estatica
            buffer = createImage(getWidth(),getHeight());
            Graphics gbuffer = buffer.getGraphics();
            gbuffer.setClip(0,0,getWidth(),getWidth());
            gbuffer.drawImage(fondo,0,0,this);

            //horas
            System.out.println(hora);
            anguloHora = hora;

            System.out.println("Angulo hora "+ anguloHora);
           // xHora = get
        }
    }
    @Override
    public void run(){

    }
}
