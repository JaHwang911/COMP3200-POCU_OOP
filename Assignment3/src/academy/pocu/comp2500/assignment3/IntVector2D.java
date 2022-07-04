package academy.pocu.comp2500.assignment3;

public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistance(IntVector2D position) {
        return Math.abs(this.x - position.x) + Math.abs(this.y - position.y);
    }

    public boolean isSamePosition(IntVector2D position) {
        return (this.x == position.x && this.y == position.y);
    }

    public boolean isSamePosition(int x, int y) {
        return (this.x == x && this.y == y);
    }
}
