package moveValidator;

import board.Board;
import figures.Move;
import figures.Piece;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator extends AbstractMoveValidator {

    public boolean isPreventable(Board board, int startX, int startY, int endX, int endY, boolean isKnight, Player player, Player opponentPlayer) {
        Piece king = board.getPiece(endX, endY);
        // check if king can go somewhere where he wouldn't be in check
        if (!king.getPossibleMoves(this, board, endX, endY, player, opponentPlayer).isEmpty()){
            return true;
        } else if (!isKnight) {
            int[][] squares = new int[0][];
            int size;

            if (startX == endX) { // vertical line
                size = Math.abs(endY - startY) + 1;
                squares = new int[size][2];
                int y = Math.min(startY, endY);
                for (int i = 0; i < size; i++) {
                    squares[i][0] = startX;
                    squares[i][1] = y;
                    y++;
                }
            } else if (startY == endY) { // horizontal line
                size = Math.abs(endX - startX) + 1;
                squares = new int[size][2];
                int x = Math.min(startX, endX);
                for (int i = 0; i < size; i++) {
                    squares[i][0] = x;
                    squares[i][1] = startY;
                    x++;
                }
            } else if (Math.abs(startX - endX) == Math.abs(startY - endY)) { // diagonal line
                size = Math.abs(endX - startX) + 1;
                squares = new int[size][2];
                int x = startX;
                int y = startY;
                int xStep = Integer.compare(endX, startX);
                int yStep = Integer.compare(endY, startY);
                for (int i = 0; i < size; i++) {
                    squares[i][0] = x;
                    squares[i][1] = y;
                    x += xStep;
                    y += yStep;
                }
            }
            for (int[] square: squares) {
                System.out.println(square[0] + " " +square[1]);
            }
            // Check if any of the player's pieces can be moved to block the check
            for (PieceInfo pieceInfo : player.getPieces(board)) {
                Piece piece = pieceInfo.getPiece();
                if (piece.getType().equals("King")) {
                    continue;
                }
                for (int[] square: squares) {
                    if (piece.isValidMove(pieceInfo.getX(), pieceInfo.getY(), square[0], square[1], false)){
                        List<Move> possibleMoves = piece.getPossibleMoves(this, board, pieceInfo.getX(), pieceInfo.getY(), player, opponentPlayer);
                        for (Move move : possibleMoves) {
                            if (move.getEndX() == square[0] && move.getEndY() == square[1]){
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            // check if any of the player's pieces can be moved to capture the attacking piece
            for (PieceInfo pieceInfo : player.getPieces(board)) {
                Piece piece = pieceInfo.getPiece();
                List<Move> possibleMoves = piece.getPossibleMoves(this, board, pieceInfo.getX(), pieceInfo.getY(), player, opponentPlayer);
                for (Move move : possibleMoves) {
                    Piece attackedPiece = board.getPiece(move.getEndX(), move.getEndY());
                    if (attackedPiece != null && attackedPiece == board.getPiece(startX, startY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Move> isKingInCheck(Player player, Player opponentPlayer, Board board) {
        List<Move> moves = new ArrayList<>();
        for (PieceInfo pieceInfo : opponentPlayer.getPieces(board)) {
            Piece piece = pieceInfo.getPiece();
            if (!piece.getType().equals("King")) {
                List<Move> possibleMoves = piece.getPossibleMoves(this, board, pieceInfo.getX(), pieceInfo.getY(), player, opponentPlayer);
                for (Move move : possibleMoves) {
                    Piece attackedPiece = board.getPiece(move.getEndX(), move.getEndY());
                    if (attackedPiece != null && attackedPiece.getType().equals("King") && attackedPiece.getColor().equals(player.getColor())) {
                        moves.add(move);
                    }
                }
            }
        }
        return moves;
    }
}
