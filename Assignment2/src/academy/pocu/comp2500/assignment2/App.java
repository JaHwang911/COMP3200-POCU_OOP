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
        registry.registerLandscapeBannerTextApertureAdder("Banner", "addAperture");
        registry.registerLandscapeBannerImageApertureAdder("Banner", "addAperture");
        registry.registerPortraitBannerTextApertureAdder("Banner", "addAperture");
        registry.registerPortraitBannerImageApertureAdder("Banner", "addAperture");
        registry.registerGlossBannerTextApertureAdder("Banner", "addAperture");
        registry.registerGlossBannerImageApertureAdder("Banner", "addAperture");
        registry.registerScrimBannerTextApertureAdder("Banner", "addAperture");
        registry.registerScrimBannerImageApertureAdder("Banner", "addAperture");
        registry.registerMeshBannerTextApertureAdder("Banner", "addAperture");
        registry.registerMeshBannerImageApertureAdder("Banner", "addAperture");
        registry.registerLandscapeBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerLandscapeBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerPortraitBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerPortraitBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerIvoryBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerIvoryBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerGrayBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerGrayBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerWhiteBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerWhiteBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerLaidBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerLaidBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerLinenBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerLinenBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerSmoothBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerSmoothBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerSingleSidedBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerSingleSidedBusinessCardImageApertureAdder("BusinessCard", "addAperture");
        registry.registerDoubleSidedBusinessCardTextApertureAdder("BusinessCard", "addAperture");
        registry.registerDoubleSidedBusinessCardImageApertureAdder("BusinessCard", "addAperture");
    }
}
