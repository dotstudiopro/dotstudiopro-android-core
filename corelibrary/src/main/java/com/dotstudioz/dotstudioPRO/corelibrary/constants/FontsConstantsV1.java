package com.dotstudioz.dotstudioPRO.corelibrary.constants;

import android.content.Context;
import android.graphics.Typeface;

import java.io.Serializable;

/**
 * Created by mohsin on 15-10-2016.
 */
public class FontsConstantsV1 implements Serializable {
    private static FontsConstantsV1 ourInstance = new FontsConstantsV1();
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
    public  Typeface opensansRegularFont;


    public void initialize(Context ctx)
    {
        opensansRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_regular.ttf");
    }


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

    public static Typeface getTfRegular() {
        return tfRegular;
    }

    public static void setTfRegular(Typeface tfRegular) {
        FontsConstantsV1.tfRegular = tfRegular;
    }

    public static Typeface getTfBold() {
        return tfBold;
    }

    public static void setTfBold(Typeface tfBold) {
        FontsConstantsV1.tfBold = tfBold;
    }

    public static Typeface getTfSemiBold() {
        return tfSemiBold;
    }

    public static void setTfSemiBold(Typeface tfSemiBold) {
        FontsConstantsV1.tfSemiBold = tfSemiBold;
    }

    public static Typeface getTfMedium() {
        return tfMedium;
    }

    public static void setTfMedium(Typeface tfMedium) {
        FontsConstantsV1.tfMedium = tfMedium;
    }

    public static Typeface getAlwaysForeverBold() {
        return alwaysForeverBold;
    }

    public static void setAlwaysForeverBold(Typeface alwaysForeverBold) {
        FontsConstantsV1.alwaysForeverBold = alwaysForeverBold;
    }

    public static Typeface getVerdana() {
        return verdana;
    }

    public static void setVerdana(Typeface verdana) {
        FontsConstantsV1.verdana = verdana;
    }

    public static Typeface getVerdanaRegular() {
        return verdanaRegular;
    }

    public static void setVerdanaRegular(Typeface verdanaRegular) {
        FontsConstantsV1.verdanaRegular = verdanaRegular;
    }

    public static Typeface getGothamBook() {
        return gothamBook;
    }

    public static void setGothamBook(Typeface gothamBook) {
        FontsConstantsV1.gothamBook = gothamBook;
    }

    public static Typeface getGothamMedium() {
        return gothamMedium;
    }

    public static void setGothamMedium(Typeface gothamMedium) {
        FontsConstantsV1.gothamMedium = gothamMedium;
    }

    public static Typeface getGothamBold() {
        return gothamBold;
    }

    public static void setGothamBold(Typeface gothamBold) {
        FontsConstantsV1.gothamBold = gothamBold;
    }

    public static Typeface getSourcesansproBold() {
        return sourcesansproBold;
    }

    public static void setSourcesansproBold(Typeface sourcesansproBold) {
        FontsConstantsV1.sourcesansproBold = sourcesansproBold;
    }

    public static Typeface getSourcesansproRegular() {
        return sourcesansproRegular;
    }

    public static void setSourcesansproRegular(Typeface sourcesansproRegular) {
        FontsConstantsV1.sourcesansproRegular = sourcesansproRegular;
    }

    public static Typeface getKlavikaRegular() {
        return klavikaRegular;
    }

    public static void setKlavikaRegular(Typeface klavikaRegular) {
        FontsConstantsV1.klavikaRegular = klavikaRegular;
    }

    public static Typeface getUniversltstdUltracn() {
        return universltstdUltracn;
    }

    public static void setUniversltstdUltracn(Typeface universltstdUltracn) {
        FontsConstantsV1.universltstdUltracn = universltstdUltracn;
    }

    public static Typeface getDidactgothicRegular() {
        return didactgothicRegular;
    }

    public static void setDidactgothicRegular(Typeface didactgothicRegular) {
        FontsConstantsV1.didactgothicRegular = didactgothicRegular;
    }

    public static Typeface getLatoRegular() {
        return latoRegular;
    }

    public static void setLatoRegular(Typeface latoRegular) {
        FontsConstantsV1.latoRegular = latoRegular;
    }

    public static Typeface getLatoBold() {
        return latoBold;
    }

    public static void setLatoBold(Typeface latoBold) {
        FontsConstantsV1.latoBold = latoBold;
    }

    public static Typeface getSfProRegular() {
        return sfProRegular;
    }

    public static void setSfProRegular(Typeface sfProRegular) {
        FontsConstantsV1.sfProRegular = sfProRegular;
    }

    public static Typeface getSfProDisplayRegular() {
        return sfProDisplayRegular;
    }

    public static void setSfProDisplayRegular(Typeface sfProDisplayRegular) {
        FontsConstantsV1.sfProDisplayRegular = sfProDisplayRegular;
    }

    public static Typeface getSfProBold() {
        return sfProBold;
    }

    public static void setSfProBold(Typeface sfProBold) {
        FontsConstantsV1.sfProBold = sfProBold;
    }

