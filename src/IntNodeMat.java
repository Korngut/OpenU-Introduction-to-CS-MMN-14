/**
 * @author Ori Korngut
 * @version 30/1/2025
 */
public class MatrixList {

    IntNodeMat _m00;  // Head of the matrix (top-left corner)

    /**
     * Default constructor for an empty matrix.
     */
    public MatrixList() {
        _m00 = null;
    }

    /**
     * Constructor that initializes the matrix from a 2D array.
     *
     * @param mat 2D array representing the matrix.
     */
    public MatrixList(int[][] mat) {
        if (mat == null || mat.length == 0) {
            _m00 = null;
            return;
        }

        // Create the first node in the matrix (top-left corner)
        _m00 = new IntNodeMat(mat[0][0]);
        IntNodeMat currentRow = _m00;

        for (int i = 0; i < mat.length; i++) {
            IntNodeMat currentNode = currentRow;
            for (int j = 1; j < mat[i].length; j++) {
                currentNode.setNextCol(new IntNodeMat(mat[i][j]));
                currentNode = currentNode.getNextCol();
            }

            if (i < mat.length - 1) {
                currentRow.setNextRow(new IntNodeMat(mat[i + 1][0]));
                currentRow = currentRow.getNextRow();
            }
        }
    }

    /**
     * Returns the value at position [i][j] in the matrix.
     * If the indices are out of bounds, returns Integer.MIN_VALUE.
     *
     * @param i Row index
     * @param j Column index
     * @return The value at [i][j], or Integer.MIN_VALUE if out of bounds.
     */
    public int getData_i_j(int i, int j) {
        int colCounter = 0;
        int rowCounter = 0;

        IntNodeMat currentCol = _m00;
        while (currentCol != null) {
            colCounter++;
            currentCol = currentCol.getNextCol();
        }

        IntNodeMat currentRow = _m00;
        while (currentRow != null) {
            rowCounter++;
            currentRow = currentRow.getNextRow();
        }

        if (i >= rowCounter || j >= colCounter || i < 0 || j < 0) {
            return Integer.MIN_VALUE;  // Invalid index
        }

        IntNodeMat currentNode = _m00;

        for (int row = 0; row < i; row++) {
            currentNode = currentNode.getNextRow();
        }

        for (int col = 0; col < j; col++) {
            currentNode = currentNode.getNextCol();
        }

        return currentNode.getData();
    }

    /**
     * Sets the value at position [i][j] in the matrix.
     * If the indices are out of bounds, does nothing.
     *
     * @param data The value to set
     * @param i Row index
     * @param j Column index
     */
    public void setData_i_j(int data, int i, int j) {
        int colCounter = 0;
        int rowCounter = 0;

        IntNodeMat currentCol = _m00;
        while (currentCol != null) {
            colCounter++;
            currentCol = currentCol.getNextCol();
        }

        IntNodeMat currentRow = _m00;
        while (currentRow != null) {
            rowCounter++;
            currentRow = currentRow.getNextRow();
        }

        if (i >= rowCounter || j >= colCounter || i < 0 || j < 0) {
            return;  // Invalid index, do nothing
        }

        IntNodeMat currentNode = _m00;

        for (int row = 0; row < i; row++) {
            currentNode = currentNode.getNextRow();
        }

        for (int col = 0; col < j; col++) {
            currentNode = currentNode.getNextCol();
        }

        currentNode.setData(data);  // Set the value
    }

    /**
     * Converts the matrix into a string representation.
     * The matrix is represented as rows of numbers, with tabs separating columns
     * and newlines separating rows. If the matrix is empty, an empty string is returned.
     *
     * @return String representation of the matrix
     */
    public String toString() {
        // If the matrix is empty
        if (_m00 == null) {
            return "";  // Return an empty string
        }

        IntNodeMat currentRow = _m00;  // Start with the first row
        String result = "";  // Start with an empty string

        while (currentRow != null) {  // Traverse rows
            IntNodeMat currentValue = currentRow;  // Start with the first element in the current row

            while (currentValue != null) {  // Traverse columns
                result += currentValue.getData();  // Append the current element

                // If not the last column, add a tab between values
                if (currentValue.getNextCol() != null) {
                    result += "\t";
                }

                currentValue = currentValue.getNextCol();  // Move to the next column
            }

            // If not the last row, add a newline
            if (currentRow.getNextRow() != null) {
                result += "\n";
            }

            currentRow = currentRow.getNextRow();  // Move to the next row
        }

        return result;  // Return the final string representation
    }

    /**
     * Finds the maximum value in the matrix using recursion.
     *
     * @return The maximum value found in the matrix.
     */
    public int findMax() {
        if (_m00 == null) {
            return Integer.MIN_VALUE;
        }

        return findMaxInRow(_m00, Integer.MIN_VALUE);
    }

    private int findMaxInRow(IntNodeMat currentRow, int currentMax) {
        if (currentRow == null) {
            return currentMax;
        }

        currentMax = Math.max(currentMax, currentRow.getData());

        if (currentRow.getNextCol() != null) {
            currentMax = findMaxInRow(currentRow.getNextCol(), currentMax);
        }

        if (currentRow.getNextRow() != null) {
            currentMax = findMaxInRow(currentRow.getNextRow(), currentMax);
        }

        return currentMax;
    }

    /**
     * Counts the occurrences of a given value x in the matrix.
     * Uses an optimized approach that leverages the sorted nature of the matrix.
     * O(N+K)
     * 
     * @param x The value to search for.
     * @return The number of occurrences of x in the matrix.
     */
    public int howManyX(int x) {
        if (_m00 == null) {
            return 0;
        }

        return countOccurrences(x, _m00);
    }

    private int countOccurrences(int x, IntNodeMat currentRow) {
        int count = 0;

        while (currentRow != null) {
            if (currentRow.getData() == x) {
                count++;
            }

            if (currentRow.getNextCol() != null && currentRow.getData() < x) {
                currentRow = currentRow.getNextCol();
            } else {
                if (currentRow.getNextRow() != null) {
                    currentRow = currentRow.getNextRow();
                } else {
                    break;
                }
            }

        }
        return count;
    }
}
