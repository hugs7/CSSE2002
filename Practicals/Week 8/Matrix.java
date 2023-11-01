public class Matrix {
    double[][] matrix;
    int rows;
    int columns;

    public Matrix(int rows, int columns) {
        matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                builder.append(';');
            }
            builder.append('[');
            for (int j = 0; j < columns; j++) {
                if (j != 0) {
                    builder.append(',');
                }
                builder.append(matrix[i][j]);
            }
            builder.append(']');
        }
        builder.append(']');
        return builder.toString();
    }

    private void boundsCheck(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new ArrayIndexOutOfBoundsException("(" + row + ',' 
                                                     + column + ")");
        }
    }

    public void set(int row, int column, double value) {
        boundsCheck(row, column);
        matrix[row][column] = value;
    }

    public double get(int row, int column) {
        boundsCheck(row, column);
        return matrix[row][column];
    }

    public void fill() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix[r][c] = r * columns + c;
            }
        }
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 2);
        matrix.fill();
        System.out.println(matrix);
    }
}
