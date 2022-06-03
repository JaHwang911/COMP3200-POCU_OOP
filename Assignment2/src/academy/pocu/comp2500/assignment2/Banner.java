package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Banner extends Product {
    private final BannerType bannerType;
    private BannerSize bannerSize;
    private int width;
    private int height;
    private final Orientation orientation;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Banner(BannerType bannerType, BannerSize bannerSize, Orientation orientation, Color color) {
        super(ProductType.BANNER);

        this.bannerType = bannerType;
        this.bannerSize = bannerSize;
        this.orientation = orientation;
        this.color = color;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

        switch (this.bannerSize) {
            case BANNER_1000X500:
                this.width = 1000;
                this.height = 500;
                break;
            case BANNER_1000X1000:
                this.width = 1000;
                this.height = 1000;
                break;
            case BANNER_2000X500:
                this.width = 2000;
                this.height = 500;
                break;
            case BANNER_3000X1000:
                this.width = 3000;
                this.height = 1000;
                break;
        }

        setPrice();
    }

    public int getColor() {
        return this.color.getColor();
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

    public String getSize() {
        switch (this.bannerSize) {
            case BANNER_1000X500:
                return String.format("%d mm x %d mm", 1000, 500);
            case BANNER_1000X1000:
                return String.format("%d mm x %d mm", 1000, 1000);
            case BANNER_2000X500:
                return String.format("%d mm x %d mm", 2000, 500);
            case BANNER_3000X1000:
                return String.format("%d mm x %d mm", 3000, 1000);
            default:
                assert false : "Unknown Banner size";
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

    private void setPrice() {
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

        switch (this.bannerSize) {
            case BANNER_1000X500:
                break;
            case BANNER_1000X1000:
                super.price += 200;
                break;
            case BANNER_2000X500:
                super.price += 300;
                break;
            case BANNER_3000X1000:
                super.price += 1000;
                break;
            default:
                assert false : "Wrong banner size!!";
                break;
        }
    }
}
