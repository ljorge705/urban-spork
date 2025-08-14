package com.clevertap.android.sdk.inapp;

import com.clevertap.android.sdk.Constants;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes5.dex */
public enum CTInAppType {
    CTInAppTypeHTML(Constants.INAPP_HTML_TAG),
    CTInAppTypeCoverHTML("coverHtml"),
    CTInAppTypeInterstitialHTML("interstitialHtml"),
    CTInAppTypeHeaderHTML("headerHtml"),
    CTInAppTypeFooterHTML("footerHtml"),
    CTInAppTypeHalfInterstitialHTML("halfInterstitialHtml"),
    CTInAppTypeCover("cover"),
    CTInAppTypeInterstitial("interstitial"),
    CTInAppTypeHalfInterstitial("half-interstitial"),
    CTInAppTypeHeader("header-template"),
    CTInAppTypeFooter("footer-template"),
    CTInAppTypeAlert("alert-template"),
    CTInAppTypeCoverImageOnly("cover-image"),
    CTInAppTypeInterstitialImageOnly("interstitial-image"),
    CTInAppTypeHalfInterstitialImageOnly("half-interstitial-image"),
    CTInAppTypeCustomCodeTemplate("custom-code"),
    UNKNOWN("");

    private final String inAppType;

    @Override // java.lang.Enum
    public String toString() {
        return this.inAppType;
    }

    CTInAppType(String str) {
        this.inAppType = str;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static CTInAppType fromString(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1824210231:
                if (str.equals("custom-code")) {
                    c = 0;
                    break;
                }
                break;
            case -1698613420:
                if (str.equals("half-interstitial-image")) {
                    c = 1;
                    break;
                }
                break;
            case -1258935355:
                if (str.equals("cover-image")) {
                    c = 2;
                    break;
                }
                break;
            case -1160074422:
                if (str.equals("halfInterstitialHtml")) {
                    c = 3;
                    break;
                }
                break;
            case -1141304454:
                if (str.equals("interstitial-image")) {
                    c = 4;
                    break;
                }
                break;
            case -728863497:
                if (str.equals("interstitialHtml")) {
                    c = 5;
                    break;
                }
                break;
            case -334055316:
                if (str.equals("footer-template")) {
                    c = 6;
                    break;
                }
                break;
            case -37253685:
                if (str.equals("alert-template")) {
                    c = 7;
                    break;
                }
                break;
            case 3213227:
                if (str.equals(Constants.INAPP_HTML_TAG)) {
                    c = '\b';
                    break;
                }
                break;
            case 94852023:
                if (str.equals("cover")) {
                    c = '\t';
                    break;
                }
                break;
            case 604727084:
                if (str.equals("interstitial")) {
                    c = '\n';
                    break;
                }
                break;
            case 894039686:
                if (str.equals("half-interstitial")) {
                    c = 11;
                    break;
                }
                break;
            case 1189018554:
                if (str.equals("header-template")) {
                    c = '\f';
                    break;
                }
                break;
            case 1420225510:
                if (str.equals("footerHtml")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case 1977176024:
                if (str.equals("headerHtml")) {
                    c = 14;
                    break;
                }
                break;
            case 1979390978:
                if (str.equals("coverHtml")) {
                    c = 15;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return CTInAppTypeCustomCodeTemplate;
            case 1:
                return CTInAppTypeHalfInterstitialImageOnly;
            case 2:
                return CTInAppTypeCoverImageOnly;
            case 3:
                return CTInAppTypeHalfInterstitialHTML;
            case 4:
                return CTInAppTypeInterstitialImageOnly;
            case 5:
                return CTInAppTypeInterstitialHTML;
            case 6:
                return CTInAppTypeFooter;
            case 7:
                return CTInAppTypeAlert;
            case '\b':
                return CTInAppTypeHTML;
            case '\t':
                return CTInAppTypeCover;
            case '\n':
                return CTInAppTypeInterstitial;
            case 11:
                return CTInAppTypeHalfInterstitial;
            case '\f':
                return CTInAppTypeHeader;
            case '\r':
                return CTInAppTypeFooterHTML;
            case 14:
                return CTInAppTypeHeaderHTML;
            case 15:
                return CTInAppTypeCoverHTML;
            default:
                return UNKNOWN;
        }
    }
}
