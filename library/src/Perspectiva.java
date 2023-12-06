import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.abs;
import static java.lang.Math.round;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Perspectiva extends JFrame implements Runnable{

    private BufferedImage buffer, animacion,temp;
    private Thread hilo;
    private Color color, disponible;


    Double a=-20.0,b=20.0;
    Double [][] coordenadas={   {a,a,a,1.0}, //A0
            {a,a,b,1.0}, //B1
            {a,b,a,1.0}, //C2
            {a,b,b,1.0}, //D3
            {b,a,a,1.0}, //E4
            {b,a,b,1.0}, //F5
            {b,b,a,1.0}, //G6
            {b,b,b,1.0}, //H7
    };

    Double [][] d={ {200.0,200.0,300.0},
            {200.0,200.0,-300.0},
            {-200.0,-200.0,300.0}
    };

    Double [][] ref={{a,b,1.0,1.0}};
    Double Xa=300.0,Ya=400.0;
    int indexZ;
    private boolean animarCubo=false;
    Double [][] auxCoordenadas=new Double[8][4];
    Double [][] centros,centrosP,auxCentros=new Double[6][4];


    public Perspectiva(){

        setTitle("Transformaciones");
        setSize(600,600);
        setLayout(null);

        buffer=new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        animacion=new BufferedImage(600,500,BufferedImage.TYPE_INT_RGB);
        disponible=new Color(0,0,0);

        centros=new Double[6][4];
        determinarCentros();
        auxCoordenadas=coordenadas.clone();
        auxCentros=centros.clone();


        JButton boton=new JButton("Animar");
        boton.setBounds(200,490,100,50);
        add(boton);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                animarCubo=true;
                System.out.println(animarCubo);
            }
        });

        hilo=new Thread(this);
        hilo.start();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g){

        animacion=new BufferedImage(this.getWidth(),500,BufferedImage.TYPE_INT_RGB);

        if(animarCubo){
            animRotar(g);
            //animTrasladar(g);
            //animEscalar(g);
        }
        this.getGraphics().drawImage(this.animacion, 0, 0, this);

    }

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0, 0, c.getRGB());
        animacion.getGraphics().drawImage(buffer, x, y, this);

    }

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

        /*
        //Sin relleno
        lineaDDA((int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]),(int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]), Color.WHITE); //A->B
        lineaDDA((int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]),(int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]), Color.WHITE); //B->D
        lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]), Color.WHITE); //C->D
        lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]), Color.WHITE); //C->A
        lineaDDA((int)(Xa-fig2d[2][0]),(int)(Ya-fig2d[2][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE); //C->G
        lineaDDA((int)(Xa-fig2d[3][0]),(int)(Ya-fig2d[3][1]),(int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]), Color.WHITE); //D->H
        lineaDDA((int)(Xa-fig2d[0][0]),(int)(Ya-fig2d[0][1]),(int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]), Color.WHITE); //A->E
        lineaDDA((int)(Xa-fig2d[1][0]),(int)(Ya-fig2d[1][1]),(int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]), Color.WHITE); //B->F
        lineaDDA((int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE); //H->G
        lineaDDA((int)(Xa-fig2d[7][0]),(int)(Ya-fig2d[7][1]),(int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]), Color.WHITE); //H->F
        lineaDDA((int)(Xa-fig2d[5][0]),(int)(Ya-fig2d[5][1]),(int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]), Color.WHITE); //F->E
        lineaDDA((int)(Xa-fig2d[4][0]),(int)(Ya-fig2d[4][1]),(int)(Xa-fig2d[6][0]),(int)(Ya-fig2d[6][1]), Color.WHITE); //E->G
        */
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

    public void paraleloCentros(Double Xa, Double Ya){

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

    public void Traslacion(Double [][] coordenadas, int tx,int ty,int tz){
        Double r[]={0.0,0.0,0.0,0.0};

        double[][] T={
                {1.0,0.0,0.0,tx},
                {0.0,1.0,0.0,ty},
                {0.0,0.0,1.0,tz},
                {0.0,0.0,0.0,1.0}
        };

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

        if(coordenadas.length==8){
            a=coordenadas[1][0];
            b=coordenadas[1][2];
        }
    }

    public void Escalacion(Double [][] coordenadas, double sx,double sy,double sz){
        Double r[]={0.0,0.0,0.0,0.0};

        double[][] T={
                {sx,0.0,0.0,0.0},
                {0.0,sy,0.0,0.0},
                {0.0,0.0,sz,0.0},
                {0.0,0.0,0.0,1.0}
        };

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
        if(coordenadas.length==8){
            a=coordenadas[1][0];
            b=coordenadas[1][2];
        }

    }


    public void animRotar(Graphics g){

        BufferedImage temp = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        temp.setData(animacion.getRaster());

        for(int i=1;i<361;i++){
            animacion = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            animacion.setData(temp.getRaster());
            try{
                Rotacion(this.coordenadas,1,'x');
                Rotacion(this.centros,1,'x');
                paraleloCentros(300.0,400.0);
                paralelaCubo(this.coordenadas,300.0,400.0);
                inundarCubo();
                this.getGraphics().drawImage(this.animacion, 0, 0, this);
                hilo.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Perspectiva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.coordenadas=new Double[8][4];
        this.centros=new Double[6][4];
        this.coordenadas=auxCoordenadas.clone();
        this.centros=this.auxCentros.clone();

        for(int i=1;i<361;i++){
            animacion = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            animacion.setData(temp.getRaster());
            try{
                Rotacion(this.coordenadas,1,'y');
                Rotacion(this.centros,1,'y');

                paraleloCentros(300.0,400.0);
                paralelaCubo(this.coordenadas,300.0,400.0);

                //inundarCubo();
                this.getGraphics().drawImage(this.animacion, 0, 0, this);
                hilo.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Perspectiva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.coordenadas=auxCoordenadas.clone();
        this.centros=this.auxCentros.clone();

       //Sin rellenar
        for(int i=1;i<361;i++){
            animacion = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            animacion.setData(temp.getRaster());
            try{
                Rotacion(this.coordenadas,1,'z');
                Rotacion(this.centros,1,'z');

                paraleloCentros(300.0,400.0);
                paralelaCubo(this.coordenadas,300.0,400.0);

                inundarCubo();
                this.getGraphics().drawImage(this.animacion, 0, 0, this);
                hilo.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Perspectiva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void inundacion(int x, int y, Color c){
        if((x<this.getWidth()&&y<600)&&(x>0&&y>0)){
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

    public void inundarCubo(){
        System.out.println(indexZ);
        if(indexZ!=0&&indexZ!=1&&indexZ!=2&&indexZ!=3)inundacion((int)(Xa-centrosP[0][0]),(int)(Ya-centrosP[0][1]),Color.WHITE);
        if(indexZ!=1&&indexZ!=3&&indexZ!=5&&indexZ!=7)inundacion((int)(Xa-centrosP[1][0]),(int)(Ya-centrosP[1][1]),Color.BLUE);
        if(indexZ!=4&&indexZ!=5&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[2][0]),(int)(Ya-centrosP[2][1]),Color.RED);
        if(indexZ!=0&&indexZ!=2&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[3][0]),(int)(Ya-centrosP[3][1]),Color.YELLOW);
        if(indexZ!=2&&indexZ!=3&&indexZ!=6&&indexZ!=7)inundacion((int)(Xa-centrosP[4][0]),(int)(Ya-centrosP[4][1]),Color.ORANGE);
        if(indexZ!=0&&indexZ!=1&&indexZ!=4&&indexZ!=5)inundacion((int)(Xa-centrosP[5][0]),(int)(Ya-centrosP[5][1]),Color.GREEN);
    }

    public static void main(String[] args){
        new Perspectiva();
    }

    @Override
    public void run(){
        while(true){
            try{
                repaint();
                hilo.sleep(100);
            }catch(InterruptedException ex){
            }
        }
    }
}