    public static Typeface getFuturaMediumBt() {
        return futuraMediumBt;
    }

    public static void setFuturaMediumBt(Typeface futuraMediumBt) {
        FontsConstantsV1.futuraMediumBt = futuraMediumBt;
    }

    public static Typeface getFuturaMediumCondensedBt() {
        return futuraMediumCondensedBt;
    }

    public static void setFuturaMediumCondensedBt(Typeface futuraMediumCondensedBt) {
        FontsConstantsV1.futuraMediumCondensedBt = futuraMediumCondensedBt;
    }

    public static Typeface getFuturaCondensedExtraBold() {
        return futuraCondensedExtraBold;
    }

    public static void setFuturaCondensedExtraBold(Typeface futuraCondensedExtraBold) {
        FontsConstantsV1.futuraCondensedExtraBold = futuraCondensedExtraBold;
    }

    public static Typeface getFuturaBookFont() {
        return futuraBookFont;
    }

    public static void setFuturaBookFont(Typeface futuraBookFont) {
        FontsConstantsV1.futuraBookFont = futuraBookFont;
    }

    public static Typeface getFuturaLightFont() {
        return futuraLightFont;
    }

    public static void setFuturaLightFont(Typeface futuraLightFont) {
        FontsConstantsV1.futuraLightFont = futuraLightFont;
    }

    public static Typeface getAvenirMediumFont() {
        return avenirMediumFont;
    }

    public static void setAvenirMediumFont(Typeface avenirMediumFont) {
        FontsConstantsV1.avenirMediumFont = avenirMediumFont;
    }

    public static Typeface getAvenirNextRegularFont() {
        return avenirNextRegularFont;
    }

    public static void setAvenirNextRegularFont(Typeface avenirNextRegularFont) {
        FontsConstantsV1.avenirNextRegularFont = avenirNextRegularFont;
    }

    public static Typeface getMontserratRegularFont() {
        return montserratRegularFont;
    }

    public static void setMontserratRegularFont(Typeface montserratRegularFont) {
        FontsConstantsV1.montserratRegularFont = montserratRegularFont;
    }

    public static Typeface getMontserratBoldFont() {
        return montserratBoldFont;
    }

    public static void setMontserratBoldFont(Typeface montserratBoldFont) {
        FontsConstantsV1.montserratBoldFont = montserratBoldFont;
    }

    public static Typeface getMontserratSemiBoldFont() {
        return montserratSemiBoldFont;
    }

    public static void setMontserratSemiBoldFont(Typeface montserratSemiBoldFont) {
        FontsConstantsV1.montserratSemiBoldFont = montserratSemiBoldFont;
    }

    public static Typeface getMontserratMediumFont() {
        return montserratMediumFont;
    }

    public static void setMontserratMediumFont(Typeface montserratMediumFont) {
        FontsConstantsV1.montserratMediumFont = montserratMediumFont;
    }



    public static Typeface getOpensansSemiBoldFont() {
        return opensansSemiBoldFont;
    }

    public static void setOpensansSemiBoldFont(Typeface opensansSemiBoldFont) {
        FontsConstantsV1.opensansSemiBoldFont = opensansSemiBoldFont;
    }

    public static Typeface getOpensansBoldFont() {
        return opensansBoldFont;
    }

    public static void setOpensansBoldFont(Typeface opensansBoldFont) {
        FontsConstantsV1.opensansBoldFont = opensansBoldFont;
    }

    public static Typeface getOpensansLightFont() {
        return opensansLightFont;
    }

    public static void setOpensansLightFont(Typeface opensansLightFont) {
        FontsConstantsV1.opensansLightFont = opensansLightFont;
    }

    public static Typeface getRobotBlackFont() {
        return robotBlackFont;
    }

    public static void setRobotBlackFont(Typeface robotBlackFont) {
        FontsConstantsV1.robotBlackFont = robotBlackFont;
    }

    public static Typeface getProximaNovaExtraCondensedBlackFont() {
        return proximaNovaExtraCondensedBlackFont;
    }

    public static void setProximaNovaExtraCondensedBlackFont(Typeface proximaNovaExtraCondensedBlackFont) {
        FontsConstantsV1.proximaNovaExtraCondensedBlackFont = proximaNovaExtraCondensedBlackFont;
    }

    public static Typeface getRobotMediumFont() {
        return robotMediumFont;
    }

    public static void setRobotMediumFont(Typeface robotMediumFont) {
        FontsConstantsV1.robotMediumFont = robotMediumFont;
    }

    public static Typeface getRobotRegularFont() {
        return robotRegularFont;
    }

    public static void setRobotRegularFont(Typeface robotRegularFont) {
        FontsConstantsV1.robotRegularFont = robotRegularFont;
    }

    public static Typeface getRobotLightFont() {
        return robotLightFont;
    }

    public static void setRobotLightFont(Typeface robotLightFont) {
        FontsConstantsV1.robotLightFont = robotLightFont;
    }

    public static Typeface getLatoRegularFont() {
        return latoRegularFont;
    }

