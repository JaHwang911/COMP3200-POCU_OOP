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
        registry.registerLandscapeBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerLandscapeBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerPortraitBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerPortraitBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerIvoryBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerIvoryBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerGrayBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerGrayBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerWhiteBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerWhiteBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerLaidBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerLaidBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerLinenBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerLinenBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerSmoothBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerSmoothBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerSingleSidedBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerSingleSidedBusinessCardImageApertureAdder("BusinessCard", "addImageApertureToFront");
        registry.registerDoubleSidedBusinessCardTextApertureAdder("BusinessCard", "addTextApertureToFront");
        registry.registerDoubleSidedBusinessCardImageApertureAdder("BusinessCard", "addTextApertureToFront");
    }
}
