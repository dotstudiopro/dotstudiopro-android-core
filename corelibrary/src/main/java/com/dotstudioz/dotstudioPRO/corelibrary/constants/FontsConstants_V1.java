package com.dotstudioz.dotstudioPRO.corelibrary.constants;

import android.content.Context;
import android.graphics.Typeface;

import java.io.Serializable;

/**
 * Created by mohsin on 15-10-2016.
 */
public class FontsConstants_V1 implements Serializable {
    private  FontsConstants_V1 ourInstance = new FontsConstants_V1();
    public  Typeface tfRegular;
    public  Typeface tfBold;
    public  Typeface tfSemiBold;
    public  Typeface tfMedium;
    public  Typeface alwaysForeverBold;
    public  Typeface verdana;
    public  Typeface verdanaRegular;
    public  Typeface gothamBook;
    public  Typeface gothamMedium;
    public  Typeface gothamBold;
    public  Typeface sourcesansproBold;
    public  Typeface sourcesansproRegular;
    public  Typeface klavikaRegular;
    public  Typeface universltstdUltracn;
    public  Typeface didactgothicRegular;
    public  Typeface latoRegular;
    public  Typeface latoBold;
    public  Typeface sfProRegular;
    public  Typeface sfProDisplayRegular;
    public  Typeface sfProBold;
    public  Typeface futuraMediumBt;
    public  Typeface futuraMediumCondensedBt;
    public  Typeface futuraCondensedExtraBold;
    public  Typeface futuraBookFont;
    public  Typeface futuraLightFont;
    public  Typeface avenirMediumFont;
    public  Typeface avenirNextRegularFont;
    public  Typeface montserratRegularFont;
    public  Typeface montserratBoldFont;
    public  Typeface montserratSemiBoldFont;
    public  Typeface montserratMediumFont;
    public  Typeface opensansSemiBoldFont;
    public  Typeface opensansBoldFont;
    public  Typeface opensansLightFont;
    public  Typeface robotBlackFont;
    public  Typeface proximaNovaExtraCondensedBlackFont;
    public  Typeface robotMediumFont;
    public  Typeface robotRegularFont;
    public  Typeface robotLightFont;
    public  Typeface latoRegularFont;
    public  Typeface tfSitkaHeadingRegular;
    public  Typeface tfSitkaHeadingBold;
    public  Typeface tfSitkaDisplayRegular;
    public  Typeface tfSitkaDisplayBold;
    public  Typeface sitka_headingFont;
    public  Typeface sitka_displayFont;
    public  Typeface khandBoldFont;
    public  Typeface khandLightFont;
    public  Typeface khandMediumFont;
    public  Typeface khandRegularFont;
    public  Typeface khandSemiBoldFont;
    public  Typeface comfortaaBoldFont;
    public  Typeface comfortaaRegularFont;
    public  Typeface comfortaaLightFont;
    public  Typeface comfortaaThinFont;
    public  Typeface lilitaoneRegularFont;
    public  Typeface arimoRegularFont;
    public  Typeface arimoBoldFont;
    public  Typeface opensansRegularFont;


    public void initializeFontInstance(Context ctx)
    {
        opensansRegularFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "opensans_regular.ttf");
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


    /*public static void initializeInstance(Context ctx) {


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
        *//*avenirMediumFont = Typeface.createFromAsset(ctx.getResources().getAssets(), "avenir-medium.ttf");*//*
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
    }*/


    public FontsConstants_V1 getOurInstance() {
        return ourInstance;
    }

    public void setOurInstance(FontsConstants_V1 ourInstance) {
        this.ourInstance = ourInstance;
    }

    public Typeface getTfRegular() {
        return tfRegular;
    }

    public void setTfRegular(Typeface tfRegular) {
        this.tfRegular = tfRegular;
    }

    public Typeface getTfBold() {
        return tfBold;
    }

    public void setTfBold(Typeface tfBold) {
        this.tfBold = tfBold;
    }

    public Typeface getTfSemiBold() {
        return tfSemiBold;
    }

    public void setTfSemiBold(Typeface tfSemiBold) {
        this.tfSemiBold = tfSemiBold;
    }

