package academy.pocu.comp2500.assignment2;

import java.util.ArrayList;

public class BusinessCard extends CustomizingProduct {
    private final PaperType paperType;
    private final SideType sideType;
    private final Orientation orientation;

    public BusinessCard(PaperType paperType, SideType sides, Orientation orientation, BusinessCardColor color) {
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

        if (sides == SideType.DOUBLE) {
            super.price += 30;
        }

        this.paperType = paperType;
        this.sideType = sides;
        this.orientation = orientation;
    }

    public String getDisplayName() {
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
}
