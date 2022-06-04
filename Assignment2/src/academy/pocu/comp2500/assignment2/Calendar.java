package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final Orientation orientation;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Calendar(CalendarType calendarType, Orientation orientation, Color color) {
        super.color = color;

        this.calendarType = calendarType;
        this.orientation = orientation;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

        switch (calendarType) {
            case WALL:
                this.width = 400;
                this.height = 400;
                super.price = 1000;
                break;
            case DESK:
                this.width = 200;
                this.height = 150;
                super.price = 1000;
                break;
            case MAGNET:
                this.width = 100;
                this.height = 200;
                super.price = 1500;
                break;
            default:
                assert false : "Unknown calendar type";
                break;
        }
    }

    public String getCalendarName() {
        switch (this.calendarType) {
            case WALL:
                return "Wall Calendar";
            case DESK:
                return "Desk Calendar";
            case MAGNET:
                return "Magnet Calendar";
            default:
                assert false : "Unknown calendar type";
                return "";
        }
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public TextAperture getTextAperture(String text) {
        return this.textApertures.get(text);
    }

    public boolean addTextAperture(int x, int y, TextAperture aperture) {
        if (x < 0 || x > this.width || y < 0 || y > this.height) {
            return false;
        } else if (aperture.getWidth() > this.width || aperture.getHeight() > this.height) {
            return false;
        }

        this.textApertures.put(aperture.getText(), aperture);
        super.price += 5;

        return true;
    }

    public ImageAperture getImageAperture(String imagePath) {
        return this.imageApertures.get(imagePath);
    }

    public boolean addImageAperture(int x, int y, ImageAperture aperture) {
        if (x < 0 || x > this.width || y < 0 || y > this.height) {
            return false;
        } else if (aperture.getWidth() > this.width || aperture.getHeight() > this.height) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }
}
