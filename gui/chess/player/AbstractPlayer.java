package player;
import board.Board;
import figures.*;
import moveValidator.MoveValidator;
import moveValidator.PieceInfo;

import java.util.ArrayList;

public abstract class AbstractPlayer {
    protected String color;
    protected String name;

    public AbstractPlayer(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public abstract int[] choosePiece(MoveValidator validator, Player opponentPlayer, Board board);
    public abstract void chooseMove(int[] pos, Piece piece, MoveValidator validator, Board board);

    public ArrayList<PieceInfo> getPieces(Board board) {
        ArrayList<PieceInfo> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board.getPiece(i,j);
                if (piece != null) {
                    if (piece.getColor().equals(color)) {
                        pieces.add(new PieceInfo(piece, i, j));
                    }
                }
            }
        }
        return pieces;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}