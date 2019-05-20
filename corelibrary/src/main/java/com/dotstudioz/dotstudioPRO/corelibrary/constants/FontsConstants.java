package com.dotstudioz.dotstudioPRO.corelibrary.constants;

import android.content.Context;
import android.graphics.Typeface;

import java.io.Serializable;

/**
 * Created by mohsin on 15-10-2016.
 */
public class FontsConstants implements Serializable {
    private static FontsConstants ourInstance = new FontsConstants();

    public static Typeface tfRegular;
    public static Typeface tfBold;
    public static Typeface tfSemiBold;
    public static Typeface tfMedium;
    public static Typeface alwaysForeverBold;
    public static Typeface verdana;
    public static Typeface verdanaRegular;
    public static Typeface gothamBook;
    public static Typeface gothamMedium;
    public static Typeface gothamBold;
    public static Typeface sourcesansproBold;
    public static Typeface sourcesansproRegular;
    public static Typeface klavikaRegular;
    public static Typeface universltstdUltracn;
    public static Typeface didactgothicRegular;
    public static Typeface latoRegular;
    public static Typeface latoBold;
    public static Typeface sfProRegular;
    public static Typeface sfProDisplayRegular;
    public static Typeface sfProBold;
    public static Typeface futuraMediumBt;
    public static Typeface futuraMediumCondensedBt;
    public static Typeface futuraCondensedExtraBold;
    public static Typeface futuraBookFont;
    public static Typeface futuraLightFont;
    public static Typeface avenirMediumFont;
    public static Typeface avenirNextRegularFont;
    public static Typeface montserratRegularFont;
    public static Typeface montserratBoldFont;
    public static Typeface montserratSemiBoldFont;
    public static Typeface montserratMediumFont;
    public static Typeface opensansRegularFont;
    public static Typeface opensansSemiBoldFont;
    public static Typeface opensansBoldFont;
    public static Typeface opensansLightFont;
    public static Typeface robotBlackFont;
    public static Typeface proximaNovaExtraCondensedBlackFont;
    public static Typeface robotMediumFont;
    public static Typeface robotRegularFont;
    public static Typeface robotLightFont;
    public static Typeface latoRegularFont;
    public static Typeface tfSitkaHeadingRegular;
    public static Typeface tfSitkaHeadingBold;
    public static Typeface tfSitkaDisplayRegular;
    public static Typeface tfSitkaDisplayBold;
    public static Typeface sitka_headingFont;
    public static Typeface sitka_displayFont;
    public static Typeface khandBoldFont;
    public static Typeface khandLightFont;
    public static Typeface khandMediumFont;
    public static Typeface khandRegularFont;
    public static Typeface khandSemiBoldFont;
    public static Typeface comfortaaBoldFont;
    public static Typeface comfortaaRegularFont;
    public static Typeface comfortaaLightFont;
    public static Typeface comfortaaThinFont;
    public static Typeface lilitaoneRegularFont;
    public static Typeface arimoRegularFont;
    public static Typeface arimoBoldFont;

    public static void initializeInstance(Context ctx) {


        tfRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-regular.ttf");
        tfBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-bold.ttf");
        tfSemiBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-semibold.ttf");
        tfMedium = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-medium.ttf");
        alwaysForeverBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "always-forever-bold.ttf");
        verdana = Typeface.createFromAsset(ctx.getResources().getAssets(), "verdana.ttf");
        verdanaRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "verdana_regular.ttf");
        gothamBook = Typeface.createFromAsset(ctx.getResources().getAssets(), "gotham-book.ttf");
        gothamMedium = Typeface.createFromAsset(ctx.getResources().getAssets(), "gotham-medium.ttf");
        gothamBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "gotham-bold.ttf");
        sourcesansproBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sourcesanspro-bold.ttf");
        sourcesansproRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sourcesanspro-regular.ttf");
        klavikaRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "klavika-regular.otf");
        universltstdUltracn = Typeface.createFromAsset(ctx.getResources().getAssets(), "universltstd-ultracn.otf");
        universltstdUltracn = Typeface.createFromAsset(ctx.getResources().getAssets(), "didactgothic-regular.ttf");
        latoRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "lato-regular.ttf");
        latoBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "lato-bold.ttf");
        sfProRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sanfranciscotext-regular.otf");
        sfProDisplayRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sanfranciscodisplay-regular.otf");
        sfProBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sanfranciscotext-bold.otf");
        futuraMediumBt = Typeface.createFromAsset(ctx.getResources().getAssets(), "futura-medium-bt.ttf");
        futuraMediumCondensedBt = Typeface.createFromAsset(ctx.getResources().getAssets(), "futura-medium-condensed-bt.ttf");
        futuraCondensedExtraBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "futura-condensed-extra-bold.otf");
        futuraBookFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "futura-book-font.ttf");
        futuraLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "futura-light-font.ttf");
        /*avenirMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "avenir-medium.ttf");*/
        avenirNextRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "avenirnext-regular.ttf");
        montserratRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-regular.ttf");
        montserratBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-bold.ttf");
        montserratSemiBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-semibold.ttf");
        montserratMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "montserrat-medium.ttf");
        opensansBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_bold.ttf");
        opensansRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_regular.ttf");
        opensansSemiBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_semibold.ttf");
        opensansLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_light.ttf");
        robotBlackFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_black.ttf");
        proximaNovaExtraCondensedBlackFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_extra_condensed_black.otf");
        robotMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_medium.ttf");
        latoRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "lato_regular.ttf");
        tfSitkaHeadingRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-heading-regular.ttc");
        tfSitkaHeadingBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-heading-bold.ttc");
        tfSitkaDisplayRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-display-regular.ttc");
        tfSitkaDisplayBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-display-bold.ttc");
        robotRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_regular.ttf");
        robotLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_light.ttf");
        sitka_headingFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-heading.ttc");
        sitka_displayFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-display.ttc");
        khandBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "khand-bold.ttf");
        khandLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "khand-light.ttf");
        khandMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "khand-medium.ttf");
        khandRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "khand-regular.ttf");
        khandSemiBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "khand-semibold.ttf");
        comfortaaBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "comfortaa-bold.ttf");
        comfortaaRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "comfortaa-regular.ttf");
        comfortaaLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "comfortaa-light.ttf");
        comfortaaThinFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "comfortaa-thin.ttf");
        lilitaoneRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "lilitaone-regular.ttf");
        arimoBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "arimo-bold.ttf");
        arimoRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "arimo-regular.ttf");
    }
}
