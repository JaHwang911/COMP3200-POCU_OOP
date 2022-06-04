package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class BusinessCard extends Product {
    private final BusinessCardType businessCardType;
    private final Orientation orientation;
    private final SideType sideType;
    private final int WIDTH = 90;
    private final int HEIGHT = 50;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public BusinessCard(BusinessCardType businessCardType, SideType sides, Orientation orientation, BusinessCardColor color) {

        switch (businessCardType) {
            case LINEN:
                super.price = 110;
                break;
            case LAID:
                super.price = 120;
                break;
            case SMOOTH:
                super.price = 100;
                break;
            default:
                assert false : "Unknown card type";
                break;
        }

        switch (sides) {
            case DOUBLE:
                super.price += 30;
                break;
            case SINGLE:
                break;
            default:
                assert false : "Unknown side type";
                break;
        }

        switch (color) {
            case GRAY:
                super.color = new Color(0xE6, 0xE6, 0xE6);
                break;
            case IVORY:
                super.color = new Color(0xFF, 0xFF, 0xF0);
                break;
            case WHITE:
                super.color = new Color(0xFF, 0xFF, 0xFF);
                break;
            default:
                assert false : "Unknown color type";
                break;
        }

        this.businessCardType = businessCardType;
        this.sideType = sides;
        this.orientation = orientation;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();
    }

    public String getBusinessCardName() {
        switch (this.businessCardType) {
            case LINEN:
                return "Linen Business Card";
            case LAID:
                return "Laid Business Card";
            case SMOOTH:
                return "Smooth Business Card";
            default:
                assert false : "Unknown card type";
                return "";
        }
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public SideType getSideType() {
        return this.sideType;
    }

    public TextAperture getTextAperture(String text) {
        return this.textApertures.get(text);
    }

    public boolean addTextApertureToFront(int x, int y, TextAperture aperture) {
        if (x < 0 || x > this.WIDTH || y < 0 || y > this.HEIGHT) {
            return false;
        } else if (aperture.getWidth() > this.WIDTH || aperture.getHeight() > this.HEIGHT) {
            return false;
        }

        this.textApertures.put(aperture.getText(), aperture);
        super.price += 5;

        return true;
    }

    public boolean addTextApertureToBack(int x, int y, TextAperture aperture) {
        if (x < 0 || x > this.WIDTH || y < 0 || y > this.HEIGHT) {
            return false;
        } else if (aperture.getWidth() > this.WIDTH || aperture.getHeight() > this.HEIGHT) {
            return false;
        } else if (this.sideType != SideType.DOUBLE) {
            return false;
        }

        this.textApertures.put(aperture.getText(), aperture);
        super.price += 5;

        return true;
    }

    public ImageAperture getImageAperture(String imagePath) {
        return this.imageApertures.get(imagePath);
    }

    public boolean addImageApertureToFront(int x, int y, ImageAperture aperture) {
        if (x < 0 || x > this.WIDTH || y < 0 || y > this.HEIGHT) {
            return false;
        } else if (aperture.getWidth() > this.WIDTH || aperture.getHeight() > this.HEIGHT) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }

    public boolean addImageApertureToBack(int x, int y, ImageAperture aperture) {
        if (x < 0 || x > this.WIDTH || y < 0 || y > this.HEIGHT) {
            return false;
        } else if (aperture.getWidth() > this.WIDTH || aperture.getHeight() > this.HEIGHT) {
            return false;
        } else if (this.sideType != SideType.DOUBLE) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }
}
