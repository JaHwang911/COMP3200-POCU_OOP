package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private String text;

    public Stamp(int width, int height, StampColor color, String text) {
        super.widthMillimeter = width * 10;
        super.heightMillimeter = height * 10;
        super.productType = ProductType.STAMP;

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

        setPrice(width * height);
    }

    public Color getColor() {
        return this.color;
    }

    private void setPrice(int area) {
        if (area <= 12) {
            super.price = 2300;
            return;
        }

        super.price = 2600;
    }
}
