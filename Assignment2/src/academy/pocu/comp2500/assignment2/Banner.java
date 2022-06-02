package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.app.CalendarType;

import java.util.HashMap;

public class Banner extends Product {
    private final BannerType bannerType;
    private final PrintDirection printDirection;
    private final Color color;
    private final HashMap<String, TextAperture> textApertures;
    private final HashMap<String, ImageAperture> imageApertures;

    public Banner(int width, int height, BannerType bannerType, PrintDirection printDirection, Color color) {
        super.widthMillimeter = width * 1000;
        super.heightMillimeter = height * 1000;
        super.productType = ProductType.BANNER;

        this.bannerType = bannerType;
        this.printDirection = printDirection;
        this.color = color;
        this.textApertures = new HashMap<>();
        this.imageApertures = new HashMap<>();

        setPrice();
    }

    public Color getColor() {
        return this.color;
    }

    public BannerType getBannerType() {
        return this.bannerType;
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

    private void setPrice() {

    }
}
