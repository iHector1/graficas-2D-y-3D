import javax.swing.*;
import java.awt.*;

public class Finals extends JPanel {
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(83, 162, 190));
        g.fillRect(0, 0, getWidth(), getHeight());
        // Dibuja el sol
        g.setColor(Color.YELLOW);
        g.fillOval(getWidth() - 150, 50, 100, 100);
        //nubes
        g.setColor(Color.WHITE);
        g.fillOval(200, 100, 80, 40);
        g.fillOval(200 + 50, 100, 80, 40);
        g.fillOval(440, 100, 80, 40);
        g.fillOval(440 + 50, 100, 80, 40);

        // Draw green grass background
        g.setColor(new Color(50,83,61));
        g.fillRect(0, 250, 700, 300);
//ROOF

        g.drawRect(395,130,70,33);
        g.drawRect(400,136,65,20);

        g.setColor(Color.darkGray);
        g.fillRect(395,130,70,33);
        g.fillRect(400,136,65,20);

        g.draw3DRect(395,130,70,5,true);

        g.drawRect(395,155,70,8);
        g.setColor(Color.gray);
        g.fillRect(395,155,70,8);
        g.fill3DRect(395,130,70,5,true);

        int x[] = {50,180,182,265,353,353,425,425,372,372,100,100,50,50};
        int y[] = {162,162,155,88,155,162,162,155,118,70,70,118,155,162};
        int pts = x.length;
        g.drawPolygon(x,y,pts);
        g.setColor(Color.darkGray);
        g.fillPolygon(x,y,pts);

        g.setColor(Color.black);
        int x2[] = {180,180,219,255,353,353,242,242,219,194,194,180};
        int y2[] = {162,155,125,155,155,162,162,152,134,152,162,162};
        int pts2 = x2.length;
        g.setColor(Color.gray);
        g.drawPolygon(x2,y2,pts2);
        g.fillPolygon(x2,y2,pts2);

        int x3[] = {265,218,223,265,342,353,265};
        int y3[] = {88,125,130,97,155,155,88};
        int pts3 = x3.length;
        g.setColor(Color.gray);
        g.drawPolygon(x3,y3,pts3);
        g.fillPolygon(x3,y3,pts3);

        g.draw3DRect(100,70,272,7,true);
        g.drawRect(50,155,130,7);
        g.drawRect(257,155,168,7);

        g.setColor(Color.gray);
        g.fill3DRect(100,70,272,7,true);
        g.fillRect(50,155,130,8);
        g.fillRect(257,155,168,7);


        g.setColor(Color.black);
        g.drawLine(56,150,56,154);
        g.drawLine(60,147,60,154);
        g.drawLine(65,145,65,154);
        g.drawLine(70,140,70,154);
        g.drawLine(75,136,75,154);
        g.drawLine(80,133,80,154);
        g.drawLine(85,130,85,154);
        g.drawLine(90,126,90,154);
        g.drawLine(95,123,95,154);
        g.drawLine(100,119,100,154);
        g.drawLine(105,78,105,154);
        g.drawLine(110,78,110,154);
        g.drawLine(115,78,115,154);
        g.drawLine(120,78,120,154);
        g.drawLine(125,78,125,154);
        g.drawLine(130,78,130,154);
        g.drawLine(135,78,135,154);
        g.drawLine(140,78,140,154);
        g.drawLine(145,78,145,154);
        g.drawLine(150,78,150,154);
        g.drawLine(155,78,155,154);
        g.drawLine(160,78,160,154);
        g.drawLine(165,78,165,154);
        g.drawLine(170,78,170,154);
        g.drawLine(175,78,175,154);
        g.drawLine(180,78,180,154);
        g.drawLine(185,78,185,150);
        g.drawLine(190,78,190,145);
        g.drawLine(195,78,195,141);
        g.drawLine(200,78,200,138);
        g.drawLine(205,78,205,134);
        g.drawLine(210,78,210,130);
        g.drawLine(215,78,215,125);
        g.drawLine(220,78,220,121);
        g.drawLine(225,78,225,117);
        g.drawLine(230,78,230,113);
        g.drawLine(235,78,235,110);
        g.drawLine(240,78,240,105);
        g.drawLine(245,78,245,102);
        g.drawLine(250,78,250,99);
        g.drawLine(255,78,255,95);
        g.drawLine(260,78,260,90);
        g.drawLine(265,78,265,86);
        g.drawLine(270,78,270,90);
        g.drawLine(275,78,275,95);
        g.drawLine(280,78,280,99);
        g.drawLine(285,78,285,102);
        g.drawLine(290,78,290,105);
        g.drawLine(295,78,295,110);
        g.drawLine(300,78,300,113);
        g.drawLine(305,78,305,117);
        g.drawLine(310,78,310,121);
        g.drawLine(315,78,315,125);
        g.drawLine(320,78,320,129);
        g.drawLine(325,78,325,131);
        g.drawLine(330,78,330,136);
        g.drawLine(335,78,335,138);
        g.drawLine(340,78,340,143);
        g.drawLine(345,78,345,148);
        g.drawLine(350,78,350,152);
        g.drawLine(355,78,355,154);
        g.drawLine(360,78,360,154);
        g.drawLine(366,78,366,154);
        g.drawLine(372,119,372,154);
        g.drawLine(377,122,377,154);
        g.drawLine(383,127,383,154);
        g.drawLine(389,130,389,154);

        g.drawLine(405,136,405,154);
        g.drawLine(410,136,410,154);
        g.drawLine(415,136,415,154);
        g.drawLine(420,136,420,154);
        g.drawLine(425,136,425,154);
        g.drawLine(430,136,430,154);
        g.drawLine(435,136,435,154);
        g.drawLine(440,136,440,154);
        g.drawLine(445,136,445,154);
        g.drawLine(450,136,450,154);
        g.drawLine(455,136,455,154);
        g.drawLine(395,135,395,154);
        g.drawLine(400,136,400,154);
        g.drawLine(405,136,405,154);
        g.drawLine(460,136,460,154);
        g.drawLine(460,136,460,154);
        g.drawLine(372,78,372,117);
        g.drawLine(372,118,425,155);
        g.drawLine(425,155,425,163);
        g.drawLine(425,163,243,163);
        g.drawLine(50,163,193,163);


