package board;
import java.util.function.Function;

public class CheckPieceColor implements Function<Integer, String> {
    @Override
    public String apply(Integer color) {
        if (color == 1) {
            return "black";
        } else if (color == 0) {
            return "white";
        } else {
            throw new IllegalArgumentException("Invalid input value for color: " + color);
        }
    }
}
