package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.app.CalendarType;

import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final PrintDirection printDirection;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Calendar(CalendarType calendarType, PrintDirection printDirection, Color color) {
        switch (calendarType) {
            case WALL:
                super.widthMillimeter = 400;
                super.heightMillimeter = 400;
                break;
            case DESK:
                super.widthMillimeter = 200;
                super.heightMillimeter = 150;
                break;
            case MAGNET:
                super.widthMillimeter = 100;
                super.heightMillimeter = 200;
                break;
        }
        super.productType = ProductType.CALENDAR;

        this.calendarType = calendarType;
        this.color = color;
        this.printDirection = printDirection;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

        setPrice();
    }

    public Color getColor() {
        return this.color;
    }

    public CalendarType getCalendarType() {
        return this.calendarType;
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
        switch (this.calendarType) {
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
