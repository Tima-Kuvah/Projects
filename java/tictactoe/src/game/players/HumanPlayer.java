package game.players;

import game.game.Move;
import game.game.TTTGameState;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements IPlayer {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public boolean isAI() {
        return false;
    }

    @Override
    public Move choseMove(TTTGameState state) {
        out.println("текущая позиция: ");
        out.println(state.showBoard());
        out.println("ход игрока " + state.getCell());
        out.println("Пожалуйста, введите номера столбца и строчки в виде целых положительных чисел не больше " +
                state.getN() + " и " + state.getM() + " соответственно");
        int r = in.nextInt() - 1;
        int c = in.nextInt() - 1;

        while (!state.isValid(r, c)) {
            out.println("Пожалуйста, введите корректные значения для своего ход");
            out.println("номер столбца от 1 до " + state.getN() + " и");
            out.print(" номер строки от 1 до " + state.getM());
            out.print(" что бы при этом данная ячейка была свободной.");
            r = in.nextInt() - 1;
            c = in.nextInt() - 1;
        }
        return new Move(r, c, state.getCell());
    }
}
