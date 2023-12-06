package org.example;// Source code is decompiled from a .class file using FernFlower decompiler.


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

import static java.lang.Thread.sleep;

public class Practica_9 extends JFrame {
   private BufferedImage buffer;

   public static void main(String[] var0) {
      new Practica_9();
   }

   private Practica_9() {
      super("Cilindro");
      this.setSize(400, 600);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.buffer = new BufferedImage(this.getWidth(), this.getHeight(), 2);
      new Pixel(this.buffer);
      this.setVisible(true);
      this.setLocationRelativeTo((Component)null);
   }

   public void paint(Graphics var1) {
      new ArrayList();
      double var3 = 4.0;
      double var5 = 6.0;
      double var7 = 0.0;
         ArrayList var2 = new ArrayList();
         Pixel.fondo(Color.BLACK);

         for(double var15 = 0.0; var15 <= var3; var15 += 0.05) {
            for(double var17 = 0.0; var17 <= var5; var17 += 0.1) {
               double var9 = 30.0 * (1.0 + Math.pow(Math.cos(var7 + var15), 2.0)) * Math.cos(var7 + var17);
               double var11 = 30.0 * (1.0 + Math.pow(Math.cos(var7 + var15), 2.0)) * Math.sin(var7 + var17);
               double var13 = 50.0 * var15;
               var2.add(new Point3D((int)var9, (int)var13, (int)var11));
               var9 = 30.0 * (1.0 + Math.pow(Math.cos(var7 + var15 - 0.1), 2.0)) * Math.cos(var7 + var17);
               var11 = 30.0 * (1.0 + Math.pow(Math.cos(var7 + var15 - 0.1), 2.0)) * Math.sin(var7 + var17);
               var13 = 50.0 * (var15 - 0.05);
               var2.add(new Point3D((int)var9, (int)var13, (int)var11));
            }
         }
         AlgorDibujo.CurveExplicita(var2, Color.PINK);
         var1.drawImage(this.buffer, 0, 0, this);
      }
}
