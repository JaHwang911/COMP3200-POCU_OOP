package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class BusinessCard extends Product {
    private final PaperType paperType;
    private final Orientation orientation;
    private final SideType sideType;
    private final ArrayList<Aperture> textApertures;
    private final ArrayList<Aperture> imageApertures;

    public BusinessCard(PaperType paperType, SideType sideType, Orientation orientation, BusinessCardColor color) {
        super.width = 90;
        super.height = 50;

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

        switch (paperType) {
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

        if (sideType == SideType.DOUBLE) {
            super.price += 30;
        }

        this.paperType = paperType;
        this.sideType = sideType;
        this.orientation = orientation;
        this.textApertures = new ArrayList<>();
        this.imageApertures = new ArrayList<>();
    }

    public String getBusinessCardName() {
        switch (this.paperType) {
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

    public PaperType getPaperType() {
        return this.paperType;
    }

    public SideType getSideType() {
        return this.sideType;
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
