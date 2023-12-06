package org.example;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class AlgorDibujo {
   public AlgorDibujo() {
   }

   private static void LineaBresenham(int var0, int var1, int var2, int var3, Color var4) {
      int var7 = var2 - var0;
      int var8 = var3 - var1;
      byte var13;
      if (var8 < 0) {
         var8 = -1 * var8;
         var13 = -1;
      } else {
         var13 = 1;
      }

      byte var12;
      if (var7 < 0) {
         var7 = -2 * var7;
         var12 = -1;
      } else {
         var12 = 1;
      }

      int var5 = var0;
      int var6 = var1;
      Pixel.instance.drawPixel(var0, var1, var4);
      int var9;
      int var10;
      int var11;
      if (var7 > var8) {
         var11 = 2 * var8 - var7;
         var9 = 2 * var8;

         for(var10 = 2 * var8 - 2 * var7; var5 != var2; Pixel.instance.drawPixel(var5, var6, var4)) {
            var5 += var12;
            if (var11 < 0) {
               var11 += var9;
            } else {
               var6 += var13;
               var11 += var10;
            }
         }
      } else {
         var11 = 2 * var7 - var8;
         var9 = 2 * var7;

         for(var10 = 2 * var7 - 2 * var8; var6 != var3; Pixel.instance.drawPixel(var5, var6, var4)) {
            var6 += var13;
            if (var11 < 0) {
               var11 += var9;
            } else {
               var5 += var12;
               var11 += var10;
            }
         }
      }

   }

   private static void relleno(int var0, int var1, int var2, int var3, int var4, int var5, Color var6) {
      var6 = Color.PINK;
      int var9 = var4 - var2;
      int var10 = var5 - var3;
      byte var15;
      if (var10 < 0) {
         var10 = -1 * var10;
         var15 = -1;
      } else {
         var15 = 1;
      }

      byte var14;
      if (var9 < 0) {
         var9 = -1 * var9;
         var14 = -1;
      } else {
         var14 = 1;
      }

      int var7 = var2;
      int var8 = var3;
      int var11;
      int var12;
      int var13;
      if (var9 > var10) {
         var13 = 2 * var10 - var9;
         var11 = 2 * var10;

         for(var12 = 2 * var10 - 2 * var9; var7 != var4; LineaBresenham(var0, var1, var7, var8, var6)) {
            var7 += var14;
            if (var13 < 0) {
               var13 += var11;
            } else {
               var8 += var15;
               var13 += var12;
            }
         }
      } else {
         var13 = 2 * var9 - var10;
         var11 = 2 * var9;

         for(var12 = 2 * var9 - 2 * var10; var8 != var5; LineaBresenham(var0, var1, var7, var8, var6)) {
            var8 += var15;
            if (var13 < 0) {
               var13 += var11;
            } else {
               var7 += var14;
               var13 += var12;
            }
         }
      }

   }

   public static void CurveExplicita(ArrayList<Point3D> var0, Color var1) {
      double var6 = 200.0;
      double var8 = -150.0;
      double var10 = -150.0;
      double var12 = -150.0;
      ArrayList var14 = new ArrayList();
      Iterator var15 = var0.iterator();

      while(var15.hasNext()) {
         Point3D var16 = (Point3D)var15.next();
         double var2 = (double)var16.x + var8 * ((var6 - (double)var16.z) / var12);
         double var4 = (double)Pixel.height - ((double)var16.y + var10 * ((var6 - (double)var16.z) / var12));
         var14.add(new Point3D((int)var2, (int)var4, 0));
      }

      for(int var17 = 3; var17 < var14.size(); ++var17) {
         relleno(((Point3D)var14.get(var17 - 3)).x, ((Point3D)var14.get(var17 - 3)).y, ((Point3D)var14.get(var17 - 2)).x, ((Point3D)var14.get(var17 - 2)).y, ((Point3D)var14.get(var17 - 1)).x, ((Point3D)var14.get(var17 - 1)).y, Color.WHITE);
         LineaBresenham(((Point3D)var14.get(var17 - 2)).x, ((Point3D)var14.get(var17 - 2)).y, ((Point3D)var14.get(var17 - 3)).x, ((Point3D)var14.get(var17 - 3)).y, Color.BLACK);
      }

   }
}
