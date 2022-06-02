package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Banner extends Product {
    private final BannerType bannerType;
    private final Orientation orientation;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Banner(BannerSize size, BannerType bannerType, Orientation orientation, Color color) {
        super(ProductType.BANNER);

        switch (bannerType) {
            case GLOSS:
                super.price = 5000;
                break;
            case SCRIM:
            case MESH:
                super.price = 5100;
                break;
            default:
                assert false : "Unknown banner type";
                break;
        }

        switch (size) {
            case BANNER_1000MM_500MM:
                super.widthMillimeter = 1000;
                super.heightMillimeter = 500;
                break;
            case BANNER_1000MM_1000MM:
                super.widthMillimeter = 1000;
                super.heightMillimeter = 1000;
                super.price += 200;
                break;
            case BANNER_2000MM_500MM:
                super.widthMillimeter = 2000;
                super.heightMillimeter = 500;
                super.price += 300;
                break;
            case BANNER_3000MM_1000MM:
                super.widthMillimeter = 3000;
                super.heightMillimeter = 1000;
                super.price += 1000;
                break;
            default:
                assert false : "Unknown banner size";
                break;
        }

        this.bannerType = bannerType;
        this.orientation = orientation;
        this.color = color;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();
    }

    public Color getColor() {
        return this.color;
    }

    public String getBannerType() {
        switch (this.bannerType) {
            case GLOSS:
                return "Gloss";
            case SCRIM:
                return "Scrim";
            case MESH:
                return "Mesh";
            default:
                assert false : "Unknown banner type";
                return "";
        }
    }

    public Orientation getOrientation() {
        return this.orientation;
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
}
