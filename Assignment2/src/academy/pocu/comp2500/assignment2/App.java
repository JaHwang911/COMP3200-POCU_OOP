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
        registry.registerLandscapeBannerTextApertureAdder("Banner", "addTextAperture");
        registry.registerLandscapeBannerImageApertureAdder("Banner", "addImageAperture");
        registry.registerPortraitBannerTextApertureAdder("Banner", "addTextAperture");
        registry.registerPortraitBannerImageApertureAdder("Banner", "addImageAperture");
        registry.registerGlossBannerTextApertureAdder("Banner", "addTextAperture");
        registry.registerGlossBannerImageApertureAdder("Banner", "addImageAperture");
        registry.registerScrimBannerTextApertureAdder("Banner", "addTextAperture");
        registry.registerScrimBannerImageApertureAdder("Banner", "addImageAperture");
        registry.registerMeshBannerTextApertureAdder("Banner", "addTextAperture");
        registry.registerMeshBannerImageApertureAdder("Banner", "addImageAperture");
        registry.registerLandscapeBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerLandscapeBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerPortraitBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerPortraitBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerIvoryBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerIvoryBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerGrayBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerGrayBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerWhiteBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerWhiteBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerLaidBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerLaidBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerLinenBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerLinenBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerSmoothBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerSmoothBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerSingleSidedBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerSingleSidedBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
        registry.registerDoubleSidedBusinessCardTextApertureAdder("BusinessCard", "addTextAperture");
        registry.registerDoubleSidedBusinessCardImageApertureAdder("BusinessCard", "addImageAperture");
    }
}
