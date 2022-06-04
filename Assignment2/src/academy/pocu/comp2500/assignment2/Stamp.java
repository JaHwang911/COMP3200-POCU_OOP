package academy.pocu.comp2500.assignment2;

public class Stamp extends Product {
    private final String text;

    public Stamp(StampSize stampSize, StampColor color, String text) {
        this.text = text;

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

        switch (color) {
            case RED:
                super.red = 0xFF;
                super.green = 0x80;
                super.blue = 0x00;
                break;
            case GREEN:
                super.red = 0x00;
                super.green = 0x80;
                super.blue = 0x00;
                break;
            case BLUE:
                super.red = 0x00;
                super.green = 0x00;
                super.blue = 0xFF;
                break;
        }
    }

    public String getStampName() {
        return String.format("Stamp (%d mm x %d mm)", super.width, super.height);
    }

    public String getText() {
        return this.text;
    }
}
