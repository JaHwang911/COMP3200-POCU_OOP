package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private Color color;
    private final StampType stampType;
    private final String text;

    public Stamp(StampType stampType, StampColor color, String text) {
        super(ProductType.STAMP);

        this.stampType = stampType;
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

    public StampType getStampType() {
        return this.stampType;
    }

    public String getStampInfo() {
        switch (this.stampType) {
            case SMALL:
                return String.format("Stamp (%d mm x %d mm)", 40, 30);
            case MEDIUM:
                return String.format("Stamp (%d mm x %d mm)", 50, 20);
            case LARGE:
                return String.format("Stamp (%d mm x %d mm)", 70, 40);
            default:
                assert false : "Unknown stamp size";
                return "";
        }
    }

    private void setPrice() {
        switch (this.stampType) {
            case SMALL:
            case MEDIUM:
                super.price = 2300;
                break;
            case LARGE:
                super.price = 2600;
                break;
            default:
                assert false : "Unknown stamp size type";
                break;
        }
    }
}
