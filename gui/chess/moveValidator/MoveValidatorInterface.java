package moveValidator;

import board.Board;
import figures.Piece;

public interface MoveValidatorInterface {
    boolean isValidMove(Board board, int startX, int startY, int endX, int endY, boolean isAttack, Piece piece);
}