//BODY LEFT
        g.setColor(Color.orange);
        g.drawRect(412,173,53,88);
        g.fillRect(412,173,53,88);

        g.setColor(Color.lightGray);
        int x4[] = {412,443,465,465,412};
        int y4[] = {240,240,245,261,261};
        int pts4 = x4.length;
        g.drawPolygon(x4,y4,pts4);
        g.fillPolygon(x4,y4,pts4);


//BODY FRONT LEFT SIDE
        g.setColor(Color.black);

        g.drawRect(342,170,70,85);
        g.setColor(Color.orange);
        g.fillRect(342,170,70,85);
        g.setColor(Color.black);

        g.drawLine(342,241,412,241);
        g.drawRect(342,242,70,13);
        g.setColor(Color.lightGray);
        g.fillRect(342,242,70,13);

        g.setColor(Color.black);
        g.drawRect(342,180,70,10);
        g.drawRect(342,190,70,10);
        g.drawRect(342,200,70,10);
        g.drawRect(342,210,70,10);
        g.drawRect(342,220,70,10);

        g.drawRect(360,170,20,40);
        g.setColor(Color.darkGray);
        g.fillRect(360,170,20,40);

        g.setColor(Color.black);
        int x13[] = {355,355,360,355,355,360,360,355};
        int y13[] = {170,189,191,193,208,208,170,170};
        int pts13 = x13.length;
        g.drawPolygon(x13,y13,pts13);

        int x14[] = {385,385,380,385,385,380,380,385};
        int y14[] = {170,189,191,193,208,208,170,170};
        int pts14 = x14.length;
        g.drawPolygon(x14,y14,pts14);
        g.setColor(Color.lightGray);
        g.fillPolygon(x13,y13,pts13);
        g.fillPolygon(x14,y14,pts14);

        g.setColor(Color.yellow);
        g.drawRect(360,170,20,15);
        g.drawRect(360,198,20,12);
        g.drawLine(370,170,370,210);

        g.setColor(Color.black);
        g.drawLine(360,170,360,210);
        g.drawLine(360,210,380,210);
        g.drawLine(380,170,380,210);


