/**
 * Write a description of class MatrixList here.
 *
 * @author Ori Kornflutzzzz
 * @version Ema Shel KELLER 9/11/2001
 */

public class MatrixList
{
    IntNodeMat _m00;

    public MatrixList()
    {
        _m00 = null;
    }

    public MatrixList(int[][]mat)
    {

    }


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
            return Integer.MIN_VALUE;  
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


    public void setData_i_j (int data, int i, int j)
    {
    }


    public String toString() {

        int colCounter = 0;
        int rowCounter = 0;

        if (_m00 == null) {
            return "";
        }

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

        String result = "";
        IntNodeMat currentValue = _m00;

        for (int i = 0; i < rowCounter; i++) {
            for (int j = 0; j < colCounter; j++) {
                result += currentValue.getData();
                if (j < colCounter - 1) {
                    result += "\t";
                }
                currentValue = currentValue.getNextRow();
            }
            if (i < rowCounter - 1) {
                result += "\n";
            }
            currentValue = currentValue.getNextRow();
        }

        return result;
    }


    public int findMax()
    {
        return 0;
    }

    public int howManyX(int x)
    {
        return 0;
    }

}
