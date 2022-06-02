package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private final String text;
    private final StampSize stampSize;

    public Stamp(StampSize stampSize, StampColor color, String text) {
        super(ProductType.STAMP);

        switch (stampSize) {
            case STAMP_40MM_30MM:
                super.widthMillimeter = 40;
                super.heightMillimeter = 30;
                super.price = 2300;
                break;
            case STAMP_50MM_20MM:
                super.widthMillimeter = 50;
                super.heightMillimeter = 20;
                super.price = 2300;
                break;
            case STAMP_70MM_40MM:
                super.widthMillimeter = 70;
                super.heightMillimeter = 40;
                super.price = 2600;
                break;
            default:
                assert false : "Unknown size type";
                break;
        }

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

        this.stampSize = stampSize;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public int getColor() {
        return this.color.getColor();
    }

    public StampSize getStampSize() {
        return this.stampSize;
    }
}