//BODY FRONT CENTER
        g.setColor(Color.black);

        g.draw3DRect(194,171,148,70,true);
        g.setColor(Color.lightGray);
        g.fill3DRect(194,171,148,70,true);

        g.setColor(Color.orange);

        g.draw3DRect(200,173,135,75,true);
        g.fill3DRect(200,173,135,75,true);

        g.setColor(Color.black);
        g.drawRect(240,173,5,75);
        g.drawRect(288,173,5,75);
        g.setColor(Color.lightGray);
        g.fillRect(240,173,5,75);
        g.fillRect(288,173,5,75);

        int x15[]= {195,195,242,242,341,341,195};
        int y15[]= {163,156,156,163,163,171,171};
        int pts15 = x15.length;
        g.drawPolygon(x15,y15,pts15);
        g.fillPolygon(x15,y15,pts15);

//door
        g.setColor(Color.black);
        g.drawRect(205,177,20,67);
        g.drawRect(207,183,15,55);
        g.setColor(Color.lightGray);
        g.fillRect(205,177,20,67);
        g.setColor(Color.darkGray);
        g.fillRect(207,183,15,55);


        g.setColor(Color.yellow);
        g.drawLine(214,184,214,236);
//g.drawLine(216,184,216,236);
        g.drawLine(208,195,220,195);
        g.drawLine(208,205,220,205);
        g.drawLine(208,215,220,215);
        g.drawLine(208,225,220,225);

//window
        g.setColor(Color.black);
        g.drawRect(250,177,30,40);
        g.drawRect(315,183,15,30);
        g.setColor(Color.darkGray);
        g.fillRect(250,177,30,40);
        g.fillRect(315,183,15,30);

        g.setColor(Color.yellow);
        g.drawLine(257,177,257,215);
        g.drawLine(263,177,263,215);
        g.drawLine(268,177,268,215);
        g.drawLine(273,177,273,215);
        g.drawLine(250,190,280,190);
        g.drawLine(250,205,280,205);

        g.setColor(Color.black);
        g.drawLine(231,174,231,240);
        g.drawLine(231,180,239,180);
        g.drawLine(231,190,239,190);
        g.drawLine(231,200,239,200);
        g.drawLine(231,210,239,210);
        g.drawLine(231,220,239,220);
        g.drawLine(231,230,239,230);


        g.drawRect(313,177,20,68);

        g.setColor(Color.yellow);
        g.drawLine(315,193,330,193);
        g.drawLine(315,204,330,204);
        g.drawLine(320,183,320,213);
        g.drawLine(325,183,325,213);


//BODY FRONT RIGHT SIDE
        g.setColor(Color.black);

        g.draw3DRect(62,170,132,85,true);
        g.setColor(Color.orange);
        g.fill3DRect(62,170,132,85,true);

        g.setColor(Color.black);

        g.drawRect(62,180,132,10);
        g.drawRect(62,190,132,10);
        g.drawRect(62,200,132,10);
        g.drawRect(62,210,132,10);
        g.drawRect(62,220,132,10);

        g.drawLine(62,163,62,255);
        g.drawLine(62,241,192,241);
        g.drawRect(63,242,132,14);
        g.setColor(Color.lightGray);
        g.fillRect(63,242,132,14);

        //WINDOWS
        g.setColor(Color.black);
        int x9[] = {95,95,100,95,95,100,100,95};
        int y9[] = {170,189,191,193,208,208,170,170};
        int pts9 = x9.length;
        g.drawPolygon(x9,y9,pts9);
        int x10[] = {125,125,120,125,125,120,120,125};
        int y10[] = {170,189,191,193,208,208,170,170};
        int pts10 = x10.length;
        g.drawPolygon(x10,y10,pts10);

        g.drawRect(148,170,40,53);
        g.drawRect(153,170,30,42);

        g.setColor(Color.lightGray);
        g.fillPolygon(x9,y9,pts9);
        g.fillPolygon(x10,y10,pts10);
        g.fillRect(148,170,40,53);

        g.drawRect(100,170,20,40);
        g.setColor(Color.darkGray);
        g.fillRect(100,170,20,40);
        g.fillRect(153,170,30,42);

        g.setColor(Color.yellow);
        g.drawRect(100,170,20,15);
        g.drawRect(100,197,20,12);
        g.drawLine(110,170,110,210);
        g.drawRect(153,170,29,15);
        g.drawRect(153,197,29,15);
        g.drawLine(168,170,168,212);
        g.drawLine(153,170,153,212);

        g.setColor(Color.black);
        g.drawLine(100,170,100,210);
        g.drawLine(120,170,120,210);
        g.drawLine(153,170,153,212);
        g.drawLine(153,212,182,212);
        g.drawLine(182,170,182,212);

        g.drawLine(308,174,308,240);
        g.drawLine(294,180,307,180);
        g.drawLine(294,190,307,190);
        g.drawLine(294,200,307,200);
        g.drawLine(294,210,307,210);
        g.drawLine(294,220,307,220);
        g.drawLine(294,230,307,230);

        g.drawRect(245,220,42,21);
        g.setColor(Color.orange);
        g.fillRect(245,220,42,21);

        g.setColor(Color.black);
        g.drawRect(248,220,10,21);
        g.drawRect(253,220,10,21);
        g.drawRect(258,220,10,21);
        g.drawRect(263,220,10,21);
        g.drawRect(268,220,10,21);
        g.drawRect(273,220,10,21);
        g.drawRect(245,220,42,10);
        g.drawLine(245,225,286,225);
        g.drawLine(245,235,286,235);

        int x11[] = {153,153,155,156,156,153};
        int y11[] = {223,238,240,238,223,223};
        int pts11 = x11.length;
        g.drawPolygon(x11,y11,pts11);
        int x12[] = {180,180,182,183,183,180};
        int y12[] = {223,238,240,238,223,223};
        int pts12 = x12.length;
        g.drawPolygon(x12,y12,pts12);
        g.setColor(Color.yellow);
        g.fillPolygon(x11,y11,pts11);
        g.fillPolygon(x12,y12,pts12);

