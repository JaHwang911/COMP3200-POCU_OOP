package academy.pocu.comp2500.assignment2;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomizingProduct extends Product {
    protected final ArrayList<Aperture> textApertures;
    protected final ArrayList<Aperture> imageApertures;

    protected CustomizingProduct() {
        this.textApertures = new ArrayList<>();
        this.imageApertures = new ArrayList<>();
    }

    public int getTextApertureCount() {
        return this.textApertures.size();
    }

    public Aperture getTextAperture(Aperture aperture) {
        int index = this.textApertures.indexOf(aperture);
        return this.textApertures.get(index);
    }

    public int getImageApertureCount() {
        return this.imageApertures.size();
    }

    public Aperture getImageAperture(Aperture aperture) {
        int index = this.imageApertures.indexOf(aperture);
        return this.imageApertures.get(index);
    }

    public boolean addAperture(Aperture aperture) {
        int posX = aperture.getPosX();
        int posY = aperture.getPosY();

        if (posX >= super.width || posY >= super.height) {
            return false;
        } else if (posX + aperture.getWidth() < 1 || posY + aperture.getHeight() < 1) {
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
