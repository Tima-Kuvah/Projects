package game.game;

public class TTTGameState {
    private final TTTBoardState boardState;
    private final Cell currentCell;


    public TTTGameState(TTTBoardState boardState, Cell currentCell) {
        this.boardState = boardState;
        this.currentCell = currentCell;
    }

    public int getN() {
        return boardState.getN();
    }

    public int getM() {
        return boardState.getM();
    }

    public Cell getCell() {
        return this.currentCell;
    }

    public boolean isValid(int row, int col) {
        return boardState.isValid(row, col);
    }

    public String showBoard() {
        return boardState.showBoard();
    }
}
