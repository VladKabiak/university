package figures;

public class Move {

    int startX;
    int startY;
    int endX;
    int endY;

    boolean isKnight;

    public Move(int startX, int startY, int endX, int endY, boolean isKnight) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.isKnight = isKnight;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public boolean getIsKnight() {
        return isKnight;
    }
}
