package player;

import board.Board;
import figures.Move;
import figures.Piece;
import moveValidator.MoveValidator;

import java.util.*;

public class Player extends AbstractPlayer {
    public Player(String color, String name) {
        super(color, name);
    }

    @Override
    public int[] choosePiece(MoveValidator validator, Player opponentPlayer, Board board) {
        List<Move> kingAttacks = validator.isKingInCheck(this, opponentPlayer, board);

        if (!kingAttacks.isEmpty()){
            if (!validator.isPreventable(board, kingAttacks.get(0).getStartX(),kingAttacks.get(0).getStartY(),kingAttacks.get(0).getEndX(),kingAttacks.get(0).getEndY(), false, this, opponentPlayer)){
                return new int[]{-1};
            } else {
                System.out.println(name + " your king is under attack! Choose a piece to prevent mate:");
            }
        } else {
            System.out.println(name + ", choose a piece to move: ");
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return new int[]{input.charAt(0) - 'a', Integer.parseInt(input.substring(1))-1};
    }

    public boolean isPieceExists(Board board, int x, int y){
        Piece piece = board.getPiece(x, y);
        return piece != null;
    }

    @Override
    public void chooseMove(int[] pos, Piece piece, MoveValidator validator, Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", choose a move for the piece: ");
        String input = scanner.nextLine();
        int[] move = {input.charAt(0) - 'a', Integer.parseInt(input.substring(1))-1};
        if (validator.isValidMove(board, pos[0], pos[1], move[0], move[1], isPieceExists(board, move[0], move[1]), piece)) {
            board.setNull(pos[0], pos[1]);
            board.setPiece(move[0], move[1], piece);
        } else {
            System.out.println("This move is wrong, make another");
            chooseMove(pos, piece, validator, board);
        }
    }
}