    public Typeface getTfMedium() {
        return tfMedium;
    }

    public void setTfMedium(Typeface tfMedium) {
        this.tfMedium = tfMedium;
    }

    public Typeface getAlwaysForeverBold() {
        return alwaysForeverBold;
    }

    public void setAlwaysForeverBold(Typeface alwaysForeverBold) {
        this.alwaysForeverBold = alwaysForeverBold;
    }

    public Typeface getVerdana() {
        return verdana;
    }

    public void setVerdana(Typeface verdana) {
        this.verdana = verdana;
    }

    public Typeface getVerdanaRegular() {
        return verdanaRegular;
    }

    public void setVerdanaRegular(Typeface verdanaRegular) {
        this.verdanaRegular = verdanaRegular;
    }

    public Typeface getGothamBook() {
        return gothamBook;
    }

    public void setGothamBook(Typeface gothamBook) {
        this.gothamBook = gothamBook;
    }

    public Typeface getGothamMedium() {
        return gothamMedium;
    }

    public void setGothamMedium(Typeface gothamMedium) {
        this.gothamMedium = gothamMedium;
    }

    public Typeface getGothamBold() {
        return gothamBold;
    }

    public void setGothamBold(Typeface gothamBold) {
        this.gothamBold = gothamBold;
    }

    public Typeface getSourcesansproBold() {
        return sourcesansproBold;
    }

    public void setSourcesansproBold(Typeface sourcesansproBold) {
        this.sourcesansproBold = sourcesansproBold;
    }

    public Typeface getSourcesansproRegular() {
        return sourcesansproRegular;
    }

    public void setSourcesansproRegular(Typeface sourcesansproRegular) {
        this.sourcesansproRegular = sourcesansproRegular;
    }

    public Typeface getKlavikaRegular() {
        return klavikaRegular;
    }

    public void setKlavikaRegular(Typeface klavikaRegular) {
        this.klavikaRegular = klavikaRegular;
    }

    public Typeface getUniversltstdUltracn() {
        return universltstdUltracn;
    }

    public void setUniversltstdUltracn(Typeface universltstdUltracn) {
        this.universltstdUltracn = universltstdUltracn;
    }

    public Typeface getDidactgothicRegular() {
        return didactgothicRegular;
    }

    public void setDidactgothicRegular(Typeface didactgothicRegular) {
        this.didactgothicRegular = didactgothicRegular;
    }

    public Typeface getLatoRegular() {
        return latoRegular;
    }

    public void setLatoRegular(Typeface latoRegular) {
        this.latoRegular = latoRegular;
    }

    public Typeface getLatoBold() {
        return latoBold;
    }

    public void setLatoBold(Typeface latoBold) {
        this.latoBold = latoBold;
    }

    public Typeface getSfProRegular() {
        return sfProRegular;
    }

    public void setSfProRegular(Typeface sfProRegular) {
        this.sfProRegular = sfProRegular;
    }

    public Typeface getSfProDisplayRegular() {
        return sfProDisplayRegular;
    }

    public void setSfProDisplayRegular(Typeface sfProDisplayRegular) {
        this.sfProDisplayRegular = sfProDisplayRegular;
    }

    public Typeface getSfProBold() {
        return sfProBold;
    }

    public void setSfProBold(Typeface sfProBold) {
        this.sfProBold = sfProBold;
    }

    public Typeface getFuturaMediumBt() {
        return futuraMediumBt;
    }

    public void setFuturaMediumBt(Typeface futuraMediumBt) {
        this.futuraMediumBt = futuraMediumBt;
    }

    public Typeface getFuturaMediumCondensedBt() {
        return futuraMediumCondensedBt;
    }

    public void setFuturaMediumCondensedBt(Typeface futuraMediumCondensedBt) {
        this.futuraMediumCondensedBt = futuraMediumCondensedBt;
    }

    public Typeface getFuturaCondensedExtraBold() {
        return futuraCondensedExtraBold;
    }

    public void setFuturaCondensedExtraBold(Typeface futuraCondensedExtraBold) {
        this.futuraCondensedExtraBold = futuraCondensedExtraBold;
    }

    public Typeface getFuturaBookFont() {
        return futuraBookFont;
    }

