package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private String text;

    public Stamp(int width, int height, String text, StampColor color) {
        super(width * 10, height * 10);

        this.text = text;

        switch (color) {
            case RED:
                this.color = new Color(255, 0, 0);
                break;
            case BLUE:
                this.color = new Color(0, 0, 255);
                break;
            case GREEN:
                this.color = new Color(0, 128, 0);
                break;
            default:
                assert false : "Unknown color type!";
                break;
        }

        setPrice(width * height);
    }

    public int getColor() {
        return this.color.getColor();
    }

    private void setPrice(int area) {
        if (area <= 12) {
            super.price = 2300;
            return;
        }

        super.price = 2600;
    }
}
