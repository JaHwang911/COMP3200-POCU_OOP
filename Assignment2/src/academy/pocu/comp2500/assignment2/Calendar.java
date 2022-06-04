package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final Orientation orientation;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Calendar(CalendarType calendarType, Orientation orientation) {
        super.color = new Color(0xFF, 0xFF, 0xFF);

        switch (calendarType) {
            case WALL:
                super.width = 400;
                super.height = 400;
                super.price = 1000;
                break;
            case DESK:
                super.width = 200;
                super.height = 150;
                super.price = 1000;
                break;
            case MAGNET:
                super.width = 100;
                super.height = 200;
                super.price = 1500;
                break;
            default:
                assert false : "Unknown calendar type";
                break;
        }

        this.calendarType = calendarType;
        this.orientation = orientation;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();
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

    public CalendarType getCalendarType() {
        return this.calendarType;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public int getTextApertureCount() {
        return this.textApertures.size();
    }

    public TextAperture getTextAperture(String text) {
        return this.textApertures.get(text);
    }

    public boolean addTextAperture(int x, int y, TextAperture aperture) {
        if (x < 0 || x > super.width || y < 0 || y > super.height) {
            return false;
        } else if (aperture.getWidth() + x > super.width || aperture.getHeight() + y > super.height) {
            return false;
        }

        this.textApertures.put(aperture.getText(), aperture);
        super.price += 5;

        return true;
    }

    public int getImageApertureCount() {
        return this.imageApertures.size();
    }

    public ImageAperture getImageAperture(String imagePath) {
        return this.imageApertures.get(imagePath);
    }

    public boolean addImageAperture(int x, int y, ImageAperture aperture) {
        if (x < 0 || x > super.width || y < 0 || y > super.height) {
            return false;
        } else if (aperture.getWidth() + x > super.width || aperture.getHeight() + y > super.height) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }
}
