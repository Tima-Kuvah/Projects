package game.game;

import java.util.Map;

public class TTTBoardState implements BoardState {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] board;
    private final int n;
    private final int m;

    public TTTBoardState(Cell[][] original) {
        n = original.length;
        m = original[0].length;
        board = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i],0, board[i], 0, m);
        }
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isValid(int row, int col) {
        return 0 <= row && row < n
                && 0 <= col && col < m
                && board[row][col] == Cell.E;

    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    @Override
    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int col = 0; col < m; col++) {
            sb.append(col + 1).append(" ");
        }
        sb.append("\n");

        for (int row = 0; row < n; row++) {
            sb.append(row + 1).append(" ");

            for (int col = 0; col < m; col++) {
                Character symbol = SYMBOLS.get(getCell(row, col));
                sb.append(symbol).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
