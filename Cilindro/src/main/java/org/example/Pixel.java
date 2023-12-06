package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pixel {
   private final BufferedImage buffer;
   public static Pixel instance;
   public static int width;
   public static int height;

   public Pixel(BufferedImage var1) {
      this.buffer = var1;
      width = this.buffer.getWidth();
      height = this.buffer.getHeight();
      instance = this;
   }

   public void drawPixel(int var1, int var2, Color var3) {
      this.buffer.setRGB(var1, var2, var3.getRGB());
   }

   public static void fondo(Color var0) {
      for(int var1 = 0; var1 < instance.buffer.getWidth(); ++var1) {
         for(int var2 = 0; var2 < instance.buffer.getHeight(); ++var2) {
            instance.drawPixel(var1, var2, var0);
         }
      }

   }
}