//BODY FRONT CENTER
        g.setColor(Color.black);

        g.draw3DRect(194,242,150,20,true);
        g.setColor(Color.lightGray);
        g.fill3DRect(194,242,150,20,true);

        g.setColor(Color.black);

        g.drawRoundRect(187,247,57,7,4,4);
        g.drawRoundRect(182,255,62,8,4,4);
        g.setColor(Color.lightGray);
        g.fillRoundRect(187,247,57,7,4,4);
        g.fillRoundRect(182,255,62,8,4,4);

//ROOF STYLE
        g.setColor(Color.black);
        int x16[] = {224,265,342,255,225};
        int y16[] = {130,98,155,155,130};
        int pts16 = x16.length;
        g.drawPolygon(x16,y16,pts16);
        int x5[] = {194,194,219,242,242,194};
        int y5[] = {155,152,135,152,155,155};
        int pts5 = x5.length;
        g.drawPolygon(x5,y5,pts5);
        g.setColor(Color.darkGray);
        g.fillPolygon(x5,y5,pts5);
        g.fillPolygon(x16,y16,pts16);

        int x6[] = {263,265,266,266,265,263,263};
        int y6[] = {100,98,100,115,117,115,100};
        int pts6 = x6.length;
        g.drawPolygon(x6,y6,pts6);

        int x7[] = {237,240,240,238,237,237};
        int y7[] = {121,117,135,136,135,121};
        int pts7 = x7.length;
        g.drawPolygon(x7,y7,pts7);

        int x8[] = {290,293,293,292,290,290};
        int y8[] = {117,120,136,137,136,117};
        int pts8 = x8.length;
        g.drawPolygon(x8,y8,pts8);

        g.setColor(Color.gray);
        g.fillPolygon(x6,y6,pts6);
        g.fillPolygon(x7,y7,pts7);
        g.fillPolygon(x8,y8,pts8);

        g.setColor(Color.yellow);
        g.drawArc(212,147,12,13,0,180);
        g.fillArc(212,147,12,13,0,180);

        g.drawLine(200,150,212,155);
        g.drawLine(205,145,212,153);
        g.drawLine(208,142,213,150);
        g.drawLine(214,140,215,148);
        g.drawLine(218,135,218,146);
        g.drawLine(222,140,221,148);
        g.drawLine(226,142,223,150);
        g.drawLine(229,145,225,152);
        g.drawLine(235,148,225,155);

//UNDER THE ROOF
        g.drawRect(343,164,124,10);
        g.setColor(Color.black);
        g.drawLine(343,174,465,174);
        g.drawLine(342,174,342,163);

        g.drawRect(63,164,131,10);
        g.setColor(Color.lightGray);
        g.fillRect(343,164,124,10);
        g.fillRect(63,164,131,10);
        // Dibujo del camino de piedra
        
    }

    public static void main (String[]args)
    {
        Finals finals = new Finals();
        JFrame frame = new JFrame ("Casita");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(finals);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}