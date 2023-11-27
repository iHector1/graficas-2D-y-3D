import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class HSR extends JFrame {

    private BufferedImage buffer;
    private double[][] vertices;
    private int[][] caras;

    public HSR() {
        setTitle("Cubo 3D");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);

        // Coordenadas de los vértices del cubo
        vertices = new double[][]{
                {-50, -50, -50}, // Vértice 0
                {-50, -50, 50},  // Vértice 1
                {-50, 50, -50},  // Vértice 2
                {-50, 50, 50},   // Vértice 3
                {50, -50, -50},  // Vértice 4
                {50, -50, 50},   // Vértice 5
                {50, 50, -50},   // Vértice 6
                {50, 50, 50}     // Vértice 7
        };

        // Definición de caras del cubo (índices de los vértices que la componen)
        caras = new int[][]{
                {0, 1, 3, 2},  // Cara 0 (A-B-C-D)
                {4, 5, 7, 6},  // Cara 1 (E-F-G-H)
                {0, 1, 5, 4},  // Cara 2 (A-B-F-E)
                {2, 3, 7, 6},  // Cara 3 (C-D-H-G)
                {0, 2, 6, 4},  // Cara 4 (A-C-G-E)
                {1, 3, 7, 5}   // Cara 5 (B-D-H-F)
        };

        rotateCube(Math.PI / 180, Math.PI / 180, Math.PI / 180); // Rotación inicial
    }

    // Método para aplicar una rotación sobre los vértices del cubo
    public void rotateCube(double thetaX, double thetaY, double thetaZ) {
        double[][] rotationX = {
                {1, 0, 0},
                {0, Math.cos(thetaX), -Math.sin(thetaX)},
                {0, Math.sin(thetaX), Math.cos(thetaX)}
        };

        double[][] rotationY = {
                {Math.cos(thetaY), 0, Math.sin(thetaY)},
                {0, 1, 0},
                {-Math.sin(thetaY), 0, Math.cos(thetaY)}
        };

        double[][] rotationZ = {
                {Math.cos(thetaZ), -Math.sin(thetaZ), 0},
                {Math.sin(thetaZ), Math.cos(thetaZ), 0},
                {0, 0, 1}
        };

        // Aplicar rotaciones a cada vértice del cubo
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = multiplyMatrices(rotationX, multiplyMatrices(rotationY, multiplyMatrices(rotationZ, vertices[i])));
        }

        repaint();
    }

    // Método para multiplicar matrices (producto de matrices)
    private double[] multiplyMatrices(double[][] matrix1, double[] matrix2) {
        double[] result = new double[matrix1.length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                result[i] += matrix1[i][j] * matrix2[j];
            }
        }
        return result;
    }

    // Método para dibujar el cubo en el lienzo
    public void paint(Graphics g) {
        buffer = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer.createGraphics();

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.BLACK);

        // Dibujar las caras visibles del cubo
        for (int[] cara : caras) {
            if (isVisible(vertices[cara[0]], vertices[cara[1]], vertices[cara[2]])) {
                int[] xPoints = new int[cara.length];
                int[] yPoints = new int[cara.length];

                for (int i = 0; i < cara.length; i++) {
                    xPoints[i] = (int) vertices[cara[i]][0] + getWidth() / 2;
                    yPoints[i] = (int) vertices[cara[i]][1] + getHeight() / 2;
                }

                g2.drawPolygon(xPoints, yPoints, cara.length);
            }
        }

        g.drawImage(buffer, 0, 0, this);
    }

    // Método para determinar si una cara del cubo es visible
    // Método para determinar si una cara del cubo es visible
    private boolean isVisible(double[] a, double[] b, double[] c) {
        // Calcular los vectores que representan dos lados del triángulo de la cara
        double[] vectorAB = {b[0] - a[0], b[1] - a[1], b[2] - a[2]};
        double[] vectorAC = {c[0] - a[0], c[1] - a[1], c[2] - a[2]};

        // Calcular el vector normal a la cara utilizando el producto cruz
        double[] crossProduct = {
                vectorAB[1] * vectorAC[2] - vectorAB[2] * vectorAC[1],
                vectorAB[2] * vectorAC[0] - vectorAB[0] * vectorAC[2],
                vectorAB[0] * vectorAC[1] - vectorAB[1] * vectorAC[0]
        };

        // Definir la posición de la cámara (suponiendo una posición fija)
        double[] cameraPosition = {0, 0, -400}; // Cambia estas coordenadas según la posición de la cámara

        // Calcular el vector desde un punto de la cara hacia la cámara
        double[] vectorToCamera = {
                a[0] - cameraPosition[0],
                a[1] - cameraPosition[1],
                a[2] - cameraPosition[2]
        };

        // Calcular el producto punto entre el vector normal y el vector hacia la cámara
        double dotProduct = crossProduct[0] * vectorToCamera[0] +
                crossProduct[1] * vectorToCamera[1] +
                crossProduct[2] * vectorToCamera[2];

        // Si el producto punto es positivo, la cara es visible
        return dotProduct > 0;
    }


    public static void main(String[] args) {
        HSR cubo = new HSR();
        cubo.setVisible(true);
    }
}