    public static void setLatoRegularFont(Typeface latoRegularFont) {
        FontsConstantsV1.latoRegularFont = latoRegularFont;
    }

    public static Typeface getTfSitkaHeadingRegular() {
        return tfSitkaHeadingRegular;
    }

    public static void setTfSitkaHeadingRegular(Typeface tfSitkaHeadingRegular) {
        FontsConstantsV1.tfSitkaHeadingRegular = tfSitkaHeadingRegular;
    }

    public static Typeface getTfSitkaHeadingBold() {
        return tfSitkaHeadingBold;
    }

    public static void setTfSitkaHeadingBold(Typeface tfSitkaHeadingBold) {
        FontsConstantsV1.tfSitkaHeadingBold = tfSitkaHeadingBold;
    }

    public static Typeface getTfSitkaDisplayRegular() {
        return tfSitkaDisplayRegular;
    }

    public static void setTfSitkaDisplayRegular(Typeface tfSitkaDisplayRegular) {
        FontsConstantsV1.tfSitkaDisplayRegular = tfSitkaDisplayRegular;
    }

    public static Typeface getTfSitkaDisplayBold() {
        return tfSitkaDisplayBold;
    }

    public static void setTfSitkaDisplayBold(Typeface tfSitkaDisplayBold) {
        FontsConstantsV1.tfSitkaDisplayBold = tfSitkaDisplayBold;
    }

    public static Typeface getSitka_headingFont() {
        return sitka_headingFont;
    }

    public static void setSitka_headingFont(Typeface sitka_headingFont) {
        FontsConstantsV1.sitka_headingFont = sitka_headingFont;
    }

    public static Typeface getSitka_displayFont() {
        return sitka_displayFont;
    }

    public static void setSitka_displayFont(Typeface sitka_displayFont) {
        FontsConstantsV1.sitka_displayFont = sitka_displayFont;
    }

    public static Typeface getKhandBoldFont() {
        return khandBoldFont;
    }

    public static void setKhandBoldFont(Typeface khandBoldFont) {
        FontsConstantsV1.khandBoldFont = khandBoldFont;
    }

    public static Typeface getKhandLightFont() {
        return khandLightFont;
    }

    public static void setKhandLightFont(Typeface khandLightFont) {
        FontsConstantsV1.khandLightFont = khandLightFont;
    }

    public static Typeface getKhandMediumFont() {
        return khandMediumFont;
    }

    public static void setKhandMediumFont(Typeface khandMediumFont) {
        FontsConstantsV1.khandMediumFont = khandMediumFont;
    }

    public static Typeface getKhandRegularFont() {
        return khandRegularFont;
    }

    public static void setKhandRegularFont(Typeface khandRegularFont) {
        FontsConstantsV1.khandRegularFont = khandRegularFont;
    }

    public static Typeface getKhandSemiBoldFont() {
        return khandSemiBoldFont;
    }

    public static void setKhandSemiBoldFont(Typeface khandSemiBoldFont) {
        FontsConstantsV1.khandSemiBoldFont = khandSemiBoldFont;
    }

    public static Typeface getComfortaaBoldFont() {
        return comfortaaBoldFont;
    }

    public static void setComfortaaBoldFont(Typeface comfortaaBoldFont) {
        FontsConstantsV1.comfortaaBoldFont = comfortaaBoldFont;
    }

    public static Typeface getComfortaaRegularFont() {
        return comfortaaRegularFont;
    }

    public static void setComfortaaRegularFont(Typeface comfortaaRegularFont) {
        FontsConstantsV1.comfortaaRegularFont = comfortaaRegularFont;
    }

    public static Typeface getComfortaaLightFont() {
        return comfortaaLightFont;
    }

    public static void setComfortaaLightFont(Typeface comfortaaLightFont) {
        FontsConstantsV1.comfortaaLightFont = comfortaaLightFont;
    }

    public static Typeface getComfortaaThinFont() {
        return comfortaaThinFont;
    }

    public static void setComfortaaThinFont(Typeface comfortaaThinFont) {
        FontsConstantsV1.comfortaaThinFont = comfortaaThinFont;
    }

    public static Typeface getLilitaoneRegularFont() {
        return lilitaoneRegularFont;
    }

    public static void setLilitaoneRegularFont(Typeface lilitaoneRegularFont) {
        FontsConstantsV1.lilitaoneRegularFont = lilitaoneRegularFont;
    }

    public static Typeface getArimoRegularFont() {
        return arimoRegularFont;
    }

    public static void setArimoRegularFont(Typeface arimoRegularFont) {
        FontsConstantsV1.arimoRegularFont = arimoRegularFont;
    }

    public static Typeface getArimoBoldFont() {
        return arimoBoldFont;
    }

    public static void setArimoBoldFont(Typeface arimoBoldFont) {
        FontsConstantsV1.arimoBoldFont = arimoBoldFont;
    }

    public Typeface getOpensansRegularFont() {
        return opensansRegularFont;
    }

    public void setOpensansRegularFont(Typeface opensansRegularFont) {
        this.opensansRegularFont = opensansRegularFont;
    }
}
