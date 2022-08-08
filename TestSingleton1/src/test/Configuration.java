package test;

public class Configuration {
    private static Configuration instance;
    private int x;
    private int y;
    private final int width;
    private final int height;

    private Configuration() {
        this.x = 50;
        this.y = 50;
        this.width = 600;
        this.height = 400;
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }

        return instance;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int position) {
        this.x = position;
    }

    public void setY(int position) {
        this.y = position;
    }
}
