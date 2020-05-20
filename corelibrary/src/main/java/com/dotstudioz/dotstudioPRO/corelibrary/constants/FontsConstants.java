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
    public static Typeface proximaNovaExtraCondensedBlackFont;
    public static Typeface proximaNovaAltBoldFont;
    public static Typeface proximaNovaAltLightFont;
    public static Typeface proximaNovaAltThinFont;
    public static Typeface proximaNovaBlackFont;
    public static Typeface proximaNovaBoldFont;
    public static Typeface proximaNovaRegularFont;
    public static Typeface proximaNovaThinFont;
    public static Typeface robotBlackFont;
    public static Typeface robotBoldFont;
    public static Typeface robotMediumFont;
    public static Typeface robotRegularFont;
    public static Typeface robotLightFont;
    public static Typeface robotoCondensedBoldFont;
    public static Typeface robotoCondensedItalicFont;
    public static Typeface robotoCondensedLightFont;
    public static Typeface robotoCondensedRegularFont;
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
    public static Typeface poppinsBlack;
    public static Typeface poppinsBlackItalic;
    public static Typeface poppinsBold;
    public static Typeface poppinsBoldItalic;
    public static Typeface poppinsExtraBold;
    public static Typeface poppinsExtraBoldItalic;
    public static Typeface poppinsExtraLight;
    public static Typeface poppinsExtraLightItalic;
    public static Typeface poppinsItalic;
    public static Typeface poppinsLight;
    public static Typeface poppinsLightItalic;
    public static Typeface poppinsMedium;
    public static Typeface poppinsMediumItalic;
    public static Typeface poppinsRegular;
    public static Typeface poppinsSemiBold;
    public static Typeface poppinsSemiBoldItalic;
    public static Typeface poppinsThin;
    public static Typeface poppinsThinItalic;




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
        proximaNovaExtraCondensedBlackFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_extra_condensed_black.otf");
        proximaNovaAltBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_alt_bold.otf");
        proximaNovaAltLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_alt_light.otf");
        proximaNovaAltThinFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_alt_thin.otf");
        proximaNovaBlackFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_black.otf");
        proximaNovaBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_bold.otf");
        proximaNovaRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_regular.otf");
        proximaNovaThinFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "proxima_nova_thin.otf");
        robotBlackFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_black.ttf");
        robotBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto-bold.ttf");
        robotMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_medium.ttf");
        latoRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "lato_regular.ttf");
        tfSitkaHeadingRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-heading-regular.ttc");
        tfSitkaHeadingBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-heading-bold.ttc");
        tfSitkaDisplayRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-display-regular.ttc");
        tfSitkaDisplayBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "sitka-display-bold.ttc");
        robotRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_regular.ttf");
        robotLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "roboto_light.ttf");
        robotoCondensedBoldFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "robotocondensed_bold.ttf");
        robotoCondensedItalicFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "robotocondensed_italic.ttf");
        robotoCondensedLightFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "robotocondensed_light.ttf");
        robotoCondensedRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "robotocondensed_regular.ttf");
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

        poppinsBlack = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-black.otf");
        poppinsBlackItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-blackitalic.otf");
        poppinsBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-bold.otf");
        poppinsBoldItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-bolditalic.otf");
        poppinsExtraBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-extrabold.otf");
        poppinsExtraBoldItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-extrabolditalic.otf");
        poppinsExtraLight = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-extralight.otf");
        poppinsExtraLightItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-extralightitalic.otf");
        poppinsItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-italic.otf");
        poppinsLight = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-light.otf");
        poppinsLightItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-lightitalic.otf");
        poppinsMedium = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-medium.otf");
        poppinsMediumItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-mediumitalic.otf");
        poppinsRegular = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-regular.otf");
        poppinsSemiBold = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-semibold.otf");
        poppinsSemiBoldItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-semibolditalic.otf");
        poppinsThin = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-thin.otf");
        poppinsThinItalic = Typeface.createFromAsset(ctx.getResources().getAssets(), "poppins-thinitalic.otf");
    }


    public enum FONTS_ENUM {
        TFREGULAR,
        TFBOLD,
        TFSEMIBOLD,
        TFMEDIUM,
        ALWAYSFOREVERBOLD,
        VERDANA,
        VERDANAREGULAR,
        GOTHAMBOOK,
        GOTHAMMEDIUM,
        GOTHAMBOLD,
        SOURCESANSPROBOLD,
        SOURCESANSPROREGULAR,
        KLAVIKAREGULAR,
        UNIVERSLTSTDULTRACN,
        DIDACTGOTHICREGULAR,
        LATOREGULAR,
        LATOBOLD,
        SFPROREGULAR,
        SFPRODISPLAYREGULAR,
        SFPROBOLD,
        FUTURAMEDIUMBT,
        FUTURAMEDIUMCONDENSEDBT,
        FUTURACONDENSEDEXTRABOLD,
        FUTURABOOKFONT,
        FUTURALIGHTFONT,
        AVENIRMEDIUMFONT,
        AVENIRNEXTREGULARFONT,
        MONTSERRATREGULARFONT,
        MONTSERRATBOLDFONT,
        MONTSERRATSEMIBOLDFONT,
        MONTSERRATMEDIUMFONT,
        OPENSANSREGULARFONT,
        OPENSANSSEMIBOLDFONT,
        OPENSANSBOLDFONT,
        OPENSANSLIGHTFONT,
        PROXIMANOVAEXTRACONDENSEDBLACKFONT,
        PROXIMANOVAALTBOLDFONT,
        PROXIMANOVAALTLIGHTFONT,
        PROXIMANOVAALTTHINFONT,
        PROXIMANOVABLACKFONT,
        PROXIMANOVABOLDFONT,
        PROXIMANOVAREGULARFONT,
        PROXIMANOVATHINFONT,
        ROBOTBLACKFONT,
        ROBOTBOLDFONT,
        ROBOTMEDIUMFONT,
        ROBOTREGULARFONT,
        ROBOTLIGHTFONT,
        LATOREGULARFONT,
        TFSITKAHEADINGREGULAR,
        TFSITKAHEADINGBOLD,
        TFSITKADISPLAYREGULAR,
        TFSITKADISPLAYBOLD,
        SITKA_HEADINGFONT,
        SITKA_DISPLAYFONT,
        KHANDBOLDFONT,
        KHANDLIGHTFONT,
        KHANDMEDIUMFONT,
        KHANDREGULARFONT,
        KHANDSEMIBOLDFONT,
        COMFORTAABOLDFONT,
        COMFORTAAREGULARFONT,
        COMFORTAALIGHTFONT,
        COMFORTAATHINFONT,
        LILITAONEREGULARFONT,
        ARIMOREGULARFONT,
        ARIMOBOLDFONT,
        POPPINSBLACK,
        POPPINSBLACKITALIC,
        POPPINSBOLD,
        POPPINSBOLDITALIC,
        POPPINSEXTRABOLD,
        POPPINSEXTRABOLDITALIC,
        POPPINSEXTRALIGHT,
        POPPINSEXTRALIGHTITALIC,
        POPPINSITALIC,
        POPPINSLIGHT,
        POPPINSLIGHTITALIC,
        POPPINSMEDIUM,
        POPPINSMEDIUMITALIC,
        POPPINSREGULAR,
        POPPINSSEMIBOLD,
        POPPINSSEMIBOLDITALIC,
        POPPINSTHIN,
        POPPINSTHINITALIC,
        ROBOTOCONDENSEDBOLDFONT,
        ROBOTOCONDENSEDITALICFONT,
        ROBOTOCONDENSEDLIGHTFONT,
        ROBOTOCONDENSEDREGULARFONT
    };
    public Context context;
    public Typeface getGetTypeFace(int fontsEnum) {
        FontsConstants.initializeInstance(context);
        if(fontsEnum == FONTS_ENUM.ALWAYSFOREVERBOLD.ordinal()) {
            return FontsConstants.alwaysForeverBold;
        } else if(fontsEnum == FONTS_ENUM.GOTHAMBOLD.ordinal()) {
            return FontsConstants.gothamBold;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVABOLDFONT.ordinal()) {
            return FontsConstants.proximaNovaBoldFont;
        } else if(fontsEnum == FONTS_ENUM.ARIMOBOLDFONT.ordinal()) {
            return FontsConstants.arimoBoldFont;
        } else if(fontsEnum == FONTS_ENUM.ARIMOREGULARFONT.ordinal()) {
            return FontsConstants.arimoRegularFont;
        } else if(fontsEnum == FONTS_ENUM.AVENIRMEDIUMFONT.ordinal()) {
            return FontsConstants.avenirMediumFont;
        } else if(fontsEnum == FONTS_ENUM.AVENIRNEXTREGULARFONT.ordinal()) {
            return FontsConstants.avenirNextRegularFont;
        } else if(fontsEnum == FONTS_ENUM.COMFORTAABOLDFONT.ordinal()) {
            return FontsConstants.comfortaaBoldFont;
        } else if(fontsEnum == FONTS_ENUM.COMFORTAALIGHTFONT.ordinal()) {
            return FontsConstants.comfortaaLightFont;
        } else if(fontsEnum == FONTS_ENUM.COMFORTAAREGULARFONT.ordinal()) {
            return FontsConstants.comfortaaRegularFont;
        } else if(fontsEnum == FONTS_ENUM.COMFORTAATHINFONT.ordinal()) {
            return FontsConstants.comfortaaThinFont;
        } else if(fontsEnum == FONTS_ENUM.DIDACTGOTHICREGULAR.ordinal()) {
            return FontsConstants.didactgothicRegular;
        } else if(fontsEnum == FONTS_ENUM.FUTURABOOKFONT.ordinal()) {
            return FontsConstants.futuraBookFont;
        } else if(fontsEnum == FONTS_ENUM.FUTURACONDENSEDEXTRABOLD.ordinal()) {
            return FontsConstants.futuraCondensedExtraBold;
        } else if(fontsEnum == FONTS_ENUM.FUTURALIGHTFONT.ordinal()) {
            return FontsConstants.futuraLightFont;
        } else if(fontsEnum == FONTS_ENUM.FUTURAMEDIUMBT.ordinal()) {
            return FontsConstants.futuraMediumBt;
        } else if(fontsEnum == FONTS_ENUM.FUTURAMEDIUMCONDENSEDBT.ordinal()) {
            return FontsConstants.futuraMediumCondensedBt;
        } else if(fontsEnum == FONTS_ENUM.GOTHAMBOOK.ordinal()) {
            return FontsConstants.gothamBook;
        } else if(fontsEnum == FONTS_ENUM.GOTHAMMEDIUM.ordinal()) {
            return FontsConstants.gothamMedium;
        } else if(fontsEnum == FONTS_ENUM.KHANDBOLDFONT.ordinal()) {
            return FontsConstants.khandBoldFont;
        } else if(fontsEnum == FONTS_ENUM.KHANDLIGHTFONT.ordinal()) {
            return FontsConstants.khandLightFont;
        } else if(fontsEnum == FONTS_ENUM.KHANDMEDIUMFONT.ordinal()) {
            return FontsConstants.khandMediumFont;
        } else if(fontsEnum == FONTS_ENUM.KHANDREGULARFONT.ordinal()) {
            return FontsConstants.khandRegularFont;
        } else if(fontsEnum == FONTS_ENUM.KHANDSEMIBOLDFONT.ordinal()) {
            return FontsConstants.khandSemiBoldFont;
        } else if(fontsEnum == FONTS_ENUM.KLAVIKAREGULAR.ordinal()) {
            return FontsConstants.klavikaRegular;
        } else if(fontsEnum == FONTS_ENUM.LATOBOLD.ordinal()) {
            return FontsConstants.latoBold;
        } else if(fontsEnum == FONTS_ENUM.LATOREGULAR.ordinal()) {
            return FontsConstants.latoRegular;
        } else if(fontsEnum == FONTS_ENUM.LATOREGULARFONT.ordinal()) {
            return FontsConstants.latoRegularFont;
        } else if(fontsEnum == FONTS_ENUM.LILITAONEREGULARFONT.ordinal()) {
            return FontsConstants.lilitaoneRegularFont;
        } else if(fontsEnum == FONTS_ENUM.MONTSERRATBOLDFONT.ordinal()) {
            return FontsConstants.montserratBoldFont;
        } else if(fontsEnum == FONTS_ENUM.MONTSERRATMEDIUMFONT.ordinal()) {
            return FontsConstants.montserratMediumFont;
        } else if(fontsEnum == FONTS_ENUM.MONTSERRATREGULARFONT.ordinal()) {
            return FontsConstants.montserratRegularFont;
        } else if(fontsEnum == FONTS_ENUM.MONTSERRATSEMIBOLDFONT.ordinal()) {
            return FontsConstants.montserratSemiBoldFont;
        } else if(fontsEnum == FONTS_ENUM.OPENSANSBOLDFONT.ordinal()) {
            return FontsConstants.opensansBoldFont;
        } else if(fontsEnum == FONTS_ENUM.OPENSANSLIGHTFONT.ordinal()) {
            return FontsConstants.opensansLightFont;
        } else if(fontsEnum == FONTS_ENUM.OPENSANSREGULARFONT.ordinal()) {
            return FontsConstants.opensansRegularFont;
        } else if(fontsEnum == FONTS_ENUM.OPENSANSSEMIBOLDFONT.ordinal()) {
            return FontsConstants.opensansSemiBoldFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVAALTBOLDFONT.ordinal()) {
            return FontsConstants.proximaNovaAltBoldFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVAALTLIGHTFONT.ordinal()) {
            return FontsConstants.proximaNovaAltLightFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVAALTTHINFONT.ordinal()) {
            return FontsConstants.proximaNovaAltThinFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVABLACKFONT.ordinal()) {
            return FontsConstants.proximaNovaBlackFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVAEXTRACONDENSEDBLACKFONT.ordinal()) {
            return FontsConstants.proximaNovaExtraCondensedBlackFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVAREGULARFONT.ordinal()) {
            return FontsConstants.proximaNovaRegularFont;
        } else if(fontsEnum == FONTS_ENUM.PROXIMANOVATHINFONT.ordinal()) {
            return FontsConstants.proximaNovaThinFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTBLACKFONT.ordinal()) {
            return FontsConstants.robotBlackFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTBOLDFONT.ordinal()) {
            return FontsConstants.robotBoldFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTLIGHTFONT.ordinal()) {
            return FontsConstants.robotLightFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTMEDIUMFONT.ordinal()) {
            return FontsConstants.robotMediumFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTREGULARFONT.ordinal()) {
            return FontsConstants.robotRegularFont;
        } else if(fontsEnum == FONTS_ENUM.SFPROBOLD.ordinal()) {
            return FontsConstants.sfProBold;
        } else if(fontsEnum == FONTS_ENUM.SFPRODISPLAYREGULAR.ordinal()) {
            return FontsConstants.sfProDisplayRegular;
        } else if(fontsEnum == FONTS_ENUM.SFPROREGULAR.ordinal()) {
            return FontsConstants.sfProRegular;
        } else if(fontsEnum == FONTS_ENUM.SITKA_DISPLAYFONT.ordinal()) {
            return FontsConstants.sitka_displayFont;
        } else if(fontsEnum == FONTS_ENUM.SITKA_HEADINGFONT.ordinal()) {
            return FontsConstants.sitka_headingFont;
        } else if(fontsEnum == FONTS_ENUM.SOURCESANSPROBOLD.ordinal()) {
            return FontsConstants.sourcesansproBold;
        } else if(fontsEnum == FONTS_ENUM.SOURCESANSPROREGULAR.ordinal()) {
            return FontsConstants.sourcesansproRegular;
        } else if(fontsEnum == FONTS_ENUM.TFBOLD.ordinal()) {
            return FontsConstants.tfBold;
        } else if(fontsEnum == FONTS_ENUM.TFMEDIUM.ordinal()) {
            return FontsConstants.tfMedium;
        } else if(fontsEnum == FONTS_ENUM.TFREGULAR.ordinal()) {
            return FontsConstants.tfRegular;
        } else if(fontsEnum == FONTS_ENUM.TFSEMIBOLD.ordinal()) {
            return FontsConstants.tfSemiBold;
        } else if(fontsEnum == FONTS_ENUM.TFSITKADISPLAYBOLD.ordinal()) {
            return FontsConstants.tfSitkaDisplayBold;
        } else if(fontsEnum == FONTS_ENUM.TFSITKADISPLAYREGULAR.ordinal()) {
            return FontsConstants.tfSitkaDisplayRegular;
        } else if(fontsEnum == FONTS_ENUM.TFSITKAHEADINGBOLD.ordinal()) {
            return FontsConstants.tfSitkaHeadingBold;
        } else if(fontsEnum == FONTS_ENUM.TFSITKAHEADINGREGULAR.ordinal()) {
            return FontsConstants.tfSitkaHeadingRegular;
        } else if(fontsEnum == FONTS_ENUM.UNIVERSLTSTDULTRACN.ordinal()) {
            return FontsConstants.universltstdUltracn;
        } else if(fontsEnum == FONTS_ENUM.VERDANA.ordinal()) {
            return FontsConstants.verdana;
        } else if(fontsEnum == FONTS_ENUM.VERDANAREGULAR.ordinal()) {
            return FontsConstants.verdanaRegular;
        } else if(fontsEnum == FONTS_ENUM.POPPINSBLACK.ordinal()) {
            return FontsConstants.poppinsBlack;
        } else if(fontsEnum == FONTS_ENUM.POPPINSBLACKITALIC.ordinal()) {
            return FontsConstants.poppinsBlackItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSBOLD.ordinal()) {
            return FontsConstants.poppinsBold;
        } else if(fontsEnum == FONTS_ENUM.POPPINSBOLDITALIC.ordinal()) {
            return FontsConstants.poppinsBoldItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSEXTRABOLD.ordinal()) {
            return FontsConstants.poppinsExtraBold;
        } else if(fontsEnum == FONTS_ENUM.POPPINSEXTRABOLDITALIC.ordinal()) {
            return FontsConstants.poppinsExtraBoldItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSEXTRALIGHT.ordinal()) {
            return FontsConstants.poppinsExtraLight;
        } else if(fontsEnum == FONTS_ENUM.POPPINSEXTRALIGHTITALIC.ordinal()) {
            return FontsConstants.poppinsExtraLightItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSITALIC.ordinal()) {
            return FontsConstants.poppinsLight;
        } else if(fontsEnum == FONTS_ENUM.POPPINSLIGHT.ordinal()) {
            return FontsConstants.poppinsLight;
        } else if(fontsEnum == FONTS_ENUM.POPPINSLIGHTITALIC.ordinal()) {
            return FontsConstants.poppinsLightItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSMEDIUM.ordinal()) {
            return FontsConstants.poppinsMedium;
        } else if(fontsEnum == FONTS_ENUM.POPPINSMEDIUMITALIC.ordinal()) {
            return FontsConstants.poppinsMediumItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSREGULAR.ordinal()) {
            return FontsConstants.poppinsRegular;
        } else if(fontsEnum == FONTS_ENUM.POPPINSSEMIBOLD.ordinal()) {
            return FontsConstants.poppinsSemiBold;
        } else if(fontsEnum == FONTS_ENUM.POPPINSSEMIBOLDITALIC.ordinal()) {
            return FontsConstants.poppinsSemiBoldItalic;
        } else if(fontsEnum == FONTS_ENUM.POPPINSTHIN.ordinal()) {
            return FontsConstants.poppinsThin;
        } else if(fontsEnum == FONTS_ENUM.POPPINSTHINITALIC.ordinal()) {
            return FontsConstants.poppinsThinItalic;
        } else if(fontsEnum == FONTS_ENUM.ROBOTOCONDENSEDBOLDFONT.ordinal()) {
            return FontsConstants.robotoCondensedBoldFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTOCONDENSEDITALICFONT.ordinal()) {
            return FontsConstants.robotoCondensedItalicFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTOCONDENSEDLIGHTFONT.ordinal()) {
            return FontsConstants.robotoCondensedLightFont;
        } else if(fontsEnum == FONTS_ENUM.ROBOTOCONDENSEDREGULARFONT.ordinal()) {
            return FontsConstants.robotoCondensedRegularFont;
        }

        return FontsConstants.verdanaRegular;
    };
    
    
    
    
    
    
    
    
}
