package academy.pocu.comp2500.assignment2.app;

import academy.pocu.comp2500.assignment2.*;
import academy.pocu.comp2500.assignment2.registry.Registry;

public class Program {
    public static void main(String[] args) {
        Registry registry = new Registry();
        App app = new App(registry);
        registry.validate();

        testStamp();

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
        assert stamp0.getRGB().equals("RGB(FF,0,0)");

        assert stamp0.getWidth() == 50;
        assert stamp0.getHeight() == 20;
        assert stamp1.getStampName().equals("Stamp (50 mm x 20 mm)");
        assert stamp1.getPrice() == 2300;
        assert stamp1.getText().equals("Stamp1");
        assert stamp1.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp1.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp1.getDeliveryType() == DeliveryMethod.SHIP;
        assert stamp1.getRGB().equals("RGB(0,80,0)");

        assert stamp0.getWidth() == 70;
        assert stamp0.getHeight() == 40;
        assert stamp2.getStampName().equals("Stamp (70 mm x 40 mm)");
        assert stamp2.getPrice() == 2600;
        assert stamp2.getText().equals("Stamp2");
        assert stamp2.getDeliveryType() == DeliveryMethod.PICKUP;
        stamp2.setDeliveryType(DeliveryMethod.SHIP);
        assert stamp2.getDeliveryType() == DeliveryMethod.SHIP;
        assert stamp0.getRGB().equals("RGB(0,0,FF)");
    }

    private static void testCalendar() {
        Calendar calendar0 = new Calendar(CalendarType.WALL, Orientation.PORTRAIT, new Color(0xFF, 0x00, 0x00));
        Calendar calendar1 = new Calendar(CalendarType.DESK, Orientation.PORTRAIT, new Color(0x00, 0x80, 0x00));
        Calendar calendar2 = new Calendar(CalendarType.MAGNET, Orientation.PORTRAIT, new Color(0x00, 0x80, 0xFF));
    }
}
