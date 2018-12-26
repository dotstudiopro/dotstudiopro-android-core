package com.dotstudioz.dotstudioPRO.services.constants;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by mohsin on 15-10-2016.
 */
public class FontsConstants {
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
    }
}
