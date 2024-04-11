package figures;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements Piece, Cloneable {
    protected String color;
    protected String type;

    public AbstractPiece(String color, String type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getType() {
        return type;
    }
}
