package board;
import java.util.function.Function;

public class CheckPieceType implements Function<Integer, String> {
    @Override
    public String apply(Integer pieceType) {
        return switch (pieceType) {
            case 0 -> "Pawn";
            case 1 -> "King";
            case 2 -> "Queen";
            case 3 -> "Rook";
            case 4 -> "Bishop";
            case 5 -> "Knight";
            default -> throw new IllegalArgumentException("Invalid input value for pieceType: " + pieceType);
        };
    }
}
