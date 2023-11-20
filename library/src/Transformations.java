import java.util.ArrayList;

public class Transformations {


    public ArrayList<Location> translation(int dx, int dy, ArrayList<Location> points) {
        ArrayList<Location> resultList = new ArrayList<Location>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        int[][] matrix = {{1, 0, dx}, {0, 1, dy}, {0, 0, 1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX;
            inputMatrix[1][i] = points.get(i).pointY;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++) {
            resultList.add(
                    new Location(result[0][i], result[1][i]));
        }
        return resultList;
    }
    public static int[][] multiplyMatrices(int[][] matrix, int[][] inputMatrix) {
        int m1 = matrix.length;
        int n1 = matrix[0].length;
        int n2 = inputMatrix[1].length;
        int[][] result = new int[m1][n2];

        for (int i = 0; i < m1; i++){
            for (int j = 0; j < n2; j++){
                for (int k = 0; k < n1; k++)
                    result[i][j] += matrix[i][k] * inputMatrix[k][j];
            }
        }
        return result;
    }
    public static int[][] multiplyMatrices(double[][] matrix, int[][] inputMatrix) {
        int m1 = matrix.length;
        int n1 = matrix[0].length;
        int n2 = inputMatrix[1].length;
        int[][] result = new int[m1][n2];

        for (int i = 0; i < m1; i++){
            for (int j = 0; j < n2; j++){
                for (int k = 0; k < n1; k++)
                    result[i][j] += matrix[i][k] * inputMatrix[k][j];
            }
        }
        return result;
    }
    public static ArrayList<Location> rotation(int theta, ArrayList<Location> points){
        ArrayList<Location> resultList = new ArrayList<Location>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double radian = Math.PI / 180 *theta;
        double[][] matrix = {{Math.cos(radian), - Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX;
            inputMatrix[1][i] = points.get(i).pointY;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new Location(result[0][i], result[1][i]));
        return resultList;
    }
    public static ArrayList<Location> Escalation(float sx, float sy, ArrayList<Location> points){
        ArrayList<Location> resultList = new ArrayList<Location>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double[][] matrix = {{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX;
            inputMatrix[1][i] = points.get(i).pointY;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new Location(result[0][i], result[1][i]));
        return resultList;
    }
    public static ArrayList<Location> CenterRotation(int theta, ArrayList<Location> points, Location center){
        ArrayList<Location> resultList = new ArrayList<Location>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double radian = Math.PI / 180 *theta;
        double[][] matrix = {{Math.cos(radian), - Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX - center.pointX;
            inputMatrix[1][i] = points.get(i).pointY - center.pointY;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new Location(result[0][i] + center.pointX, result[1][i] + center.pointY));
        return resultList;
    }
    public ArrayList<Location3D> translation3D(int dx, int dy,int dz, ArrayList<Location3D> points) {
        ArrayList<Location3D> resultList = new ArrayList<Location3D>();
        int size = points.size();
        int[][] inputMatrix = new int[4][size];
        int[][] matrix = {{1, 0,0,dx}, {0, 1,0, dy}, {0, 0, 1,dz},{0,0,0,1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX;
            inputMatrix[1][i] = points.get(i).pointY;
            inputMatrix[2][i] = points.get(i).pointZ;
            inputMatrix[3][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++) {
            resultList.add(
                    new Location3D(result[0][i], result[1][i],result[2][i]));
        }
        return resultList;
    }
    public static ArrayList<Location3D> Escalation3D(float sx, float sy, float sz,ArrayList<Location3D> points){
        ArrayList<Location3D> resultList = new ArrayList<Location3D>();
        int size = points.size();
        int[][] inputMatrix = new int[4][size];
        double[][] matrix = {{sx, 0, 0, 0}, {0, sy, 0, 0}, {0, 0, sz, 0},{0, 0, 0, 1}};

        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = points.get(i).pointX;
            inputMatrix[1][i] = points.get(i).pointY;
            inputMatrix[2][i] = points.get(i).pointZ;
            inputMatrix[3][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new Location3D(result[0][i], result[1][i],result[2][i]));
        return resultList;
    }
}
