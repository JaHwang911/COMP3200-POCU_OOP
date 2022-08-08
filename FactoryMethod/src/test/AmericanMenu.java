package test;

public class AmericanMenu extends Menu {
    @Override
    public Cup createCupOrNull(CupSize size) {
        switch (size) {
            case SMALL:
                return new Cup(432);
            case MEDIUM:
                return new Cup(624);
            case LARGE:
                return new Cup(856);
            default:
                assert (false) : "Unknown cup size";
                return null;
        }
    }
}
