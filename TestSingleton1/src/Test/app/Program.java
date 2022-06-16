package Test.app;

import Test.Configuration;

public class Program {
    public static void main(String[] args) {
        Configuration config0 = Configuration.getInstance();

        assert config0.getX() == 50;
        assert config0.getY() == 50;
        assert config0.getWidth() == 600;
        assert config0.getHeight() == 400;

        config0.setX(100);
        config0.setY(100);
        
        Configuration config1 = Configuration.getInstance();

        assert config1.getX() == 100;
        assert config1.getY() == 100;
        assert config1.getWidth() == 600;
        assert config1.getHeight() == 400;
        assert config0 == config1;

        Configuration config3 = config0.getInstance();

        assert config3 == config0;

        System.out.println("Singleton test No prob");
    }
}