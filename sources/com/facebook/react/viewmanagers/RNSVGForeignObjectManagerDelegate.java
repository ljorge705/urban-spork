package com.facebook.react.viewmanagers;

import android.view.View;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGForeignObjectManagerInterface;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes5.dex */
public class RNSVGForeignObjectManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGForeignObjectManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGForeignObjectManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c = 0;
                    break;
                }
                break;
            case -1221029593:
                if (str.equals("height")) {
                    c = 1;
                    break;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    c = 2;
                    break;
                }
                break;
            case -993894751:
                if (str.equals("propList")) {
                    c = 3;
                    break;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    c = 4;
                    break;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    c = 5;
                    break;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    c = 6;
                    break;
                }
                break;
            case -734428249:
                if (str.equals(ViewProps.FONT_WEIGHT)) {
                    c = 7;
                    break;
                }
                break;
            case -729118945:
                if (str.equals("fillRule")) {
                    c = '\b';
                    break;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    c = '\t';
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = '\n';
                    break;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    c = 11;
                    break;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    c = '\f';
                    break;
                }
                break;
            case 120:
                if (str.equals("x")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case PanasonicMakernoteDirectory.TAG_INTELLIGENT_D_RANGE /* 121 */:
                if (str.equals("y")) {
                    c = 14;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = 15;
                    break;
                }
                break;
            case 3148879:
                if (str.equals("font")) {
                    c = 16;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = 17;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 18;
                    break;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    c = 19;
                    break;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    c = 20;
                    break;
                }
                break;
            case 113126854:
                if (str.equals("width")) {
                    c = 21;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 22;
                    break;
                }
                break;
            case 365601008:
                if (str.equals(ViewProps.FONT_SIZE)) {
                    c = 23;
                    break;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    c = 24;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 25;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 26;
                    break;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    c = 27;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 28;
                    break;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    c = 29;
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = 30;
                    break;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    c = 31;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case 1:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setHeight((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setHeight((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setHeight((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case 2:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                break;
            case 3:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                break;
            case 4:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                break;
            case 5:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                break;
            case 6:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStroke(t, (ReadableMap) obj);
                break;
            case 7:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontWeight((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontWeight((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontWeight((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case '\b':
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                break;
            case '\t':
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case '\n':
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                break;
            case 11:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                break;
            case '\f':
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case '\r':
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setX((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setX((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setX((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case 14:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setY((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setY((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setY((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case 15:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFill(t, (ReadableMap) obj);
                break;
            case 16:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFont(t, (ReadableMap) obj);
                break;
            case 17:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                break;
            case 18:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                break;
            case 19:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 20:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 21:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setWidth((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setWidth((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setWidth((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case 22:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                break;
            case 23:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontSize((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontSize((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setFontSize((RNSVGForeignObjectManagerInterface) t, (Double) null);
                    break;
                }
            case 24:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeDasharray((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof ReadableArray) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeDasharray((RNSVGForeignObjectManagerInterface) t, (ReadableArray) obj);
                    break;
                }
                break;
            case 25:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                break;
            case 26:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 27:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 28:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                break;
            case 29:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 30:
                ((RNSVGForeignObjectManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 31:
                if (obj instanceof String) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeWidth((RNSVGForeignObjectManagerInterface) t, (String) obj);
                    break;
                } else if (obj instanceof Double) {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeWidth((RNSVGForeignObjectManagerInterface) t, (Double) obj);
                    break;
                } else {
                    ((RNSVGForeignObjectManagerInterface) this.mViewManager).setStrokeWidth((RNSVGForeignObjectManagerInterface) t, "1");
                    break;
                }
            default:
                super.setProperty(t, str, obj);
                break;
        }
    }
}
