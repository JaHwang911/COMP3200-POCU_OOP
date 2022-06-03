package academy.pocu.comp2500.assignment2;

import java.util.HashMap;

public class Banner extends Product {
    private final BannerType bannerType;
    private final Orientation orientation;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Banner(int width, int height, BannerType bannerType, Orientation orientation, Color color) {
        super(ProductType.BANNER);
        super.widthMillimeter = width;
        super.heightMillimeter = height;

        this.bannerType = bannerType;
        this.orientation = orientation;
        this.color = color;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

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

        int sizeType = super.widthMillimeter + super.heightMillimeter;

        switch (sizeType) {
            case 1500:
                break;
            case 2000:
                super.price += 200;
                break;
            case 2500:
                super.price += 300;
                break;
            case 4000:
                super.price += 1000;
                break;
            default:
                assert false : "Wrong banner size!!";
                break;
        }
    }
}
