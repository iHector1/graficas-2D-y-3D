import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FiguresColors extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;
    private Figures g;
    private ArrayList<Location> locations;
    private ExecutorService executorService;
    // Constants for thread pool size and queue capacity
    private static final int THREAD_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 10000;

    FiguresColors(){
        this.g = new Figures();
        setTitle("All Figures");
        setSize(740,517);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    @Override
    public void paint(Graphics g) {

    }
    

    private void putPixel(int x, int y, Color color){
        buffer.setRGB(x, y, color.getRGB());
        this.getGraphics().drawImage(buffer, 0, 0, this);
    }
}
