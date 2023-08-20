import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    private ButtonGroup modos;
    private JPanel area;
    private JLabel status;
    private Image buffer;
    private Image temporal;

    private final int Puntos = 1;
    private final int Lineas = 2;
    private final int Rectangulos = 3;
    private final int Circulos = 4;
    private int modo;
    private int x, y;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        super("MiniPaint 1.0");
        //Menu del archivo
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");

        //Opcion nuevo
        JMenuItem opcionNuevo = new JMenuItem("Nuevo", 'N');
        opcionNuevo.addActionListener(this);
        opcionNuevo.setActionCommand("Nuevo");
        menuArchivo.add(opcionNuevo);

        menuArchivo.addSeparator();
        //opcion Salir
        JMenuItem opcionSalir = new JMenuItem("Salir", 'S');
        opcionSalir.addActionListener(this);
        opcionSalir.setActionCommand("Salir");
        menuArchivo.add(opcionSalir);

        menuBar.add(menuArchivo);
        modos = new ButtonGroup();
        //menu modo
        JMenu menuModo = new JMenu("Modo");

        JRadioButtonMenuItem opcionPuntos = new JRadioButtonMenuItem("Puntos", true);
        opcionPuntos.addActionListener(this);
        opcionPuntos.setActionCommand("Puntos");
        menuModo.add(opcionPuntos);
        modos.add(opcionPuntos);

        //opcion lineas
        JRadioButtonMenuItem opcionLineas = new JRadioButtonMenuItem("Lineas");
        opcionLineas.addActionListener(this);
        opcionLineas.setActionCommand("Lineas");
        menuModo.add(opcionLineas);
        modos.add(opcionLineas);

        //opcion rectangulos
        JRadioButtonMenuItem opcionRectangulos = new JRadioButtonMenuItem("Rectangulos");
        opcionRectangulos.addActionListener(this);
        opcionRectangulos.setActionCommand("Rectangulos");
        menuModo.add(opcionRectangulos);
        modos.add(opcionRectangulos);

        //opcion circulos
        JRadioButtonMenuItem opcionCirculos = new JRadioButtonMenuItem("Ciruculos");
        opcionCirculos.addActionListener(this);
        opcionCirculos.setActionCommand("Circulos");
        menuModo.add(opcionCirculos);
        modos.add(opcionCirculos);
        menuBar.add(menuModo);

        //area
        area = new JPanel();
        area.addMouseListener(this);
        area.addMouseMotionListener(this);
        status = new JLabel("Status",JLabel.LEFT);
        //Asignar barra menus
        setJMenuBar(menuBar);
        //asignar zona grafica
        getContentPane().add(area,BorderLayout.CENTER);
        //agregar barra de estado
        getContentPane().add(status,BorderLayout.SOUTH);
        modo = Puntos;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,300);
        show();
        buffer = area.createImage(area.getWidth(),area.getHeight());
    }

    public void actionPerformed(ActionEvent e){
        String comando = e.getActionCommand();
        if (comando.equals("Nuevo")){
            area.getGraphics().clearRect(0,0,area.getWidth(),area.getHeight());

        }
        if(comando.equals("Salir")){
            if (JOptionPane.showConfirmDialog(this,"En verdad desea salir?",
                    "Confirmacion",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                dispose();
                System.exit(0);
            }
        }
        if (comando.equals("Puntos")){
            modo = Puntos;
        }
        if(comando.equals("Lineas")){
            modo = Lineas;
        }
        if(comando.equals("Rectangulos")){
            modo = Rectangulos;
        }
        if(comando.equals("Circulos")){
            modo = Circulos;
        }
    }

    public void mouseClicked(MouseEvent e){
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
            case Puntos:
                g.fillOval(e.getX(),e.getY(),1,1);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;
            case Lineas:
                g.drawImage(buffer,0,0,area);
                g.drawLine(x,y,e.getX()-x,e.getY()-y);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;
            case Rectangulos:
                g.drawImage(buffer,0,0,area);
                g.drawRect(x,y,e.getX()-x,getY()-y);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;
            case Circulos:
                g.drawImage(buffer,0,0,area);
                g.drawOval(x,y,e.getX()-x,e.getY()-y);
                area.getGraphics().drawImage(temporal,0,0,this);
                break;
        }
    }

    public void mouseMoved(MouseEvent e){
        status.setText("x = "+e.getX()+" , y = "+ e.getY());
    }
}