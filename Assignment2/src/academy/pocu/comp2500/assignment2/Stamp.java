package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private final String text;

    public Stamp(int width, int height, StampColor color, String text) {
        super(ProductType.STAMP);
        super.widthMillimeter = width * 10;
        super.heightMillimeter = height * 10;

        switch (color) {
            case RED:
                this.color = new Color(0xFF, 0x00, 0x00);
                break;
            case BLUE:
                this.color = new Color(0x00, 0x00, 0xFF);
                break;
            case GREEN:
                this.color = new Color(0x00, 0x80, 0x00);
                break;
            default:
                assert false : "Unknown color type!";
                break;
        }

        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public int getColor() {
        return this.color.getColor();
    }
}
