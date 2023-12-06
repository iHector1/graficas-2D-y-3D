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


    //3d
    public ArrayList<Location3D> translation3D(float dx, float dy,float dz, ArrayList<Location3D> points) {
        ArrayList<Location3D> resultList = new ArrayList<Location3D>();
        int size = points.size();
        int[][] inputMatrix = new int[4][size];
        double[][] matrix = {{1, 0,0,dx}, {0, 1,0, dy}, {0, 0, 1,dz},{0,0,0,1}};

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
    public  ArrayList<Location3D> Rotacion3D(ArrayList<Location3D> coordenadas, int a, char e){
        ArrayList<Location3D>  result = new ArrayList<>();
        Double r[]={0.0,0.0,0.0};
        int size = coordenadas.size();
        int[][] inputMatrix = new int[4][size];
        Double [][] T=new Double[4][4];
        for (int i = 0; i < size; i++){
            inputMatrix[0][i] = coordenadas.get(i).pointX;
            inputMatrix[1][i] = coordenadas.get(i).pointY;
            inputMatrix[2][i] = coordenadas.get(i).pointZ;
            inputMatrix[3][i] = 1;
        }
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
        for (int m = 0; m < size; m++) {
            // Multiplicación de matrices
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    r[i] += inputMatrix[j][m] * T[i][j];
                }
            }

            // Agregar el resultado a la lista de resultados
            result.add(new Location3D(r[0], r[1], r[2]));
        }

        return result; // Devolver la lista de coordenadas transformadas

    }


    public ArrayList<Location3D> multiplicarPorMatriz(ArrayList<Location3D> coordenadas, int a) {
        ArrayList<Location3D> resultado = new ArrayList<>();

        Double[][] Tx = {
                {Math.cos(Math.toRadians(a)),0.0,-Math.sin(Math.toRadians(a)),0.0},
                {0.0,1.0,0.0,0.0},
                {Math.sin(Math.toRadians(a)),0.0,Math.cos(Math.toRadians(a)),0.0},
                {0.0,0.0,0.0,1.0}
        };

        for (Location3D punto : coordenadas) {
            // Crear una matriz con las coordenadas del punto
            Double[][] inputMatrix = {
                    {(double) punto.pointX},
                    {(double) punto.pointY},
                    {(double) punto.pointZ},
                    {1.0} // Coordenada homogénea
            };

            // Realizar la multiplicación de matrices
            Double[][] resultadoMatriz = multiplicarMatrices(Tx, inputMatrix);

            // Crear un nuevo Location3D con los resultados y agregarlo al ArrayList
            Location3D resultadoPunto = new Location3D(
                    resultadoMatriz[0][0].intValue(),
                    resultadoMatriz[1][0].intValue(),
                    resultadoMatriz[2][0].intValue()
            );
            resultado.add(resultadoPunto);
        }

        return resultado;
    }

    // Método para multiplicar matrices (función auxiliar)
    private Double[][] multiplicarMatrices(Double[][] matriz1, Double[][] matriz2) {
        int m1Rows = matriz1.length;
        int m1Cols = matriz1[0].length;
        int m2Cols = matriz2[0].length;

        Double[][] resultado = new Double[m1Rows][m2Cols];

        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Cols; j++) {
                resultado[i][j] = 0.0;
                for (int k = 0; k < m1Cols; k++) {
                    resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                }
            }
        }

        return resultado;
    }
}