    public void setFuturaBookFont(Typeface futuraBookFont) {
        this.futuraBookFont = futuraBookFont;
    }

    public Typeface getFuturaLightFont() {
        return futuraLightFont;
    }

    public void setFuturaLightFont(Typeface futuraLightFont) {
        this.futuraLightFont = futuraLightFont;
    }

    public Typeface getAvenirMediumFont() {
        return avenirMediumFont;
    }

    public void setAvenirMediumFont(Typeface avenirMediumFont) {
        this.avenirMediumFont = avenirMediumFont;
    }

    public Typeface getAvenirNextRegularFont() {
        return avenirNextRegularFont;
    }

    public void setAvenirNextRegularFont(Typeface avenirNextRegularFont) {
        this.avenirNextRegularFont = avenirNextRegularFont;
    }

    public Typeface getMontserratRegularFont() {
        return montserratRegularFont;
    }

    public void setMontserratRegularFont(Typeface montserratRegularFont) {
        this.montserratRegularFont = montserratRegularFont;
    }

    public Typeface getMontserratBoldFont() {
        return montserratBoldFont;
    }

    public void setMontserratBoldFont(Typeface montserratBoldFont) {
        this.montserratBoldFont = montserratBoldFont;
    }

    public Typeface getMontserratSemiBoldFont() {
        return montserratSemiBoldFont;
    }

    public void setMontserratSemiBoldFont(Typeface montserratSemiBoldFont) {
        this.montserratSemiBoldFont = montserratSemiBoldFont;
    }

    public Typeface getMontserratMediumFont() {
        return montserratMediumFont;
    }

    public void setMontserratMediumFont(Typeface montserratMediumFont) {
        this.montserratMediumFont = montserratMediumFont;
    }

    public Typeface getOpensansSemiBoldFont() {
        return opensansSemiBoldFont;
    }

    public void setOpensansSemiBoldFont(Typeface opensansSemiBoldFont) {
        this.opensansSemiBoldFont = opensansSemiBoldFont;
    }

    public Typeface getOpensansBoldFont() {
        return opensansBoldFont;
    }

    public void setOpensansBoldFont(Typeface opensansBoldFont) {
        this.opensansBoldFont = opensansBoldFont;
    }

    public Typeface getOpensansLightFont() {
        return opensansLightFont;
    }

    public void setOpensansLightFont(Typeface opensansLightFont) {
        this.opensansLightFont = opensansLightFont;
    }

    public Typeface getRobotBlackFont() {
        return robotBlackFont;
    }

    public void setRobotBlackFont(Typeface robotBlackFont) {
        this.robotBlackFont = robotBlackFont;
    }

    public Typeface getProximaNovaExtraCondensedBlackFont() {
        return proximaNovaExtraCondensedBlackFont;
    }

    public void setProximaNovaExtraCondensedBlackFont(Typeface proximaNovaExtraCondensedBlackFont) {
        this.proximaNovaExtraCondensedBlackFont = proximaNovaExtraCondensedBlackFont;
    }

    public Typeface getRobotMediumFont() {
        return robotMediumFont;
    }

    public void setRobotMediumFont(Typeface robotMediumFont) {
        this.robotMediumFont = robotMediumFont;
    }

    public Typeface getRobotRegularFont() {
        return robotRegularFont;
    }

    public void setRobotRegularFont(Typeface robotRegularFont) {
        this.robotRegularFont = robotRegularFont;
    }

    public Typeface getRobotLightFont() {
        return robotLightFont;
    }

    public void setRobotLightFont(Typeface robotLightFont) {
        this.robotLightFont = robotLightFont;
    }

    public Typeface getLatoRegularFont() {
        return latoRegularFont;
    }

    public void setLatoRegularFont(Typeface latoRegularFont) {
        this.latoRegularFont = latoRegularFont;
    }

    public Typeface getTfSitkaHeadingRegular() {
        return tfSitkaHeadingRegular;
    }

    public void setTfSitkaHeadingRegular(Typeface tfSitkaHeadingRegular) {
        this.tfSitkaHeadingRegular = tfSitkaHeadingRegular;
    }

    public Typeface getTfSitkaHeadingBold() {
        return tfSitkaHeadingBold;
    }

