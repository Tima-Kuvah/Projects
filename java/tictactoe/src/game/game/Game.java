package game.game;

import game.players.*;

public class Game {
    private TicTacToeBoard board;
    private final boolean log;
    private final IPlayer player1;
    private final IPlayer player2;
    private IPlayer currentPlayer;
    private Cell currentCell;
    private GameStatus status = GameStatus.IN_PROGRESS;
    private int numOfTurn = 0;
    private final int n, m;


    public Game(final boolean log, final IPlayer player1, final IPlayer player2, final int n, final int m, final int k) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
        this.n = n;
        this.m = m;
        board = new TicTacToeBoard(n, m, k);
    }

    public GameStatus play() {
        currentPlayer = player1;
        currentCell = Cell.X;


        while (status == GameStatus.IN_PROGRESS) {
            makeTurn();
        }

        System.out.println("Результат игры: ");

        TTTGameState finalState = new TTTGameState(board.createBoardState(), currentCell);

        if (status == GameStatus.DRAW) {
            System.out.println("Ничья!" + System.lineSeparator());
            System.out.println("Конечное положение доски: " + System.lineSeparator());
            System.out.println(finalState.showBoard());
            return GameStatus.DRAW;
        } else if (status == GameStatus.WIN && currentPlayer == player1) {

            System.out.println("Победой первого игрока!" + System.lineSeparator());
            System.out.println("Конечное положение доски: " + System.lineSeparator());
            System.out.println(finalState.showBoard());
            return GameStatus.WIN;
        } else {
            System.out.println("Победой второго игрока!" + System.lineSeparator());
            System.out.println("Конечное положение доски: " + System.lineSeparator());
            System.out.println(finalState.showBoard());
            return GameStatus.LOSE;
        }
    }

    private void makeTurn() {
        numOfTurn++;
        TTTGameState state = new TTTGameState(board.createBoardState(), currentCell);
        if ((currentPlayer == player1)) {
            log("Ход первого игрока, текущая доска: " + System.lineSeparator());
        } else {
            log("Ход второго игрока, текущая доска: " + System.lineSeparator());
        }
        Move move = null;
        if (currentPlayer.isAI()) {
            log(state.showBoard());
             move = currentPlayer.choseMove(state);
            if (board.isValidMove(move)) {
                board.applyMove(move);
            } else {status = GameStatus.LOSE; return;}
        } else {
            do {
                state.showBoard();
                move = currentPlayer.choseMove(state);
            } while (!board.isValidMove(move));
            board.applyMove(move);
        }
        updateGameStatus(move);
        if (status == GameStatus.IN_PROGRESS) {
            switchCurPlayer();
        }
    }

    private void switchCurPlayer() {
        currentCell = (currentPlayer == player1) ? Cell.O : Cell.X;
        currentPlayer = (currentPlayer == player1) ? player2 : player1;

    }

    private void updateGameStatus(Move move) {
        status = board.checkStatus(move);

        if (status == GameStatus.IN_PROGRESS && numOfTurn == n * m) {
            status = GameStatus.DRAW;
        }
    }

    private void log(String massage) {
        if (log) {
            System.out.print(massage);
        }
    }

}
