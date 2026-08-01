package game.game;


public class TicTacToeBoard implements IBoard {
    private Cell[][] board;
    private final int rows;
    private final int columns;
    private final int k;


    public TicTacToeBoard(int rows, int columns, int k) {
        this.rows = rows;
        this.columns = columns;
        this.k = k;
        board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = Cell.E;
            }
        }

    }


    @Override
    public boolean isValidMove(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getColumn() && move.getColumn() < columns
                && board[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public void applyMove(Move move) {
        board[move.getRow()][move.getColumn()] = move.getPlayerSimbol();
    }


    @Override
    public GameStatus checkStatus(Move move) {
        int r = move.getRow();
        int c = move.getColumn();

        Cell cell = move.getPlayerSimbol();
        int count = 1;

        for (int i = 1; c + i < columns && board[r][c + i] == cell; i++) {
            count++;
        }

        for (int i = 1; c - i >= 0 && board[r][c - i] == cell; i++) {
            count++;
        }
        if (count >= k) return GameStatus.WIN;


        count = 1;
        for (int i = 1; r + i < rows && board[r + i][c] == cell; i++) {
            count++;
        }
        for (int i = 1; r - i >= 0 && board[r - i][c] == cell; i++) {
            count++;
        }
        if (count >= k) return GameStatus.WIN;


        count = 1;
        for (int i = 1; r + i < rows && c + i < columns && board[r + i][c + i] == cell; i++) {
            count++;
        }
        for (int i = 1; r - i >= 0 && c - i >= 0 && board[r - i][c - i] == cell; i++) {
            count++;
        }
        if (count >= k) return GameStatus.WIN;

        count = 1;
        for (int i = 1; r + i < rows && c - i >= 0 && board[r + i][c - i] == cell; i++) {
            count++;
        }
        for (int i = 1; r - i >= 0 && c + i < columns && board[r - i][c + i] == cell; i++) {
            count++;
        }
        if (count >= k) return GameStatus.WIN;


        return GameStatus.IN_PROGRESS;
    }


@Override
public TTTBoardState createBoardState() {
    return new TTTBoardState(board);
}

}
