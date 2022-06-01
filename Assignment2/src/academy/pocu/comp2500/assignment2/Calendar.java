package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.app.CalendarType;

import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType type;
    private final PrintDirection printDirection;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Calendar(int width, int height, CalendarType type, PrintDirection printDirection, Color color) {
        super(width * 10, height * 10);

        this.type = type;
        this.color = color;
        this.printDirection = printDirection;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

        setPrice();
    }

    public int getColor() {
        return this.color.getColor();
    }

    public CalendarType getCalendarType() {
        return this.type;
    }

    public boolean addTextAperture(int posX, int posY, TextAperture aperture) {
        if (posX < 0 || posX > super.widthMillimeter || posY < 0 || posY > super.heightMillimeter) {
            return false;
        } else if (aperture.getWidth() > super.widthMillimeter || aperture.getHeight() > super.heightMillimeter) {
            return false;
        }

        this.textApertures.put(aperture.getText(), aperture);
        super.price += 5;

        return true;
    }

    public boolean addImageAperture(int posX, int posY, ImageAperture aperture) {
        if (posX < 0 || posX > super.widthMillimeter || posY < 0 || posY > super.heightMillimeter) {
            return false;
        } else if (aperture.getWidth() > super.widthMillimeter || aperture.getHeight() > super.heightMillimeter) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }

    public int getTextApertureCount() {
        return this.textApertures.size();
    }

    public TextAperture getTextAperture(String text) {
        return this.textApertures.get(text);
    }

    public int getImageApertureCount() {
        return this.imageApertures.size();
    }

    public ImageAperture getImageAperture(String path) {
        return this.imageApertures.get(path);
    }

    private void setPrice() {
        switch (type) {
            case WALL:
            case DESK:
                super.price = 1000;
                break;
            case MAGNET:
                super.price = 1500;
                break;
            default:
                assert false : "Unknown CalendarType";
                break;
        }
    }
}
