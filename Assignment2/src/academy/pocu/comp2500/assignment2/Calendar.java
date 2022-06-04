package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;
import java.util.HashMap;

public class Calendar extends Product {
    private final CalendarType calendarType;
    private final Orientation orientation;
    private final ArrayList<TextAperture> textApertures;
    private final ArrayList<ImageAperture> imageApertures;

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

    public TextAperture getTextAperture(TextAperture aperture) {
        int index = this.textApertures.indexOf(aperture);
        return this.textApertures.get(index);
    }

    public boolean addTextAperture(TextAperture aperture) {
        int posX = aperture.getPosX();
        int posY = aperture.getPosY();

        if (posX < 0 || posX > super.width || posY < 0 || posY > super.height) {
            return false;
        } else if (aperture.getWidth() + posX > super.width || aperture.getHeight() + posY > super.height) {
            return false;
        }

        this.textApertures.add(aperture);
        super.price += 5;

        return true;
    }

    public int getImageApertureCount() {
        return this.imageApertures.size();
    }

    public ImageAperture getImageAperture(ImageAperture aperture) {
        int index = this.imageApertures.indexOf(aperture);
        return this.imageApertures.get(index);
    }

    public boolean addImageAperture(ImageAperture aperture) {
        int posX = aperture.getPosX();
        int posY = aperture.getPosY();

        if (posX < 0 || posX > super.width || posY < 0 || posY > super.height) {
            return false;
        } else if (aperture.getWidth() + posX > super.width || aperture.getHeight() + posY > super.height) {
            return false;
        }

        this.imageApertures.add(aperture);
        super.price += 5;

        return true;
    }
}
