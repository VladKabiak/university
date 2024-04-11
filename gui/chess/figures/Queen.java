package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(String color) {
        super(color, "Queen");
    }

    @Override
    public char getCode() {
        if (color.equals("white")){
            return 'Q';
        } else {
            return 'q';
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

        // check diagonals from top left to bottom right
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            Piece piece = board.getPiece(i, j);
            if (piece == null) {
                moves.add(new Move(x, y, i, j, false));
            } else if (!piece.getColor().equals(this.color)) {
                moves.add(new Move(x, y, i, j, false));
                break;
            } else {
                break;
            }

        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            Piece piece = board.getPiece(i, j);
            if (piece == null) {
                moves.add(new Move(x, y, i, j, false));
            } else if (!piece.getColor().equals(this.color)) {
                moves.add(new Move(x, y, i, j, false));
                break;
            } else {
                break;
            }
        }

        // check diagonals from top right to bottom left
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            Piece piece = board.getPiece(i, j);
            if (piece == null) {
                moves.add(new Move(x, y, i, j, false));
            } else if (!piece.getColor().equals(this.color)) {
                moves.add(new Move(x, y, i, j, false));
                break;
            } else {
                break;
            }
        }

        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            Piece piece = board.getPiece(i, j);
            if (piece == null) {
                moves.add(new Move(x, y, i, j, false));
            } else if (!piece.getColor().equals(this.color)) {
                moves.add(new Move(x, y, i, j, false));
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

        if (xDiff == 0 || yDiff == 0) {
            return true;
        } else if (xDiff == yDiff) {
            return true;
        } else {
            return false;
        }

    }
}