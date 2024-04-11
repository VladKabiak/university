package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece{
    public Knight(String color) {
        super(color, "Knight");
    }

    @Override
    public char getCode() {
        if (color.equals("white")){
            return 'N';
        } else {
            return 'n';
        }
    }

    @Override
    public List<Move> getPossibleMoves(MoveValidator validator, Board board, int x, int y, Player player, Player opponent) {
        List<Move> moves = new ArrayList<>();

        int[][] squares = { {2,1}, {1,2}, {-1,2}, {-2,1}, {-2,-1}, {-1,-2}, {1,-2}, {2,-1} };

        for (int[] move : squares) {
            int nextX = x + move[0];
            int nextY = y + move[1];

            if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
                Piece piece = board.getPiece(nextX, nextY);
                if (board.getPiece(nextX, nextY) != null && !piece.getColor().equals(this.color) || board.getPiece(nextX, nextY) == null) {
                    moves.add(new Move(x, y, nextX, nextY, true));
                }
            }
        }

        return moves;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, boolean isAttack) {
        int xDiff = Math.abs(endX - startX);
        int yDiff = Math.abs(endY - startY);

        return (xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2);
    }
}