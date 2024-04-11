package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece{
    public Pawn(String color) {
        super(color, "Pawn");
    }

    @Override
    public char getCode() {
        if (color.equals("white")){
            return 'P';
        } else {
            return 'p';
        }
    }

    @Override
    public List<Move> getPossibleMoves(MoveValidator validator, Board board, int x, int y, Player player, Player opponentPlayer) {
        List<Move> moves = new ArrayList<>();
        int direction = (color.equals("white")) ? -1 : 1;

        // Check if pawn can move forward
        if (board.getPiece(x, y).getColor().equals("white")) {
            if (board.getPiece(x, y + 1) == null) {
                moves.add(new Move(x, y, x, y + 1, false));
            }
            if (y == 1 && board.getPiece(x, y + 2) == null) {
                moves.add(new Move(x, y, x, y + 2, false));
            }
        } else {
            if (board.getPiece(x, y - 1) == null) {
                moves.add(new Move(x, y, x, y - 1, false));
            }
            if (y == 6 && board.getPiece(x, y - 2) == null) {
                moves.add(new Move(x, y, x, y - 2, false));
            }
        }


        // Check if pawn can capture a piece diagonally to the left
        if (x-1 >= 0 && y+direction >= 0 && y+direction < 8){
            Piece piece = board.getPiece(x-1, y+direction);
            if (board.getPiece(x-1, y+direction) != null && !board.getPiece(x-1, y+direction).getColor().equals(color)) {
                if (!piece.getColor().equals(color)) {
                    moves.add(new Move(x, y, x - 1, y + direction, false));
                }
            }
        }

        // Check if pawn can capture a piece diagonally to the right
        if (x+1 < 8 && y+direction >= 0 && y+direction < 8) {
            Piece piece = board.getPiece(x+1, y+direction);
            if ( piece != null && !board.getPiece(x+1, y+direction).getColor().equals(color)) {
                if (!piece.getColor().equals(color)) {
                    moves.add(new Move(x, y, x + 1, y + direction, false));
                }
            }
        }

        return moves;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY, boolean isAttack) {
        if (startX == endX && Math.abs(endY - startY) == 1 && !isAttack) {
            return (color.equals("white") && endY > startY) || (color.equals("black") && endY < startY);
        } else if (Math.abs(endX - startX) == 1 && Math.abs(endY - startY) == 1 && isAttack) {
            return (color.equals("white") && endY > startY) || (color.equals("black") && endY < startY);
        } else return color.equals("white") && startY == 1 && startX == endX && endY == 3 || color.equals("black") && startY == 6 && startX == endX && endY == 4;
    }
}