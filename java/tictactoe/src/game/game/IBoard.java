package game.game;

public interface IBoard {
    //интерфейс может реализовывать только двумерные доски в силу реализации Move

    //проверяет ход на валидность
    boolean isValidMove(Move move);

    //Применяет ход к доске. Ход должен быть валидным
    void applyMove(Move move);

    /*Проверяет статус игры. Возвращает WIN, IN_PROGRESS
     относительно последнего игрока, сделавшего ход*/
    GameStatus checkStatus(Move move);

    //возвращает неизменяемую копию доски
    BoardState createBoardState();


}
