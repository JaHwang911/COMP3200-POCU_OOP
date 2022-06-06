package academy.pocu.comp2500.assignment2.app;

import academy.pocu.comp2500.assignment2.*;
import academy.pocu.comp2500.assignment2.registry.Registry;

import java.util.ArrayList;

/*
    달력을 흰색으로 하지 않음

*/
public class Program {
    public static void main(String[] args) {
        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        testStamp();
        testBusinessCard();
        testCart();
        testAddApertures();

        System.out.println("No prob");
    }

    private static void testStamp() {
        Stamp stamp0 = new Stamp(StampSize.SMALL, StampColor.RED, "Stamp0");
        Stamp stamp1 = new Stamp(StampSize.MEDIUM, StampColor.BLUE, "Stamp1");
        Stamp stamp2 = new Stamp(StampSize.LARGE, StampColor.GREEN, "Stamp2");

        assert stamp0.getWidth() == 40;
        assert stamp0.getHeight() == 30;
        assert stamp0.getStampName().equals("Stamp (40 mm x 30 mm)");
        assert stamp0.getPrice() == 2300;
        assert stamp0.getText().equals("Stamp0");
        assert stamp0.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp0.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp0.getDeliveryType() == DeliveryMethod.SHIP;

        assert stamp1.getWidth() == 50;
        assert stamp1.getHeight() == 20;
        assert stamp1.getStampName().equals("Stamp (50 mm x 20 mm)");
        assert stamp1.getPrice() == 2300;
        assert stamp1.getText().equals("Stamp1");
        assert stamp1.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp1.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp1.getDeliveryType() == DeliveryMethod.SHIP;

        assert stamp2.getWidth() == 70;
        assert stamp2.getHeight() == 40;
        assert stamp2.getStampName().equals("Stamp (70 mm x 40 mm)");
        assert stamp2.getPrice() == 2600;
        assert stamp2.getText().equals("Stamp2");
        assert stamp2.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp2.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp2.getDeliveryType() == DeliveryMethod.SHIP;
    }

    private static void testBusinessCard() {
        BusinessCard businessCard0 = new BusinessCard(PaperType.LAID, SideType.SINGLE, Orientation.PORTRAIT, BusinessCardColor.IVORY);

        TextAperture textAperture0 = new TextAperture(-1, 30, 1, 1, "text");
        TextAperture textAperture1 = new TextAperture(30, -1, 1, 1, "text");
        TextAperture textAperture2 = new TextAperture(90, 50, 1, 1, "text");

        TextAperture textAperture3 = new TextAperture(0, 0, 90, 50, "text");
        TextAperture textAperture4 = new TextAperture(89, 49, 1, 1, "text");

        businessCard0.addAperture(textAperture0);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addAperture(textAperture1);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addAperture(textAperture2);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addAperture(textAperture3);
        assert businessCard0.getTextApertureCount() == 1;
        assert businessCard0.getPrice() == 125;

        businessCard0.addAperture(textAperture4);
        assert businessCard0.getTextApertureCount() == 2;
        assert businessCard0.getPrice() == 130;
    }

    private static void testCart() {
        Cart cart = new Cart();
        Stamp stamp0 = new Stamp(StampSize.SMALL, StampColor.RED, "Stamp0");
        BusinessCard businessCard0 = new BusinessCard(PaperType.LAID, SideType.SINGLE, Orientation.PORTRAIT, BusinessCardColor.IVORY);
        Banner banner0 = new Banner(BannerType.GLOSS, BannerSize.BANNER_3000X1000, Orientation.PORTRAIT, new Color(0xff, 0xff, 0xff));

        cart.addProduct(stamp0);

        assert cart.getTotalPrice() == 2300;
        assert cart.getProductsCount() == 1;
        assert !cart.removeProduct(businessCard0);

        cart.addProduct(businessCard0);

        assert cart.getTotalPrice() == 2420;
        assert cart.getProductsCount() == 2;
        assert !cart.removeProduct(banner0);

        var products = cart.getTotalProducts();
        assert products.size() == 2;
        int totalPrice = 0;

        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        assert totalPrice == 2420;

        assert cart.removeProduct(stamp0);
        assert cart.getProductsCount() == 1;
        assert cart.getTotalPrice() == 120;

        assert cart.removeProduct(businessCard0);
        assert cart.getProductsCount() == 0;
        assert cart.getTotalPrice() == 0;

        assert products.size() == 0;
    }

    private static void testAddApertures() {
        String apertureContent = "text";
        BusinessCard businessCard = new BusinessCard(PaperType.LAID, SideType.SINGLE, Orientation.LANDSCAPE, BusinessCardColor.IVORY);
        ArrayList<Aperture> invalidTextApertures = new ArrayList<>();
        ArrayList<Aperture> validTextApertures = new ArrayList<>();

        invalidTextApertures.add(new TextAperture(-1, 0 ,1, 1, apertureContent));
        invalidTextApertures.add(new TextAperture(0, -1 ,1, 1, apertureContent));
        invalidTextApertures.add(new TextAperture(90, 0 ,1, 1, apertureContent));
        invalidTextApertures.add(new TextAperture(0, 50 ,1, 1, apertureContent));
        invalidTextApertures.add(new TextAperture(-10, 0 ,10, 10, apertureContent));
        invalidTextApertures.add(new TextAperture(0, -10 ,10, 10, apertureContent));
        invalidTextApertures.add(new TextAperture(90, 0 ,10, 10, apertureContent));
        invalidTextApertures.add(new TextAperture(0, 50 ,10, 10, apertureContent));

        for (Aperture Aperture : invalidTextApertures) {
            assert !businessCard.addAperture(Aperture);
            assert businessCard.getPrice() == 120;
        }

        validTextApertures.add(new TextAperture(0, 0, 1, 1, apertureContent));
        validTextApertures.add(new TextAperture(89, 49, 1, 1, apertureContent));
        validTextApertures.add(new TextAperture(-9, -9, 10, 10, apertureContent));
        validTextApertures.add(new TextAperture(89, 49, 10, 10, apertureContent));
        validTextApertures.add(new TextAperture(0, 0, 90, 50, apertureContent));
        validTextApertures.add(new ImageAperture(0, 0, 90, 50, apertureContent));

        for (Aperture Aperture : validTextApertures) {
            assert businessCard.addAperture(Aperture);
        }

        assert businessCard.getImageApertureCount() == 6;
    }
}