    public void setTfSitkaHeadingBold(Typeface tfSitkaHeadingBold) {
        this.tfSitkaHeadingBold = tfSitkaHeadingBold;
    }

    public Typeface getTfSitkaDisplayRegular() {
        return tfSitkaDisplayRegular;
    }

    public void setTfSitkaDisplayRegular(Typeface tfSitkaDisplayRegular) {
        this.tfSitkaDisplayRegular = tfSitkaDisplayRegular;
    }

    public Typeface getTfSitkaDisplayBold() {
        return tfSitkaDisplayBold;
    }

    public void setTfSitkaDisplayBold(Typeface tfSitkaDisplayBold) {
        this.tfSitkaDisplayBold = tfSitkaDisplayBold;
    }

    public Typeface getSitka_headingFont() {
        return sitka_headingFont;
    }

    public void setSitka_headingFont(Typeface sitka_headingFont) {
        this.sitka_headingFont = sitka_headingFont;
    }

    public Typeface getSitka_displayFont() {
        return sitka_displayFont;
    }

    public void setSitka_displayFont(Typeface sitka_displayFont) {
        this.sitka_displayFont = sitka_displayFont;
    }

    public Typeface getKhandBoldFont() {
        return khandBoldFont;
    }

    public void setKhandBoldFont(Typeface khandBoldFont) {
        this.khandBoldFont = khandBoldFont;
    }

    public Typeface getKhandLightFont() {
        return khandLightFont;
    }

    public void setKhandLightFont(Typeface khandLightFont) {
        this.khandLightFont = khandLightFont;
    }

    public Typeface getKhandMediumFont() {
        return khandMediumFont;
    }

    public void setKhandMediumFont(Typeface khandMediumFont) {
        this.khandMediumFont = khandMediumFont;
    }

    public Typeface getKhandRegularFont() {
        return khandRegularFont;
    }

    public void setKhandRegularFont(Typeface khandRegularFont) {
        this.khandRegularFont = khandRegularFont;
    }

    public Typeface getKhandSemiBoldFont() {
        return khandSemiBoldFont;
    }

    public void setKhandSemiBoldFont(Typeface khandSemiBoldFont) {
        this.khandSemiBoldFont = khandSemiBoldFont;
    }

    public Typeface getComfortaaBoldFont() {
        return comfortaaBoldFont;
    }

    public void setComfortaaBoldFont(Typeface comfortaaBoldFont) {
        this.comfortaaBoldFont = comfortaaBoldFont;
    }

    public Typeface getComfortaaRegularFont() {
        return comfortaaRegularFont;
    }

    public void setComfortaaRegularFont(Typeface comfortaaRegularFont) {
        this.comfortaaRegularFont = comfortaaRegularFont;
    }

    public Typeface getComfortaaLightFont() {
        return comfortaaLightFont;
    }

    public void setComfortaaLightFont(Typeface comfortaaLightFont) {
        this.comfortaaLightFont = comfortaaLightFont;
    }

    public Typeface getComfortaaThinFont() {
        return comfortaaThinFont;
    }

    public void setComfortaaThinFont(Typeface comfortaaThinFont) {
        this.comfortaaThinFont = comfortaaThinFont;
    }

    public Typeface getLilitaoneRegularFont() {
        return lilitaoneRegularFont;
    }

    public void setLilitaoneRegularFont(Typeface lilitaoneRegularFont) {
        this.lilitaoneRegularFont = lilitaoneRegularFont;
    }

    public Typeface getArimoRegularFont() {
        return arimoRegularFont;
    }

    public void setArimoRegularFont(Typeface arimoRegularFont) {
        this.arimoRegularFont = arimoRegularFont;
    }

    public Typeface getArimoBoldFont() {
        return arimoBoldFont;
    }

    public void setArimoBoldFont(Typeface arimoBoldFont) {
        this.arimoBoldFont = arimoBoldFont;
    }

    public Typeface getOpensansRegularFont() {
        return opensansRegularFont;
    }

    public void setOpensansRegularFont(Typeface opensansRegularFont) {
        this.opensansRegularFont = opensansRegularFont;
    }

}
