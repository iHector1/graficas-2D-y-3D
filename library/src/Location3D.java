public class Location3D {
    public int pointX;
    public int pointY;
    public int pointZ;
    public Location3D(int x, int y, int z)
    {
        this.pointX = x;
        this.pointY = y;
        this.pointZ = z;
    }

    public Location3D(Double x, Double y, Double z) {
        this.pointX = x.intValue();
        this.pointY = y.intValue();
        this.pointZ = z.intValue();
    }
}
