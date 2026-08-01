package game.players;

import game.game.Move;
import game.game.TTTGameState;

public class SequentialPlayer implements IPlayer {
    @Override
    public boolean isAI() {
        return true;
    }

    @Override
    public Move choseMove(TTTGameState state) {
        for (int r = 0; r < state.getN(); r++) {
            for (int c = 0; c < state.getM(); c++) {
                if (state.isValid(r, c)) {
                    return new Move(r, c, state.getCell());
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }

}
