package game.players;

import game.game.Cell;
import game.game.Move;
import game.game.TTTGameState;

public interface IPlayer {
    boolean isAI();
    Move choseMove(TTTGameState state);
}

