package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Banner extends Product {
    private final BannerType bannerType;
    private final Orientation orientation;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Banner(BannerType bannerType, BannerSize bannerSize, Orientation orientation, Color color) {
        super.color = color;

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

        switch (bannerSize) {
            case BANNER_1000X500:
                super.width = 1000;
                super.height = 500;
                break;
            case BANNER_1000X1000:
                super.width = 1000;
                super.height = 1000;
                super.price += 200;
                break;
            case BANNER_2000X500:
                super.width = 2000;
                super.height = 500;
                super.price += 300;
                break;
            case BANNER_3000X1000:
                super.width = 3000;
                super.height = 1000;
                super.price += 1000;
                break;
            default:
                assert false : "Unknown banner size";
                break;
        }

        this.bannerType = bannerType;
        this.orientation = orientation;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();
    }

    public String getBannerName() {
        switch (this.bannerType) {
            case GLOSS:
                return String.format("Gloss Banner (%d mm x %d mm)", super.width, super.height);
            case SCRIM:
                return String.format("Scrim Banner (%d mm x %d mm)", super.width, super.height);
            case MESH:
                return String.format("Mesh Banner (%d mm x %d mm)", super.width, super.height);
            default:
                assert false : "Unknown Banner type";
                return "";
        }
    }

    public BannerType getBannerType() {
        return this.bannerType;
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
        if (x < 0 || x > this.width || y < 0 || y > this.height) {
            return false;
        } else if (aperture.getWidth() > super.width || aperture.getHeight() > super.height) {
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
        } else if (aperture.getWidth() > super.width || aperture.getHeight() > super.height) {
            return false;
        }

        this.imageApertures.put(aperture.getPath(), aperture);
        super.price += 5;

        return true;
    }
}
