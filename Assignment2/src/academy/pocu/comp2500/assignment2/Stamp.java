package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private int widthMillimeter;
    private int heightMillimeter;
    private final String text;

    public Stamp(int width, int height, StampColor color, String text) {
        super(ProductType.STAMP);
        super.widthMillimeter = width;
        super.heightMillimeter = height;

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
        setPrice();
    }

    public int getColor() {
        return this.color.getColor();
    }

    public String getText() {
        return this.text;
    }

    private void setPrice() {
        if (this.widthMillimeter * this.heightMillimeter <= 120) {
            super.price = 2300;
        }

        super.price = 2600;
    }
}
