package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private final String text;

    public Stamp(StampSize stampSize, StampColor color, String text) {
        switch (color) {
            case RED:
                super.color = new Color(0xFF, 0x00, 0x00);
                break;
            case GREEN:
                super.color = new Color(0x00, 0x80, 0x00);
                break;
            case BLUE:
                super.color = new Color(0x00, 0x00, 0xFF);
                break;
        }

        switch (stampSize) {
            case SMALL:
                super.width = 40;
                super.height = 30;
                super.price = 2300;
                break;
            case MEDIUM:
                super.width = 50;
                super.height = 20;
                super.price = 2300;
                break;
            case LARGE:
                super.width = 70;
                super.height = 40;
                super.price = 2600;
                break;
            default:
                assert false : "Unknown stamp size type";
                break;
        }

        this.text = text;
    }

    public String getStampName() {
        return String.format("Stamp (%d mm x %d mm)", super.width, super.height);
    }

    public String getText() {
        return this.text;
    }
}
