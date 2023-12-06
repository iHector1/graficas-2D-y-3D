package org.example;// Source code is decompiled from a .class file using FernFlower decompiler.

public class Point3D {
   public int x;
   public int y;
   public int z;
   public static Point3D instance;

   public Point3D(int var1, int var2, int var3) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
   }

   public static void resetPoint(Point3D var0) {
      var0.x = 0;
      var0.y = 0;
      var0.z = 0;
   }
}
