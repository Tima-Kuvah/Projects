package game.players;


import game.game.Move;
import game.game.TTTGameState;

import java.util.Random;

public class RandomPlayer implements IPlayer {
    private final Random random;

    @Override
    public boolean isAI() {
        return true;
    }

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move choseMove(final TTTGameState state) {
        while (true) {
            int r = random.nextInt(state.getN());
            int c = random.nextInt(state.getM());
            if (state.isValid(r, c)) {
                return new Move(r, c, state.getCell());
            }
        }
    }
}