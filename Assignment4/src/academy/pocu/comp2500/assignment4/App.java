package academy.pocu.comp2500.assignment4;

import academy.pocu.comp2500.assignment4.registry.Registry;

public class App {
    public App(Registry registry) {
        registry.registerDrawPixelCommandCreator("DrawPixelCommand");
        registry.registerIncreasePixelCommandCreator("IncreasePixelCommand");
        registry.registerDecreasePixelCommandCreator("DecreasePixelCommand");
        registry.registerFillHorizontalLineCommandCreator("FillHorizontalLineCommand");
        registry.registerFillVerticalLineCommandCreator("FillVerticalLineCommand");
        registry.registerToUppercaseCommandCreator("ToUpperCommand");
        registry.registerToLowercaseCommandCreator("ToLowerCommand");
        registry.registerClearCommandCreator("ClearCommand");
    }
}
