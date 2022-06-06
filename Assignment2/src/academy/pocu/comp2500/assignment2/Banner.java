package academy.pocu.comp2500.assignment2;

public class Banner extends CustomizingProduct {
    private final BannerType bannerType;
    private final BannerSize bannerSize;
    private final Orientation orientation;

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
        this.bannerSize = bannerSize;
        this.orientation = orientation;
    }

    public String getDisplayName() {
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

    public BannerSize getBannerSize() {
        return this.bannerSize;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }
}
