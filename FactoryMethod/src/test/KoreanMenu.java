package test;

public class KoreanMenu extends Menu {

    @Override
    public Cup createCupOrNull(CupSize size) {
        switch (size) {
            case SMALL:
                return new Cup(256);
            case MEDIUM:
                return new Cup(500);
            case LARGE:
                return new Cup(700);
            default:
                assert (false) : "Unknown cup size";
                return null;
        }
    }
}
