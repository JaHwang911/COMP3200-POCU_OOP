package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final Orientation orientation;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Calendar(CalendarType calendarType, Orientation orientation, Color color) {
        super(ProductType.CALENDAR);

        switch (calendarType) {
            case WALL:
                super.widthMillimeter = 400;
                super.heightMillimeter = 400;
                super.price = 1000;
                break;
            case DESK:
                super.widthMillimeter = 200;
                super.heightMillimeter = 150;
                super.price = 1000;
                break;
            case MAGNET:
                super.widthMillimeter = 100;
                super.heightMillimeter = 200;
                super.price = 1500;
                break;
        }

        this.calendarType = calendarType;
        this.color = color;
        this.orientation = orientation;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();
    }

    public int getColor() {
        return this.color.getColor();
    }

    public String getCalendarType() {
        switch (this.calendarType) {
            case WALL:
                return "Wall";
            case DESK:
                return "Desk";
            case MAGNET:
                return "Magnet";
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
        if (x < 0 || x > super.widthMillimeter || y < 0 || y > super.heightMillimeter) {
            return false;
        } else if (aperture.getWidth() > super.widthMillimeter || aperture.getHeight() > super.heightMillimeter) {
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
        if (x < 0 || x > super.widthMillimeter || y < 0 || y > super.heightMillimeter) {
            return false;
        } else if (aperture.getWidth() > super.widthMillimeter || aperture.getHeight() > super.heightMillimeter) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }
}
