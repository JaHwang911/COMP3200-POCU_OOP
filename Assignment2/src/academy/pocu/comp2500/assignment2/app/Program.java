package academy.pocu.comp2500.assignment2.app;

import academy.pocu.comp2500.assignment2.*;
import academy.pocu.comp2500.assignment2.registry.Registry;

public class Program {
    public static void main(String[] args) {
        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        testStamp();
        testBusinessCard();

        System.out.println("No prob");
    }

    private static void testStamp() {
        Stamp stamp0 = new Stamp(StampSize.SMALL, StampColor.RED, "Stamp0");
        Stamp stamp1 = new Stamp(StampSize.MEDIUM, StampColor.BLUE, "Stamp1");
        Stamp stamp2 = new Stamp(StampSize.LARGE, StampColor.GREEN, "Stamp2");

        Color red = new Color(1289, -1, -323);
        Color green = new Color(0x0, 0x80, 0x0);
        Color blue = new Color(0, 0, 32423);

        assert stamp0.getWidth() == 40;
        assert stamp0.getHeight() == 30;
        assert stamp0.getStampName().equals("Stamp (40 mm x 30 mm)");
        assert stamp0.getPrice() == 2300;
        assert stamp0.getText().equals("Stamp0");
        assert stamp0.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp0.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp0.getDeliveryType() == DeliveryMethod.SHIP;
        assert stamp0.getColor().getColor() == red.getColor();

        assert stamp0.getWidth() == 50;
        assert stamp0.getHeight() == 20;
        assert stamp1.getStampName().equals("Stamp (50 mm x 20 mm)");
        assert stamp1.getPrice() == 2300;
        assert stamp1.getText().equals("Stamp1");
        assert stamp1.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp1.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp1.getDeliveryType() == DeliveryMethod.SHIP;
        assert stamp1.getColor().getColor() == blue.getColor();

        assert stamp0.getWidth() == 70;
        assert stamp0.getHeight() == 40;
        assert stamp2.getStampName().equals("Stamp (70 mm x 40 mm)");
        assert stamp2.getPrice() == 2600;
        assert stamp2.getText().equals("Stamp2");
        assert stamp2.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp2.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp2.getDeliveryType() == DeliveryMethod.SHIP;
        assert stamp2.getColor().getColor() == green.getColor();
    }

    private static void testBusinessCard() {
        BusinessCard businessCard0 = new BusinessCard(PaperType.LAID, SideType.DOUBLE, Orientation.PORTRAIT, BusinessCardColor.IVORY);
        TextAperture textAperture0 = new TextAperture(1, 1, "text");
        TextAperture textAperture1 = new TextAperture(90, 50, "text");

        businessCard0.addTextAperture(-1, 30, textAperture0);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addTextAperture(30, -1, textAperture0);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addTextAperture(90, 50, textAperture0);
        assert businessCard0.getTextApertureCount() == 0;
        assert businessCard0.getPrice() == 120;

        businessCard0.addTextAperture(0, 0, textAperture0);
        assert businessCard0.getTextApertureCount() == 1;
        assert businessCard0.getPrice() == 125;

        businessCard0.addTextAperture(0, 0, textAperture1);
        assert businessCard0.getTextApertureCount() == 2;
        assert businessCard0.getPrice() == 130;
    }

    private static void testCalendar() {
        Calendar calendar0 = new Calendar(CalendarType.WALL, Orientation.PORTRAIT);
        Calendar calendar1 = new Calendar(CalendarType.DESK, Orientation.PORTRAIT);
        Calendar calendar2 = new Calendar(CalendarType.MAGNET, Orientation.PORTRAIT);
    }
}
