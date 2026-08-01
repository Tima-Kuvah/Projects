package game.game;

public class Move {
    private final int row;
    private final int column;
    private final Cell playerSimbol;

    public Move(int row, int col, Cell playerSimbol) {
        this.row = row;
        this.column = col;
        this.playerSimbol = playerSimbol;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getPlayerSimbol() {
        return playerSimbol;
    }
}
