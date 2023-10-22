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

}
