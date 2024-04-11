import board.*;
import moveValidator.MoveValidator;
import player.Player;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class ChessGame {
    public static void main(String[] args) throws FileNotFoundException {
        Board board = new Board();
        board.createStartPieces();
        Player player1 = new Player("white", "Player1");
        Player player2 = new Player("black", "Player2");
        MoveValidator validator = new MoveValidator();

        boolean isMate = false;
        while (!isMate) {
            board.display();
            int[] pos1 = player1.choosePiece(validator, player2, board);
            if (Arrays.equals(pos1, new int[]{-1})) {
                System.out.println("AHAHHAHAHAH " + player1.getName() + " YOU ARE LOOOSER");
                isMate = true;
            } else {
                player1.chooseMove(pos1, board.getPiece(pos1[0], pos1[1]), validator, board);
            }
            board.display();
            int[] pos2 = player2.choosePiece(validator, player1, board);
            if (Arrays.equals(pos2, new int[]{-1})) {
                System.out.println("AHAHHAHAHAH " + player2.getName() + " YOU ARE LOOOSER");
                isMate = true;
            } else {
                player2.chooseMove(pos2, board.getPiece(pos2[0], pos2[1]), validator, board);
            }
        }
    }
}