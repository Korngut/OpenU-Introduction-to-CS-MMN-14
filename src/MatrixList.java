public class MatrixList {
    private IntNodeMat _m00;

    public MatrixList() {
        _m00 = null;
    }

    public MatrixList(int[][] mat) {
        if (mat.length != 0 && mat[0].length != 0) {
            _m00 = new IntNodeMat(mat[0][0]);
            buildLinkedList(mat, 0, 0, _m00, null, null);
        }
    }

    private void buildLinkedList(int[][] mat, int i, int j, IntNodeMat current, IntNodeMat prevRow, IntNodeMat prevCol) {
        if (i >= mat.length || j >= mat[0].length) return;

        current.setData(mat[i][j]);
        if (prevCol != null) prevCol.setNextCol(current);
        if (prevRow != null) prevRow.setNextRow(current);

        if (j < mat[0].length - 1)
            buildLinkedList(mat, i, j + 1, new IntNodeMat(mat[i][j + 1]), prevRow == null ? null : prevRow.getNextCol(), current);
        if (j == 0 && i < mat.length - 1)
            buildLinkedList(mat, i + 1, 0, new IntNodeMat(mat[i + 1][0]), current, null);
    }

    public int getData_i_j(int i, int j) {
        IntNodeMat node = getNode(i, j);
        return node != null ? node.getData() : Integer.MIN_VALUE;
    }

    public void setData_i_j(int num, int i, int j) {
        IntNodeMat node = getNode(i, j);
        if (node != null) node.setData(num);
    }

    private IntNodeMat getNode(int i, int j) {
        if (i < 0 || j < 0 || _m00 == null) return null;
        IntNodeMat current = _m00;
        for (int x = 0; x < j && current != null; x++) current = current.getNextCol();
        for (int y = 0; y < i && current != null; y++) current = current.getNextRow();
        return current;
    }

    public int findMax() {
        return findMax(_m00, Integer.MIN_VALUE);
    }

    private int findMax(IntNodeMat current, int max) {
        if (current == null) return max;
        max = Math.max(max, current.getData());
        return Math.max(findMax(current.getNextCol(), max), findMax(current.getNextRow(), max));
    }

    public int howManyX(int x){
        IntNodeMat current = _m00;
        int counter = 0;
        while ( current.getData() <= x ) {
            while (current.getData() <= x){
                if (current.getData() == x){
                    counter++;
                    break;
                }
                if (current.getNextCol() != null){
                    current = current.getNextCol();
                } else {
                    break;
                }
            }
            if (current.getNextRow() != null){
                current = current.getNextRow();
            } else {
                break;
            }
        }
        return counter;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntNodeMat row = _m00;
        while (row != null) {
            IntNodeMat col = row;
            while (col != null) {
                sb.append(col.getData()).append(col.getNextCol() != null ? "\t" : "\n");
                col = col.getNextCol();
            }
            row = row.getNextRow();
        }
        return sb.toString();
    }
}
