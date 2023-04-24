import java.util.Arrays;

public class Canvas {
    private final int width;
    private final int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height + 2][width + 2];
    }

    public void setPoint(double x, double y, char c) {
        int a = Math.toIntExact(Math.round(x));
        int b = Math.toIntExact(Math.round(y));

        if (a >= 0 && a < matrix[0].length && b >= 0 && b < matrix.length) {
            matrix[b][a] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }

    public void clear() {
        matrix = new char[height + 2][width + 2];
    }

    public void print() {
        for (char[] chars : matrix) {
            System.out.print(" ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
