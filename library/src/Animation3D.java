

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Animation3D extends JFrame implements Runnable, KeyListener {

    private ArrayList<Location3D> location3D;
    private BufferedImage buffer, animacion,temp,temp2;
    private Thread hilo;
    private Color color, disponible;
    private ArrayList<Location> pointsXY = new ArrayList<Location>();
    int x , y,z;
    private float incX, incY, incZ;
    Double a=-25.0,b=15.0;
    boolean r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12;
    Double [][] coordenadas={   {a,a,a,1.0}, //A0
            {a,a,b,1.0}, //B1
            {a,b,a,1.0}, //C2
            {a,b,b,1.0}, //D3
            {b,a,a,1.0}, //E4
            {b,a,b,1.0}, //F5
            {b,b,a,1.0}, //G6
            {b,b,b,1.0}, //H7
    };
    private int contador;
    int espacios [][]={{0,0,0,0},{0,0,0},{0,0},{0}};
    Double ae=-10.0,be=5.0;
    Double [][] coordenadasEsc={    {ae,ae,ae,1.0}, //A0
            {ae,ae,be,1.0}, //B1
            {ae,be,ae,1.0}, //C2
            {ae,be,be,1.0}, //D3
            {be,ae,ae,1.0}, //E4
            {be,ae,be,1.0}, //F5
            {be,be,ae,1.0}, //G6
            {be,be,be,1.0}, //H7
    };

    Double at=-10.0,bt=10.0;
    Double [][] coordenadasT={      {at,at,at,1.0}, //A0
            {at,at,bt,1.0}, //B1
            {at,bt,at,1.0}, //C2
            {at,bt,bt,1.0}, //D3
            {bt,at,at,1.0}, //E4
            {bt,at,bt,1.0}, //F5
            {bt,bt,at,1.0}, //G6
            {bt,bt,bt,1.0}, //H7
    };

    /*         Estructura cubos     */

    Double a1=-30.0,b1=30.0;
    Double [][] coorCubos={     {a1,a1,a1,1.0}, //A0
            {a1,a1,b1,1.0}, //B1
            {a1,b1,a1,1.0}, //C2
            {a1,b1,b1,1.0}, //D3
            {b1,a1,a1,1.0}, //E4
            {b1,a1,b1,1.0}, //F5
            {b1,b1,a1,1.0}, //G6
            {b1,b1,b1,1.0}, //H7
    };
    boolean colorido;

    /*         End Estructura cubos     */
    Double [][] d={ {200.0,300.0,450.0},
            {200.0,200.0,-500.0},
            {-200.0,-200.0,500.0}
    };
    Double Xa=250.0,Ya=275.0;
    int indexZ;
    private boolean animarCubo=false;
    Double [][] auxCoordenadas=new Double[8][4];
    Double [][] centros,centrosP,auxCentros=new Double[6][4];

    Double [][] auxCoordenadasEsc=new Double[8][4];
    Double [][] centrosEsc,centrosPEsc,auxCentrosEsc=new Double[6][4];

    Double [][] auxCoordenadasT=new Double[8][4];
    Double [][] centrosT,centrosPT,auxCentrosT=new Double[6][4];
    private Location3D vector;
    private Figures g;
    private boolean loading;
    private Transformations transformations;
    private ArrayList<Location3D> pointsXYZ = new ArrayList<Location3D>();
    private int time;
    private float cubertoIncX=0,cubertoIncY=0,cubertoIncZ=0;
    private boolean finScena;

    public Animation3D(){
        finScena=false;
        colorido = false;
        this.cubertoIncX=1;
        this.cubertoIncY=1;
        this.cubertoIncZ=1;
        setTitle("Qbert");
        setSize(800,800);
        setLayout(null);

        buffer=new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion=new BufferedImage(600,500,BufferedImage.TYPE_INT_RGB);
        disponible=new Color(0,0,0);


        location3D = new ArrayList<>();
        centros=new Double[6][4];
        determinarCentros();
        auxCoordenadas=coordenadas.clone();
        auxCentros=centros.clone();
        r1=true;
        r2=r3=r4=r6=r7=r8=r9=r10=r11=r12=false;
        centrosEsc=new Double[6][4];
        determinarCentrosEsc();
        auxCoordenadasEsc=coordenadasEsc.clone();
        auxCentrosEsc=centrosEsc.clone();
        this.time = 100;
        centrosT=new Double[6][4];
        determinarCentrosT();
        auxCoordenadasT=coordenadasT.clone();
        auxCentrosT=centrosT.clone();
        transformations = new Transformations();
        Rotacion(coorCubos, 22, 'y');
        //primera cara
        location3D.add(new Location3D(10, 10, 2));//a 0
        location3D.add(new Location3D(40, 10, 2));//b 1
        location3D.add(new Location3D(10, 40, 2));//c 2
        location3D.add(new Location3D(40, 40, 2));//d 3
        //second square
        location3D.add(new Location3D(10, 10, 10));//e 4
        location3D.add(new Location3D(40, 10, 10));//f 5
        location3D.add(new Location3D(10, 40, 10));//g 6
        location3D.add(new Location3D(40, 40, 10));//h 7
        hilo=new Thread(this);
        hilo.start();


//first square
        pointsXYZ.add(new Location3D(50, 150, 50));//a 0
        pointsXYZ.add(new Location3D(150, 150, 50));//b 1
        pointsXYZ.add(new Location3D(50, 250, 50));//c 2
        pointsXYZ.add(new Location3D(150, 250, 50));//d 3
        //second square
        pointsXYZ.add(new Location3D(50, 150, 150));//e 4
        pointsXYZ.add(new Location3D(150, 150, 150));//f 5
        pointsXYZ.add(new Location3D(50, 250, 150));//g 6
        pointsXYZ.add(new Location3D(150, 250, 150));//h 7
        incX = incY = 1;
        transformations = new Transformations();
        vector= new Location3D(200.0,300.0,450.0);
        loading= false;
        this.g = new Figures();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        contador=0;
    }

    public void control(){
        ArrayList<Location3D> location3DS = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();
        //primera cara
        location3DS.add(new Location3D(30,70 ,2 ));//a
        location3DS.add(new Location3D(50,70 ,2 ));//b
        location3DS.add(new Location3D(50,50 ,2 ));//c
        location3DS.add(new Location3D(70,50 ,2 ));//d
        location3DS.add(new Location3D(70,30 ,2 ));//e
        location3DS.add(new Location3D(50,30 ,2 ));//f
        location3DS.add(new Location3D(50,10 ,2 ));//g
        location3DS.add(new Location3D(30,10 ,2 ));//h
        location3DS.add(new Location3D(30,30 ,2 ));//i
        location3DS.add(new Location3D(10,30 ,2 ));//j
        location3DS.add(new Location3D(10,50 ,2 ));//k
        location3DS.add(new Location3D(30,50 ,2 ));//L

        //segunda cara
        location3DS.add(new Location3D(30,70 ,30 ));//a1
        location3DS.add(new Location3D(50,70 ,30 ));//b1
        location3DS.add(new Location3D(50,50 ,30 ));//c1
        location3DS.add(new Location3D(70,50 ,30 ));//d1
        location3DS.add(new Location3D(70,30 ,30 ));//e1
        location3DS.add(new Location3D(50,30 ,30 ));//f1
        location3DS.add(new Location3D(50,10 ,30 ));//g1
        location3DS.add(new Location3D(30,10 ,30 ));//h1
        location3DS.add(new Location3D(30,30 ,30 ));//i1
        location3DS.add(new Location3D(10,30 ,30 ));//j1
        location3DS.add(new Location3D(10,50 ,30 ));//k1
        location3DS.add(new Location3D(30,50 ,30 ));//l1
        for (int i = 0; i < location3DS.size(); i++){
            float u = 0;
            float y = location3DS.get(i).pointY + (vector.pointY * u);
            float z = location3DS.get(i).pointX + (vector.pointX * u);

            locations.add(new Location((int) ((int)10.0+y), (int) ((int)400.0+z)));
        }
        for (Location point: locations){
            putPixel(point.pointX, point.pointY,Color.red);
        }
        ArrayList<Location> pointsAB = g.bresenham(locations.get(0),locations.get(1));
        ArrayList<Location> pointsBC = g.bresenham(locations.get(1),locations.get(2));
        ArrayList<Location> pointsCD = g.bresenham(locations.get(2),locations.get(3));
        ArrayList<Location> pointsDE = g.bresenham(locations.get(3), locations.get(4));
        ArrayList<Location> pointsEF = g.bresenham(locations.get(4), locations.get(5));
        ArrayList<Location> pointsFG = g.bresenham(locations.get(5), locations.get(6));
        ArrayList<Location> pointsGH = g.bresenham(locations.get(6), locations.get(7));
        ArrayList<Location> pointsHI = g.bresenham(locations.get(7), locations.get(8));
        ArrayList<Location> pointsIJ = g.bresenham(locations.get(8), locations.get(9));
        ArrayList<Location> pointsJK = g.bresenham(locations.get(9), locations.get(10));
        ArrayList<Location> pointsKL = g.bresenham(locations.get(10), locations.get(11));
        ArrayList<Location> pointsLA = g.bresenham(locations.get(11), locations.get(0));

        ArrayList<Location> pointsAB1 = g.bresenham(locations.get(12), locations.get(13));
        ArrayList<Location> pointsBC1 = g.bresenham(locations.get(13), locations.get(14));
        ArrayList<Location> pointsCD1 = g.bresenham(locations.get(14), locations.get(15));
        ArrayList<Location> pointsDE1 = g.bresenham(locations.get(15), locations.get(16));
        ArrayList<Location> pointsEF1 = g.bresenham(locations.get(16), locations.get(17));
        ArrayList<Location> pointsFG1 = g.bresenham(locations.get(17), locations.get(18));
        ArrayList<Location> pointsGH1 = g.bresenham(locations.get(18), locations.get(19));
        ArrayList<Location> pointsHI1 = g.bresenham(locations.get(19), locations.get(20));
        ArrayList<Location> pointsIJ1 = g.bresenham(locations.get(20), locations.get(21));
        ArrayList<Location> pointsJK1 = g.bresenham(locations.get(21), locations.get(22));
        ArrayList<Location> pointsKL1 = g.bresenham(locations.get(22), locations.get(23));
        ArrayList<Location> pointsLA1 = g.bresenham(locations.get(23), locations.get(12));

        ArrayList<Location> points = pointsAB;
        points.addAll(pointsBC);
        points.addAll(pointsCD);
        points.addAll(pointsDE);
        points.addAll(pointsEF);
        points.addAll(pointsFG);
        points.addAll(pointsGH);
        points.addAll(pointsHI);
        points.addAll(pointsIJ);
        points.addAll(pointsJK);
        points.addAll(pointsKL);
        points.addAll(pointsLA);

        //second cara
        points.addAll(pointsAB1);
        points.addAll(pointsBC1);
        points.addAll(pointsCD1);
        points.addAll(pointsDE1);
        points.addAll(pointsEF1);
        points.addAll(pointsFG1);
        points.addAll(pointsGH1);
        points.addAll(pointsHI1);
        points.addAll(pointsIJ1);
        points.addAll(pointsJK1);
        points.addAll(pointsKL1);
        points.addAll(pointsLA1);
        Location location = new Location(2,3);
        for (Location point: points){
            putPixel(point.pointX, point.pointY,Color.red);
            location.setPointX(point.pointX);
            location.setPointY(point.pointY);
        }

        inundacion2(location.pointX-15,location.pointY+11,Color.red,Color.white);

    }

    public void paint(Graphics g){
        animacion=new BufferedImage(this.getWidth(),800,BufferedImage.TYPE_INT_RGB);

        /*Animaciones*/
        qbert(g);
        this.getGraphics().drawImage(this.animacion, 0, 0, this);
        g.dispose();
    }

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        animacion.getGraphics().drawImage(buffer, x, y, this);

    }

    public void gameboy(){
        BufferedImage temp2 = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        temp2.setData(animacion.getRaster());
        int square [][]={
                {0,0},
                {100,0},
                {100,800},
                {0,800}
        };
        fillFigure(square,Color.white);

        int square2 [][] = {
                {700,0},
                {800,0},
                {800,800},
                {700,800}

        };
        fillFigure(square2,Color.white);

        int square3 [][] = {
                {100,0},
                {700,0},
                {700,100},
                {100,100}

        };
        fillFigure(square3,Color.white);

        int square4 [][] = {
                {100,700},
                {700,700},
                {700,800},
                {100,800}

        };
        fillFigure(square4,Color.white);
        control();
        button();
        button2();
    }




    private void fillFigure(int[][] points,Color color) {
        int n = points.length;
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = Integer.MIN_VALUE;
        int endY = Integer.MIN_VALUE;

        // Find the bounding box of the rotated figure
        for (int i = 0; i < n-1; i++) {
            int x = points[i][0];
            int y = points[i][1];
            startX = Math.min(startX, x);
            startY = Math.min(startY, y);
            endX = Math.max(endX, x);
            endY = Math.max(endY, y);
        }

        int direction = 0; // 0: right, 1: down, 2: left, 3: up
        int x = startX;
        int y = startY;

        while (startX <= endX && startY <= endY) {
            // Paint pixels in the current direction
            while (x >= startX && x <= endX && y >= startY && y <= endY) {
                if (isInsidePolygon(x, y, points)) {
                    putPixel(x, y, color);
                }
                // Move to the next pixel in the current direction
                switch (direction) {
                    case 0:
                        x++;
                        break;
                    case 1:
                        y++;
                        break;
                    case 2:
                        x--;
                        break;
                    case 3:
                        y--;
                        break;
                }
            }

            // Update the bounding box based on the current direction
            switch (direction) {
                case 0:
                    startY++;
                    x--;
                    y++;
                    break;
                case 1:
                    endX--;
                    x--;
                    y--;
                    break;
                case 2:
                    endY--;
                    x++;
                    y--;
                    break;
                case 3:
                    startX++;
                    x++;
                    y++;
                    break;
            }

            // Change direction (right -> down -> left -> up)
            direction = (direction + 1) % 4;
        }
    }
    private boolean isInsidePolygon(int x, int y, int[][] points) {
        int n = points.length;
        boolean isInside = false;

        for (int i = 0, j = n - 1; i < n; j = i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            int xj = points[j][0];
            int yj = points[j][1];

            if (((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                isInside = !isInside;
            }
        }

        return isInside;

    }



    //second scene
    public void lineaDDA(int x1, int y1, int x2, int y2, Color c){
        float dx=x2-x1;
        float dy=y2-y1;
        float steps;
        if(abs(dx)>=abs(dy)){
            steps=abs(dx);
        }else{
            steps=abs(dy);
        }
        float xinc=dx/steps;
        float yinc=dy/steps;

        float x=x1,y=y1;
        for(int i=0;i<=steps;i++){
            putPixel(round(x), round(y), c);
            x+=xinc;
            y+=yinc;
        }
    }




    public void paralelaCubo(Double [][] coordenadas, Double Xa, Double Ya){

        Double [][] fig2d = new Double [8][2];
        Double t;
        Double iz=1000.0;

        for(int i=0;i<8;i++){
            t=-coordenadas[i][2]/d[0][2];
            fig2d[i][0]=coordenadas[i][0]+(t*d[0][0]);
            fig2d[i][1]=coordenadas[i][1]+(t*d[0][1]);
        }

        for(int i=0;i<8;i++){
            if(coordenadas[i][2]<iz){
                iz=coordenadas[i][2];
                indexZ=i;
            }
        }
        //Cubo completo

        if(indexZ!=0&&indexZ!=1){lineaDDA((int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]),(int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]), Color.WHITE);} //A->B
        if(indexZ!=1&&indexZ!=3){lineaDDA((int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]),(int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]), Color.WHITE);} //B->D
        if(indexZ!=2&&indexZ!=3){lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]), Color.WHITE);} //C->D
        if(indexZ!=2&&indexZ!=0){lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]), Color.WHITE);} //C->A
        if(indexZ!=2&&indexZ!=6){lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE);} //C->G
        if(indexZ!=3&&indexZ!=7){lineaDDA((int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]),(int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]), Color.WHITE);} //D->H
        if(indexZ!=0&&indexZ!=4){lineaDDA((int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]),(int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]), Color.WHITE);} //A->E
        if(indexZ!=1&&indexZ!=5){lineaDDA((int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]),(int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]), Color.WHITE);} //B->F
        if(indexZ!=7&&indexZ!=6){lineaDDA((int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE);} //H->G
        if(indexZ!=7&&indexZ!=5){lineaDDA((int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]),(int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]), Color.WHITE);} //H->F
        if(indexZ!=5&&indexZ!=4){lineaDDA((int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]),(int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]), Color.WHITE);} //F->E
        if(indexZ!=4&&indexZ!=6){lineaDDA((int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE);} //E->G
    }

    public void determinarCentros(){

        Double c = a+(b-a)/2;

        centros[0][0]=a;
        centros[0][1]=c;
        centros[0][2]=c;

        centros[1][0]=c;
        centros[1][1]=c;
        centros[1][2]=b;

        centros[2][0]=b;
        centros[2][1]=c;
        centros[2][2]=c;

        centros[3][0]=c;
        centros[3][1]=c;
        centros[3][2]=a;

        centros[4][0]=c;
        centros[4][1]=b;
        centros[4][2]=c;

        centros[5][0]=c;
        centros[5][1]=a;
        centros[5][2]=c;

        centros[0][3]=1.0;
        centros[1][3]=1.0;
        centros[2][3]=1.0;
        centros[3][3]=1.0;
        centros[4][3]=1.0;
        centros[5][3]=1.0;
    }

    public void paraleloCentros(){

        centrosP = new Double [6][2];
        Double t;

        for(int i=0;i<centros.length;i++){

            t = -centros[i][2]/d[0][2];
            centrosP[i][0] = centros[i][0]+(t*d[0][0]);
            centrosP[i][1] = centros[i][1]+(t*d[0][1]);

        }
    }

    public void Rotacion(Double [][] coordenadas, int a, char e){

        Double r[]={0.0,0.0,0.0,0.0};

        Double [][] T=new Double[4][4];

        Double[][] Tx={
                {1.0,0.0,0.0,0.0},
                {0.0,Math.cos(Math.toRadians(a)),Math.sin(Math.toRadians(a)),0.0},
                {0.0,-Math.sin(Math.toRadians(a)),Math.cos(Math.toRadians(a)),0.0},
                {0.0,0.0,0.0,1.0}
        };
        Double[][] Ty={
                {Math.cos(Math.toRadians(a)),0.0,-Math.sin(Math.toRadians(a)),0.0},
                {0.0,1.0,0.0,0.0},
                {Math.sin(Math.toRadians(a)),0.0,Math.cos(Math.toRadians(a)),0.0},
                {0.0,0.0,0.0,1.0}
        };
        Double[][] Tz={
                {Math.cos(Math.toRadians(a)),Math.sin(Math.toRadians(a)),0.0,0.0},
                {-Math.sin(Math.toRadians(a)),Math.cos(Math.toRadians(a)),0.0,0.0},
                {0.0,0.0,1.0,0.0},
                {0.0,0.0,0.0,1.0}
        };

        switch(e){
            case 'x':
                T=Tx;
                break;
            case 'y':
                T=Ty;
                break;
            case 'z':
                T=Tz;
                break;
        }

        for(int m=0;m<coordenadas.length;m++){
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    r[i] += coordenadas[m][j]*T[i][j];
                }
            }

            coordenadas[m][0]=r[0];
            coordenadas[m][1]=r[1];
            coordenadas[m][2]=r[2];
            r[0]=0.0;
            r[1]=0.0;
            r[2]=0.0;
        }

    }

    public void determinarCentrosEsc(){

        Double c = ae+(be-ae)/2;

        centrosEsc[0][0]=ae;
        centrosEsc[0][1]=c;
        centrosEsc[0][2]=c;

        centrosEsc[1][0]=c;
        centrosEsc[1][1]=c;
        centrosEsc[1][2]=be;

        centrosEsc[2][0]=be;
        centrosEsc[2][1]=c;
        centrosEsc[2][2]=c;

        centrosEsc[3][0]=c;
        centrosEsc[3][1]=c;
        centrosEsc[3][2]=ae;

        centrosEsc[4][0]=c;
        centrosEsc[4][1]=be;
        centrosEsc[4][2]=c;

        centrosEsc[5][0]=c;
        centrosEsc[5][1]=ae;
        centrosEsc[5][2]=c;

        centrosEsc[0][3]=1.0;
        centrosEsc[1][3]=1.0;
        centrosEsc[2][3]=1.0;
        centrosEsc[3][3]=1.0;
        centrosEsc[4][3]=1.0;
        centrosEsc[5][3]=1.0;
    }

    public void determinarCentrosT(){

        Double c = at+(bt-at)/2;

        centrosT[0][0]=at;
        centrosT[0][1]=c;
        centrosT[0][2]=c;

        centrosT[1][0]=c;
        centrosT[1][1]=c;
        centrosT[1][2]=bt;

        centrosT[2][0]=bt;
        centrosT[2][1]=c;
        centrosT[2][2]=c;

        centrosT[3][0]=c;
        centrosT[3][1]=c;
        centrosT[3][2]=at;

        centrosT[4][0]=c;
        centrosT[4][1]=bt;
        centrosT[4][2]=c;

        centrosT[5][0]=c;
        centrosT[5][1]=at;
        centrosT[5][2]=c;

        centrosT[0][3]=1.0;
        centrosT[1][3]=1.0;
        centrosT[2][3]=1.0;
        centrosT[3][3]=1.0;
        centrosT[4][3]=1.0;
        centrosT[5][3]=1.0;
    }

    public void inundacion2(int x, int y, Color c,Color ob){
        if((x<this.getWidth()&&y<800)&&(x>0&&y>0)){
            color=ReadPixel(x,y);
            if(color.equals(ob)){
                putPixel(x,y,c);
                inundacion2(x,y+1,c,ob);
                inundacion2(x+1,y,c,ob);
                inundacion2(x,y-1,c,ob);
                inundacion2(x-1,y,c,ob);
            }
        }
    }
    public void inundacion(int x, int y, Color c){
        if((x<this.getWidth()&&y<800)&&(x>0&&y>0)){
            color=ReadPixel(x,y);
            if(color.equals(disponible)){
                putPixel(x,y,c);
                inundacion(x,y+1,c);
                inundacion(x+1,y,c);
                inundacion(x,y-1,c);
                inundacion(x-1,y,c);
            }
        }
    }

    public Color ReadPixel(int x, int y){
        return new Color(animacion.getRGB(x, y));
    }

    public void RellenarCubo(Double Xa,Double Ya){
        if(indexZ!=0&&indexZ!=1&&indexZ!=2&&indexZ!=3)inundacion((int)(Xa-centrosP[0][0]),(int)(Ya-centrosP[0][1]),Color.CYAN);
        if(indexZ!=1&&indexZ!=3&&indexZ!=5&&indexZ!=7)inundacion((int)(Xa-centrosP[1][0]),(int)(Ya-centrosP[1][1]),Color.GRAY);
        if(indexZ!=4&&indexZ!=5&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[2][0]),(int)(Ya-centrosP[2][1]),Color.CYAN);
        if(indexZ!=0&&indexZ!=2&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[3][0]),(int)(Ya-centrosP[3][1]),Color.GRAY);
        if(indexZ!=2&&indexZ!=3&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[4][0]),(int)(Ya-centrosP[4][1]),Color.YELLOW);
        if(indexZ!=0&&indexZ!=1&&indexZ!=4&&indexZ!=5)inundacion((int)(Xa-centrosP[5][0]),(int)(Ya-centrosP[5][1]),Color.YELLOW);
    }

    /*Metodos Qbert*/

    public void qbert(Graphics g){
        animacionQbert(g);
    }

    public void animacionQbert(Graphics g){

        BufferedImage temp = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        temp.setData(animacion.getRaster());

        for(int i=1;i<2;i++){

            animacion = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
            animacion.setData(temp.getRaster());
            gameboy();
            //control();

            if(incX<1.074){
                System.out.println(incX);
                cubes();

                this.time+= 1000/60;
            }else{
                Rotacion(this.coordenadas,1,'y');
                Rotacion(this.centros,1,'y');
                paraleloCentros();
                paralelaCubo(this.coordenadas,200.0,275.0);
                RellenarCubo(200.0,275.0);
                cubosQbert();//Estructura del nivel
                cuberto();
                time = 1;
            }

            this.getGraphics().drawImage(this.animacion, 0, 0, this);
        }
    }
    private void rutina(){
        if(this.r1){
            cubertoIncZ=0;
            cubertoIncX=0;
            if(cubertoIncY<4.0999985){
                cubertoIncY+=0.1;
               //System.out.println(cubertoIncY);
                location3D=transformations.translation3D(cubertoIncX,cubertoIncY,cubertoIncZ,location3D);
                return;
            }else{
                this.r1=false;
                this.r2 = true;
                espacios[0][0]=1;
                cubertoIncZ=0;
                cubertoIncX=0;
                cubertoIncY = 0;
                try {
                    sleep(120);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if(r2 && contador<150){
            //location3D=transformations.translation3D(1,-2,cubertoIncZ,location3D);
            location3D=transformations.translation3D(1,2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador == 50){
                espacios[0][1]=1;
            }
            if(contador == 110){
                espacios[0][2]=1;
            }
            if(contador==150){
                espacios[0][3]=1;
                r3=true;
                r2=false;
                contador=0;
            }
        }
        else if(r3 && contador<150){
            location3D=transformations.translation3D(-1,-2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador==150){
                r4=true;
                r3=false;
                contador = 0;
            }
        }else if(r4 && contador<126){
            location3D=transformations.translation3D(-1,2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador == 31){
                espacios[1][0]=1;
            }
            if(contador == 62){
                espacios[2][0]=1;
            }
            if(contador==126){
                r5=true;
                r4=false;
                contador=0;
                espacios[3][0]=1;
            }
        }else if(r5 && contador<85){
            location3D=transformations.translation3D(1,-2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador==85){
                r6=true;
                r5=false;
                contador=0;
            }
        }
        else if(r6 && contador<100){
            location3D=transformations.translation3D(1,2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador == 50){
                espacios[1][1]=1;
            }
            if(contador==100){
                r7=true;
                r6=false;
                contador=0;
                espacios[1][2] =1;
            }
        }else if(r7 && contador<50){
            location3D=transformations.translation3D(-1,-2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador==50){
                r8=true;
                r7=false;
                contador=0;
            }
        }
        else if(r8 && contador<40){
            location3D=transformations.translation3D(-1,2,cubertoIncZ,location3D);
            contador++;
            System.out.println(contador);
            if(contador==40){
                r8=false;
                finScena = true;
                espacios[2][1]=1;
                contador=0;
            }
        }
    }

    public void cubosQbert(){
        double initialX = 400.0; // Coordenada X inicial para la primera figura
        double initialY = 300.0; // Coordenada Y inicial para la primera figura
        double deltaY = 99; // Espaciado vertical entre filas
        int cuadros = 4;
        if(!finScena){
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < cuadros; i++) { // Generar tres filas
                    paraleloCentros(); // Incremento de X para cada fila
                    paralelaCubo(coorCubos, initialX + (i * 50.0), initialY + (i * deltaY)); // Dibujo de la figura en la fila

                    // Calculando los valores para inundacion()
                    int x = (int) (initialX + (i * 50.0)) + 1;
                    int y1 = (int) (initialY + (i * deltaY)) - 5;
                    int y2 = (int) (initialY + (i * deltaY)) + 5;
                    if(espacios[j][i]!=0){
                        inundacion(x, y1, Color.YELLOW);
                        inundacion(x, y2, Color.GRAY);
                        inundacion(x - 5, y2, Color.CYAN);
                    }else{
                        inundacion(x, y1, Color.BLUE);
                        inundacion(x, y2, Color.PINK);
                        inundacion(x - 5, y2, Color.white);
                    }

                }
                initialX-=45;
                initialY+=78;
                cuadros --;
            }
        }else{
            if(colorido){
                colorido = false;
            }else{
                colorido = true;
            }

            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < cuadros; i++) { // Generar tres filas
                    paraleloCentros(); // Incremento de X para cada fila
                    paralelaCubo(coorCubos, initialX + (i * 50.0), initialY + (i * deltaY)); // Dibujo de la figura en la fila

                    // Calculando los valores para inundacion()
                    int x = (int) (initialX + (i * 50.0)) + 1;
                    int y1 = (int) (initialY + (i * deltaY)) - 5;
                    int y2 = (int) (initialY + (i * deltaY)) + 5;
                    if(colorido){
                        inundacion(x, y1, Color.YELLOW);
                        inundacion(x, y2, Color.GRAY);
                        inundacion(x - 5, y2, Color.CYAN);
                    }else{
                        inundacion(x, y1, Color.BLUE);
                        inundacion(x, y2, Color.PINK);
                        inundacion(x - 5, y2, Color.white);
                    }

                }
                initialX-=45;
                initialY+=78;
                cuadros --;
            }
        }


    }
    private void loading(){


    }
    public static void main(String[] args){
        new Animation3D();
    }

    @Override
    public void run(){
        while (true) {
            try {
                incX += .001;
                incY = 1;
                incZ = 1;

                repaint();
                sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("algo salio mal");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    private void cubes(){
        for (int i = 0; i < pointsXYZ.size(); i++) {
            float u = (float) (pointsXYZ.get(i).pointZ) / vector.pointZ;
            float x = (pointsXYZ.get(i).pointX + (vector.pointX * u)) / 3;
            float y = (pointsXYZ.get(i).pointY + (vector.pointY * u)) / 3;
            pointsXY.add(new Location((int) x + 80, (int) y + 300));
        }
        pointsXYZ.set(0,new Location3D(50, 150, 50));
        pointsXYZ.set(2,new Location3D(50, 250, 50));
        pointsXYZ.set(4,new Location3D(50, 150, 150));
        pointsXYZ.set(6,new Location3D(50, 250, 150));

        pointsXYZ = Transformations.Escalation3D(incX, incY, incZ, pointsXYZ);
        for (Location point : pointsXY) {
            putPixel(point.pointX, point.pointY,Color.black);
        }

        int square[][] = {
                {pointsXY.get(0).pointX, pointsXY.get(0).pointY},
                {pointsXY.get(1).pointX, pointsXY.get(1).pointY},
                {pointsXY.get(5).pointX, pointsXY.get(5).pointY},
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY}
        };
        fillFigure(square, Color.BLUE);
        int square2[][] = {
                {pointsXY.get(0).pointX, pointsXY.get(0).pointY},
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY},
                {pointsXY.get(6).pointX, pointsXY.get(6).pointY},
                {pointsXY.get(2).pointX, pointsXY.get(2).pointY}

        };
        fillFigure(square2, Color.white);
        int square3[][] = {
                {pointsXY.get(4).pointX, pointsXY.get(4).pointY},
                {pointsXY.get(5).pointX, pointsXY.get(5).pointY},
                {pointsXY.get(7).pointX, pointsXY.get(7).pointY},
                {pointsXY.get(6).pointX, pointsXY.get(6).pointY}

        };
        fillFigure(square3, Color.GRAY);
        pointsXY.clear();
    }
    private void button2(){
        ArrayList<Location3D> location3DS = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();
        //primera cara
        location3DS.add(new Location3D(10,40 ,2 ));//a
        location3DS.add(new Location3D(40,40 ,2 ));//b
        location3DS.add(new Location3D(40,10 ,2 ));//c
        location3DS.add(new Location3D(10,10 ,2 ));//d
        //segunda cara
        location3DS.add(new Location3D(10,40 ,12 ));//e
        location3DS.add(new Location3D(40,40 ,12 ));//f
        location3DS.add(new Location3D(40,10 ,12 ));//g
        location3DS.add(new Location3D(10,10 ,12 ));//h

        for (int i = 0; i < location3DS.size(); i++){
            float u = 0;
            float y = location3DS.get(i).pointY + (vector.pointY * u);
            float z = location3DS.get(i).pointX + (vector.pointX * u);

            locations.add(new Location((int) ((int)700.0+y), (int) ((int)460.0+z)));
        }
        for (Location point: locations){
            putPixel(point.pointX, point.pointY,Color.red);
        }
        ArrayList<Location> pointsAB = g.bresenham(locations.get(0),locations.get(1));
        ArrayList<Location> pointsBC = g.bresenham(locations.get(1),locations.get(2));
        ArrayList<Location> pointsCD = g.bresenham(locations.get(2),locations.get(3));
        ArrayList<Location> pointsDA = g.bresenham(locations.get(3), locations.get(0));

        ArrayList<Location> pointsAB1 = g.bresenham(locations.get(4),locations.get(5));
        ArrayList<Location> pointsBC1 = g.bresenham(locations.get(5),locations.get(6));
        ArrayList<Location> pointsCD1 = g.bresenham(locations.get(6),locations.get(7));
        ArrayList<Location> pointsDA1 = g.bresenham(locations.get(7), locations.get(4));

        ArrayList<Location> pointsA = g.bresenham(locations.get(0),locations.get(4));
        ArrayList<Location> pointsB = g.bresenham(locations.get(1),locations.get(5));
        ArrayList<Location> pointsC = g.bresenham(locations.get(2),locations.get(6));
        ArrayList<Location> pointsD = g.bresenham(locations.get(3), locations.get(7));


        ArrayList<Location> points = pointsAB;
        points.addAll(pointsBC);
        points.addAll(pointsCD);
        points.addAll(pointsDA);
        //second cara
        points.addAll(pointsAB1);
        points.addAll(pointsBC1);
        points.addAll(pointsCD1);
        points.addAll(pointsDA1);

        points.addAll(pointsA);
        points.addAll(pointsB);
        points.addAll(pointsC);
        points.addAll(pointsD);

        Location location = new Location(2,3);
        for (Location point: points){
            putPixel(point.pointX, point.pointY,Color.ORANGE);
            location.setPointX(point.pointX);
            location.setPointY(point.pointY);
        }
        inundacion2(location.pointX+3,location.pointY+3,Color.ORANGE,Color.white);

    }
    private void cuberto(){
        rutina();
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = 0; i < location3D.size(); i++){
            float u =  (float) (location3D.get(i).pointZ) / vector.pointZ;
            float x = location3D.get(i).pointX + (vector.pointX * u);
            float y = location3D.get(i).pointY + (vector.pointY * u);

            locations.add(new Location((int) x+370, (int) y+150));
        }
        Location location = new Location(2,3);
        for (Location point: locations){
            putPixel(point.pointX, point.pointY,Color.red);
            location.setPointY(point.pointY);
            location.setPointX(point.pointX);
        }
        putPixel(location.pointX, location.pointY,Color.white);
        int square[][] = {
                {locations.get(0).pointX, locations.get(0).pointY},
                {locations.get(1).pointX, locations.get(1).pointY},
                {locations.get(5).pointX, locations.get(5).pointY},
                {locations.get(4).pointX, locations.get(4).pointY}
        };
        fillFigure(square, Color.magenta);
        int square2[][] = {
                {locations.get(0).pointX, locations.get(0).pointY},
                {locations.get(4).pointX, locations.get(4).pointY},
                {locations.get(6).pointX, locations.get(6).pointY},
                {locations.get(2).pointX, locations.get(2).pointY}

        };
        fillFigure(square2, Color.PINK);
        int square3[][] = {
                {locations.get(4).pointX, locations.get(4).pointY},
                {locations.get(5).pointX, locations.get(5).pointY},
                {locations.get(7).pointX, locations.get(7).pointY},
                {locations.get(6).pointX, locations.get(6).pointY}

        };
        fillFigure(square3, Color.red);
        pointsXY.clear();
        inundacion2(location.pointX,location.pointY,Color.YELLOW,Color.blue);
    }
    private void button(){
        ArrayList<Location3D> location3DS = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();
        //primera cara
        location3DS.add(new Location3D(10,40 ,2 ));//a
        location3DS.add(new Location3D(40,40 ,2 ));//b
        location3DS.add(new Location3D(40,10 ,2 ));//c
        location3DS.add(new Location3D(10,10 ,2 ));//d
        //segunda cara
        location3DS.add(new Location3D(10,40 ,12 ));//e
        location3DS.add(new Location3D(40,40 ,12 ));//f
        location3DS.add(new Location3D(40,10 ,12 ));//g
        location3DS.add(new Location3D(10,10 ,12 ));//h

        for (int i = 0; i < location3DS.size(); i++){
            float u = 0;
            float y = location3DS.get(i).pointY + (vector.pointY * u);
            float z = location3DS.get(i).pointX + (vector.pointX * u);

            locations.add(new Location((int) ((int)730.0+y), (int) ((int)420.0+z)));
        }
        for (Location point: locations){
            putPixel(point.pointX, point.pointY,Color.red);
        }
        ArrayList<Location> pointsAB = g.bresenham(locations.get(0),locations.get(1));
        ArrayList<Location> pointsBC = g.bresenham(locations.get(1),locations.get(2));
        ArrayList<Location> pointsCD = g.bresenham(locations.get(2),locations.get(3));
        ArrayList<Location> pointsDA = g.bresenham(locations.get(3), locations.get(0));

        ArrayList<Location> pointsAB1 = g.bresenham(locations.get(4),locations.get(5));
        ArrayList<Location> pointsBC1 = g.bresenham(locations.get(5),locations.get(6));
        ArrayList<Location> pointsCD1 = g.bresenham(locations.get(6),locations.get(7));
        ArrayList<Location> pointsDA1 = g.bresenham(locations.get(7), locations.get(4));

        ArrayList<Location> pointsA = g.bresenham(locations.get(0),locations.get(4));
        ArrayList<Location> pointsB = g.bresenham(locations.get(1),locations.get(5));
        ArrayList<Location> pointsC = g.bresenham(locations.get(2),locations.get(6));
        ArrayList<Location> pointsD = g.bresenham(locations.get(3), locations.get(7));


        ArrayList<Location> points = pointsAB;
        points.addAll(pointsBC);
        points.addAll(pointsCD);
        points.addAll(pointsDA);
        //second cara
        points.addAll(pointsAB1);
        points.addAll(pointsBC1);
        points.addAll(pointsCD1);
        points.addAll(pointsDA1);

        points.addAll(pointsA);
        points.addAll(pointsB);
        points.addAll(pointsC);
        points.addAll(pointsD);

        Location location = new Location(2,3);
        for (Location point: points){
            putPixel(point.pointX, point.pointY,Color.red);
            location.setPointX(point.pointX);
            location.setPointY(point.pointY);
        }


        inundacion2(location.pointX+3,location.pointY+3,Color.red,Color.white);

    }
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_A:
                x-= 10;
                repaint();
                break;
            case KeyEvent.VK_D:
                x+= 10;
                repaint();
                break;
            case KeyEvent.VK_W:
                y-= 10;
                repaint();
                break;
            case KeyEvent.VK_S:
                y+= 10;
                repaint();
                break;
            case KeyEvent.VK_Q:
                z+= 10;
                repaint();
                break;
            case KeyEvent.VK_E:
                z-= 40;
                repaint();
                break;
            default:
                break;
        }
    }



    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}