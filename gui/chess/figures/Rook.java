package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(String color) {
        super(color, "Rook");
    }

    @Override
    public char getCode() {
        if (color.equals("white")){
            return 'R';
        } else {
            return 'r';
        }
    }

    @Override
    public List<Move> getPossibleMoves(MoveValidator validator, Board board, int x, int y, Player player, Player opponentPlayer) {
        List<Move> moves = new ArrayList<>();

        // check for moves in each direction
        for (int i = x + 1; i < 8; i++) {
            Piece piece = board.getPiece(i,y);
            if (piece == null) {
                moves.add(new Move(x, y, i, y, false));
            } else if (!piece.getColor().equals(this.color)){
                moves.add(new Move(x, y, i, y, false));
                break;
            } else {
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            Piece piece = board.getPiece(i,y);
            if (piece == null) {
                moves.add(new Move(x, y, i, y, false));
            } else if (!piece.getColor().equals(this.color)){
                moves.add(new Move(x, y, i, y, false));
                break;
            } else {
                break;
            }
        }

        for (int i = y + 1; i < 8; i++) {
            Piece piece = board.getPiece(x,i);
            if (piece == null) {
                moves.add(new Move(x, y, x,i, false));
            } else if (!piece.getColor().equals(this.color)){
                moves.add(new Move(x, y, x,i, false));
                break;
            } else {
                break;
            }
        }

        for (int i = y - 1; i >= 0; i--) {
            Piece piece = board.getPiece(x,i);
            if (piece == null) {
                moves.add(new Move(x, y, x,i, false));
            } else if (!piece.getColor().equals(this.color)){
                moves.add(new Move(x, y, x,i, false));
                break;
            } else {
                break;
            }
        }

        return moves;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, boolean isAttack) {
        int xDiff = Math.abs(endX - startX);
        int yDiff = Math.abs(endY - startY);

        return xDiff == 0 || yDiff == 0;
    }
}
