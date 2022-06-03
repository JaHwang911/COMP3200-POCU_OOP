package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private StampSize stampSize;
    private final String text;

    public Stamp(StampSize stampSize, StampColor color, String text) {
        super(ProductType.STAMP);

        this.stampSize = stampSize;
        this.text = text;

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

        setPrice();
    }

    public int getColor() {
        return this.color.getColor();
    }

    public String getText() {
        return this.text;
    }

    public StampSize getStampSize() {
        return this.stampSize;
    }

    public String getProductInfo() {
        switch (this.stampSize) {
            case STAMP_4X3:
                return String.format("Stamp (%d mm x %d mm)", 40, 30);
            case STAMP_5X2:
                return String.format("Stamp (%d mm x %d mm)", 50, 20);
            case STAMP_7X4:
                return String.format("Stamp (%d mm x %d mm)", 70, 40);
            default:
                assert false : "Unknown stamp size";
                return "";
        }
    }

    private void setPrice() {
        switch (this.stampSize) {
            case STAMP_4X3:
            case STAMP_5X2:
                super.price = 2300;
                break;
            case STAMP_7X4:
                super.price = 2600;
                break;
            default:
                assert false : "Unknown stamp size type";
                break;
        }
    }
}
