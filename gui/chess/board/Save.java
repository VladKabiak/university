package board;

import figures.Piece;

import java.io.FileOutputStream;
import java.io.IOException;

public class Save {
    final String url;

    public Save(String url) {
        this.url = url;
    }

    public void createGame() throws IOException {
        FileOutputStream fos = new FileOutputStream(url);

        for (int i = 0; i <= 7; i++) {
            writePieceToFile(fos, i, 1, 0, false);
            writePieceToFile(fos, i, 6, 0, true);
        }

        writePieceToFile(fos, 0, 0, 3, false);
        writePieceToFile(fos, 7, 0, 3, false);
        writePieceToFile(fos, 1, 0, 5, false);
        writePieceToFile(fos, 6, 0, 5, false);
        writePieceToFile(fos, 2, 0, 4, false);
        writePieceToFile(fos, 5, 0, 4, false);
        writePieceToFile(fos, 3, 0, 2, false);
        writePieceToFile(fos, 4, 0, 1, false);

        writePieceToFile(fos, 0, 7, 3, true);
        writePieceToFile(fos, 7, 7, 3, true);
        writePieceToFile(fos, 1, 7, 5, true);
        writePieceToFile(fos, 6, 7, 5, true);
        writePieceToFile(fos, 2, 7, 4, true);
        writePieceToFile(fos, 5, 7, 4, true);
        writePieceToFile(fos, 3, 7, 2, true);
        writePieceToFile(fos, 4, 7, 1, true);

        fos.close();
    }

    public void writePieceToFile(FileOutputStream fos, int x, int y, int type, boolean isBlack) throws IOException {
        int piece = type << 5; // Shift the type to the left by 5 bits
        piece |= (x << 1); // Shift the horizontal position to the left by 1 bit
        fos.write(piece);

        piece = (y << 4); // Shift the vertical position to the right by 2 bits
        piece |= ((isBlack ? 1 : 0) << 3); // Set the color bit based on the isWhite boolean
        fos.write(piece);
    }
}
