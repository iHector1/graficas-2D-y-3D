import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniPaint extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private ButtonGroup modos;
    private JPanel area;
    private JLabel status;
    private Image buffer;
    private Image temporal;

    private final int PUNTOS = 1;
    private final int LINEAS = 2;
    private final int RECTANGULOS = 3;
    private final int CIRCULOS = 4;
    private int modo;
    private int x,y;

    public MiniPaint(){

        super("MiniPaint 1.0");

        JMenuBar menuBar = new JMenuBar();

        //Menu Archivo
        JMenu menuArchivo = new JMenu("Archivo");

        //Opcion Nuevo
        JMenuItem opcionNuevo = new JMenuItem("Nuevo",'N');
        opcionNuevo.addActionListener(this);
        opcionNuevo.setActionCommand("Nuevo");
        menuArchivo.add(opcionNuevo);
        menuArchivo.addSeparator();

        //Opcion Salir
        JMenuItem opcionSalir = new JMenuItem("Salir",'S');
        opcionSalir.addActionListener(this);
        opcionSalir.setActionCommand("Salir");
        menuArchivo.add(opcionSalir);

        menuBar.add(menuArchivo);

        modos = new ButtonGroup();

        //Menu Modo
        JMenu menuModo = new JMenu("Modo");

        //Opcion Puntos
        JRadioButtonMenuItem opcionPuntos = new JRadioButtonMenuItem("Puntos",true);
        opcionPuntos.addActionListener(this);
        opcionPuntos.setActionCommand("Puntos");
        menuModo.add(opcionPuntos);
        modos.add(opcionPuntos);

        //Opcion Lineas
        JRadioButtonMenuItem opcionLineas = new JRadioButtonMenuItem("Lineas",true);
        opcionLineas.addActionListener(this);
        opcionLineas.setActionCommand("Lineas");
        menuModo.add(opcionLineas);
        modos.add(opcionLineas);

        //Opcion Rectangulos
        JRadioButtonMenuItem opcionRectangulos = new JRadioButtonMenuItem("Rectangulos",true);
        opcionRectangulos.addActionListener(this);
        opcionRectangulos.setActionCommand("Rectangulos");
        menuModo.add(opcionRectangulos);
        modos.add(opcionRectangulos);

        //Opcion Circulos
        JRadioButtonMenuItem opcionCirculos = new JRadioButtonMenuItem("Circulos",true);
        opcionCirculos.addActionListener(this);
        opcionCirculos.setActionCommand("Circulos");
        menuModo.add(opcionCirculos);
        modos.add(opcionCirculos);

        menuBar.add(menuModo);

        area = new JPanel();
        area.addMouseListener(this);
        area.addMouseMotionListener(this);
        status = new JLabel("Status ",JLabel.LEFT);

        //Asignar Barra Menus
        setJMenuBar(menuBar);

        //Agregar Zona Grafica
        getContentPane().add(area,BorderLayout.CENTER);

        //Agregar Barra de Estado
        getContentPane().add(status,BorderLayout.SOUTH);
        modo = PUNTOS;

        //Configuracion JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setVisible(true);
        buffer = area.createImage(area.getWidth(),area.getHeight());

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (comando.equals("Nuevo")){
            area.getGraphics().clearRect(0,0, area.getWidth() , area.getHeight());
        }else if (comando.equals("Salir")){
            if(JOptionPane.showConfirmDialog(this,"En Verdad desea salir?","Confirmacion",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                dispose();
                System.exit(0);
            }
        }else if (comando.equals("Puntos")){
            modo = PUNTOS;
        }else if (comando.equals("Lineas")){
            modo = LINEAS;
        }else if (comando.equals("Rectangulos")){
            modo = RECTANGULOS;
        }else if (comando.equals("Circulos")){
            modo = CIRCULOS;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        temporal = area.createImage(area.getWidth(), area.getHeight());
        temporal.getGraphics().drawImage(temporal,0,0,this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buffer.getGraphics().drawImage(temporal,0,0,this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        Graphics g = temporal.getGraphics();

        switch (modo){
            case PUNTOS:
                g.fillOval(e.getX(),e.getY(),1,1);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;

            case LINEAS:
                g.drawImage(buffer,0,0,area);
                g.drawLine(x,y,e.getX(),e.getY());
                area.getGraphics().drawImage(temporal,0,0,this);
                break;

            case RECTANGULOS:
                g.drawImage(buffer,0,0,area);
                g.drawRect(x,y,e.getX()-x,e.getY()-y);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;

            case CIRCULOS:
                g.drawImage(buffer,0,0,area);
                g.drawOval(x,y,e.getX()-x,e.getY()-y);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        status.setText("X = "+ e.getX() +" Y =  "+e.getY());
    }

    public static void main(String[] args){
        new MiniPaint();
    }
}
