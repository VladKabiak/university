package board;
import figures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AbstractBoard {
    protected Piece[][] board;

    public AbstractBoard() {
        this.board = new Piece[8][8];
    }


    public void setPiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }
    public void setNull(int x, int y) {
        board[x][y] = null;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    };

    public Piece[][] getBoard() {
        return board;
    }

    public void createStartPieces() throws FileNotFoundException {
//        File file = new File("save.bin");
//        Save save = new Save("save.bin");
//        if (!file.exists()){
//            try {
//                save.createGame();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        Read reader = new Read("save.bin");
//        CheckPieceColor checkPieceColor = new CheckPieceColor();
//        CheckPieceType checkPieceType = new CheckPieceType();
//
//        for (int i = 0; i < 32; i++) {
//            int[] piece = reader.getNextPiece();
//            switch (checkPieceType.apply(piece[0])) {
//                case "Pawn" -> setPiece(piece[1], piece[2], new Pawn(checkPieceColor.apply(piece[3])));
//                case "King" -> setPiece(piece[1], piece[2], new King(checkPieceColor.apply(piece[3])));
//                case "Queen" -> setPiece(piece[1], piece[2], new Queen(checkPieceColor.apply(piece[3])));
//                case "Rook" -> setPiece(piece[1], piece[2], new Rook(checkPieceColor.apply(piece[3])));
//                case "Bishop" -> setPiece(piece[1], piece[2], new Bishop(checkPieceColor.apply(piece[3])));
//                case "Knight" -> setPiece(piece[1], piece[2], new Knight(checkPieceColor.apply(piece[3])));
//            }
//        }

        for (int i = 0; i <= 7; i++) {
            setPiece(i,6, new Pawn("black"));
            setPiece(i,1, new Pawn("white"));
        }

        setPiece(0,7, new Rook("black"));
        setPiece(7,7, new Rook("black"));
        setPiece(1,7, new Knight("black"));
        setPiece(6,7, new Knight("black"));
        setPiece(2,7, new Bishop("black"));
        setPiece(5,7, new Bishop("black"));
        setPiece(3,7, new Queen("black"));
        setPiece(4,7, new King("black"));

        setPiece(0,0, new Rook("white"));
        setPiece(7,0, new Rook("white"));
        setPiece(1,0, new Knight("white"));
        setPiece(6,0, new Knight("white"));
        setPiece(2,0, new Bishop("white"));
        setPiece(5,0, new Bishop("white"));
        setPiece(3,0, new Queen("white"));
        setPiece(4,0, new King("white"));
    }

    public void display() {
        for (int i = 7; i >= 0; i--) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(getPiece(j,i).getCode() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
}
