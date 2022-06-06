package academy.pocu.comp2500.assignment2;

import academy.pocu.comp2500.assignment2.registry.Registry;

public class App {
    public App(Registry registry) {
        registry.registerRedStampCreator("Stamp");
        registry.registerBlueStampCreator("Stamp");
        registry.registerGreenStampCreator("Stamp");
        registry.registerWallCalendarCreator("Calendar");
        registry.registerMagnetCalendarCreator("Calendar");
        registry.registerDeskCalendarCreator("Calendar");
        registry.registerLandscapeBannerCreator("Banner");
        registry.registerPortraitBannerCreator("Banner");
        registry.registerGlossBannerCreator("Banner");
        registry.registerScrimBannerCreator("Banner");
        registry.registerMeshBannerCreator("Banner");
        registry.registerLandscapeBusinessCardCreator("BusinessCard");
        registry.registerPortraitBusinessCardCreator("BusinessCard");
        registry.registerIvoryBusinessCardCreator("BusinessCard");
        registry.registerGrayBusinessCardCreator("BusinessCard");
        registry.registerWhiteBusinessCardCreator("BusinessCard");
        registry.registerLaidBusinessCardCreator("BusinessCard");
        registry.registerLinenBusinessCardCreator("BusinessCard");
        registry.registerSmoothBusinessCardCreator("BusinessCard");
        registry.registerSingleSidedBusinessCardCreator("BusinessCard");
        registry.registerDoubleSidedBusinessCardCreator("BusinessCard");
        registry.registerCartCreator("Cart");
        registry.registerProductAdder("Cart", "addProduct");
        registry.registerProductRemover("Cart", "removeProduct");
        registry.registerTotalPriceGetter("Cart", "getTotalPrice");
        registry.registerLandscapeBannerTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLandscapeBannerImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerPortraitBannerTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerPortraitBannerImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerGlossBannerTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerGlossBannerImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerScrimBannerTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerScrimBannerImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerMeshBannerTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerMeshBannerImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLandscapeBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLandscapeBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerPortraitBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerPortraitBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerIvoryBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerIvoryBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerGrayBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerGrayBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerWhiteBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerWhiteBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLaidBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLaidBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLinenBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerLinenBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerSmoothBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerSmoothBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerSingleSidedBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerSingleSidedBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
        registry.registerDoubleSidedBusinessCardTextApertureAdder("CustomizingProduct", "addAperture");
        registry.registerDoubleSidedBusinessCardImageApertureAdder("CustomizingProduct", "addAperture");
    }
}
