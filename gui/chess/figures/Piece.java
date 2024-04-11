package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.List;

public interface Piece {
    boolean isValidMove(int startX, int startY, int endX, int endY, boolean isAttack);

    char getCode();
    String getColor();
    String getType();

    List<Move> getPossibleMoves(MoveValidator validator, Board board, int x, int y, Player player, Player opponentPlayer);
}
