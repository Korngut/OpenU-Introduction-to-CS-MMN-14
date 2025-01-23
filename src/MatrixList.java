/**
 * Write a description of class MatrixList here.
 *
 * @author (your name)
 * @version (a version number or a date)
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


    public int getData_i_j (int i, int j)
    {
        return 0;
    }

    public void setData_i_j (int data, int i, int j)
    {
    }


    public String toString()
    {
        int colCounter = 0;
        int rowCounter = 0;

        if (_m00 == null){
            return "";
        }
        else {
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

            IntNodeMat currentValue = _m00;
            for (int i = 0; i < colCounter; i++) {
                for (int j = 0; j < rowCounter; j++) {
                    System.out.print(currentValue.getData() + "\t");
                    currentValue = currentValue.getNextRow();
                }
                currentValue = currentValue.getNextRow();
                System.out.println();
            }
        }
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
