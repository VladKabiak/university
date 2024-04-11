package figures;

import board.Board;
import moveValidator.MoveValidator;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece{
    public King(String color) {
        super(color, "King");
    }

    @Override
    public char getCode() {
        if (color.equals("white")){
            return 'K';
        } else {
            return 'k';
        }
    }

    @Override
    public List<Move> getPossibleMoves(MoveValidator validator, Board board, int x, int y, Player player, Player opponentPlayer) {
        List<Move> moves = new ArrayList<>();
        String color = board.getPiece(x, y).getColor();
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                if (board.getPiece(nx, ny) != null && !board.getPiece(nx, ny).getColor().equals(color)){
                    board.setNull(nx, ny);
                    board.setNull(x, y);
                    board.setPiece(nx, ny, new King(this.color));
                    if (validator.isKingInCheck(player, opponentPlayer, board).isEmpty()){
                        moves.add(new Move(x, y, nx, ny, false));
                    }
                    board.setNull(nx,ny);
                    board.setPiece(x,y, new King(this.color));
                } else if (board.getPiece(nx, ny) == null) {
                    board.setNull(x, y);
                    board.setPiece(nx, ny, new King(this.color));
                    if (validator.isKingInCheck(player, opponentPlayer, board).isEmpty()){
                        moves.add(new Move(x, y, nx, ny, false));
                    }
                    board.setNull(nx,ny);
                    board.setPiece(x,y, new King(this.color));
                }
            }
        }
        return moves;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, boolean isAttack) {
        int xDiff = Math.abs(endX - startX);
        int yDiff = Math.abs(endY - startY);

        return xDiff <= 1 && yDiff <= 1;
    }
}
