package moveValidator;

import board.Board;
import figures.Piece;

public abstract class AbstractMoveValidator implements MoveValidatorInterface {

    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY, boolean isAttack, Piece piece) {
        return piece.isValidMove(startX, startY, endX, endY, isAttack);
    }
}
