package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final Orientation orientation;
    private final ArrayList<Aperture> textApertures;
    private final ArrayList<Aperture> imageApertures;

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
        this.textApertures = new ArrayList<>();
        this.imageApertures = new ArrayList<>();
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

    public int getImageApertureCount() {
        return this.imageApertures.size();
    }

    public boolean addAperture(Aperture aperture) {
        int posX = aperture.getPosX();
        int posY = aperture.getPosY();

        if (posX < 0 || posY < 0) {
            return false;
        } else if (posX + aperture.getWidth() > super.width || posY + aperture.getHeight() > super.height) {
            return false;
        }

        switch (aperture.getType()) {
            case TEXT:
                this.textApertures.add(aperture);
                break;
            case IMAGE:
                this.imageApertures.add(aperture);
                break;
            default:
                assert false : "Unknown aperture type";
                break;
        }

        super.price += 5;

        return true;
    }
}
